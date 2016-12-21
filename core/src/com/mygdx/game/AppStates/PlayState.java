package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Basics.InputHandler;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.TimeHandler;
import com.mygdx.game.Basics.WorldBackground;
import com.mygdx.game.Obstacles.LevelGoal;
import com.mygdx.game.Obstacles.PowerUpSuperSpeed;

public class PlayState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private WorldBackground m_background = null;
    private LevelGenerator m_level = null;
    private LevelGoal m_goal = null;
    private Array<PowerUpSuperSpeed> m_powerUps = null;
    private Viewport m_viewport = null;
    private Music m_music = null;
    private LevelData m_levelData = null;
    private InputHandler m_input = null;

    private TimeHandler m_time;

    //Ändras till att ta in en int och requesta leveldata från "config"
    public PlayState(StateManager sm, LevelData levelData)
    {
        super(sm);
        m_levelData = levelData;

        //Setting up ball
        m_ball = new Ball(m_config.m_worldW/ 2, 0, m_config.m_worldW / 40, m_config.m_worldW, m_levelData);

        // Paranting camera to ball
        m_cam.setBall(m_ball);
        m_cam.setWorldDimensions(m_config.m_worldW, m_config.m_worldH);

        m_input = new InputHandler();

        setupBackground();
        setupLevel();
        setupViewPort();
        setupTimer();
        //setupMusic();


    }

    void setupBackground()
    {
        // Creating new world background and add images
        m_background = new WorldBackground(m_config.m_worldW, m_config.m_worldH, m_cam, m_levelData.m_foreground);
        for(TextureRegion s : m_levelData.m_backgroundFiles)
        {
            m_background.addBackgroundImage(s);
        }
        m_background.addForegroundImage(m_levelData.m_foregroundFile);
    }

    void setupLevel()
    {
        //Creating a new level and filling list of collidables
        m_level = new LevelGenerator(m_levelData, m_config.m_worldW, m_config.m_worldH);

        m_goal = m_level.getGoal();
        m_collidables = new Array<Collidable>();
        m_collidables = m_level.getCollidables();

    }

    void setupViewPort()
    {
        // Setting up viewport
        m_viewport = new FitViewport(m_config.m_worldW, m_config.m_worldH, m_cam);
        m_viewport.setScreenBounds(0, 0, m_config.m_screenW, m_config.m_screenH);
        m_viewport.apply(true);
    }

    void setupMusic()
    {
        //Setting up music
        m_music = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_music.setLooping(true);
        m_music.setVolume(0.5f);
        m_music.play();

    }

    void setupTimer()
    {
        m_time = new TimeHandler(30, m_cam.getY() + (m_config.m_worldH / 2) - 20);
    }

    @Override
    public void update(float dt)
    {
        handleInput();

        m_input.update(dt);
        m_ball.addToPositionX(m_input.getMoveValue());

        m_ball.update(dt);

        // Check collision with obstacles that are visible
        for (Collidable c : m_collidables)
        {
            if(m_cam.isOnScreen(c.getPosition()))
            {
                c.update(dt);
                c.checkCollision(m_ball);
            }
        }

        // Stop camera at worlds end
        if(m_cam.position.y > -m_levelData.m_levelHeight)
        {
            m_cam.setToBallPos(dt);
            m_cam.update();
            m_background.update(dt);
            m_background.setPosition(m_cam.getDeltaPosition());
            // Set timer position after camera is updated.
            m_time.updatePosition(m_cam.getDeltaPosition());
        }

        // Check collision with goal when goal is on screen
        if( m_cam.isOnScreen( ((Collidable)m_goal).getPosition() ) )
        {
            if(m_goal.checkCollision(m_ball))
            {
                m_time.stop();
                m_sm.push(new LevelFinishedState(m_sm, m_time, m_levelData));
            }
        }

        // Start timer
        if(!m_time.isRunning())
        {
            m_time.start();
        }
        m_time.update(dt*m_ball.getDtModifier());
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(m_cam.combined);
        m_background.render(sb);


        // Render only obstables that are in "camera view"
        for(Collidable c : m_collidables)
        {
            if(m_cam.isOnScreen(c.getPosition()))
            {
                c.render(sb);
            }
        }

        m_goal.render(sb);
        m_ball.render(sb);
        m_time.render(sb);
    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            if(m_music != null)
            {
                m_music.stop();
            }
            m_sm.push(new PauseState(m_sm));
        }
    }

    @Override
    public void dispose()
    {
        if(m_music != null)
        {
            m_music.stop();
            m_music.dispose();
        }

        m_background.dispose();
        m_goal.dispose();
        m_ball.dispose();

        Gdx.app.log("RL", "PlayState disposar");

    }

    @Override
    public void resize(int width, int height)
    {

    }
}
