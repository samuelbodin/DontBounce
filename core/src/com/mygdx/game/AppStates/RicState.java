package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Basics.InputHandler;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.TimeHandler;
import com.mygdx.game.Basics.WorldBackground;
import com.mygdx.game.Obstacles.LevelGoal;
import com.mygdx.game.Obstacles.PowerUp;

public class RicState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private WorldBackground m_background = null;
    private LevelGenerator m_level = null;
    private LevelGoal m_goal = null;
    private Array<PowerUp> m_powerUps = null;
    private Viewport m_viewport = null;
    private Music m_music = null;
    private LevelData m_levelData = null;
    private InputHandler m_input = null;

    private TimeHandler m_time;

    //Ändras till att ta in en int och requesta leveldata från "config"
    public RicState(StateManager sm, LevelData levelData)
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
        for(String s : m_levelData.m_backgroundFiles)
        {
            m_background.addBackgroundImage(s);
        }
        m_background.addForegroundImage(m_levelData.m_foregroundFile);
    }

    void setupLevel()
    {
        // TODO: Get color tint from Config when posible
        Color c = new Color(0.25f,0.5f,0.75f,1);

        //Creating a new level and filling list of collidables
        m_level = new LevelGenerator(m_levelData.m_seed, m_config.m_worldW, m_config.m_worldH, m_levelData.m_worldHeight, m_levelData.m_obstacleSizeFactor, m_levelData.m_obstacleSeparationFactor, m_levelData.m_obstacleMinSpacingFactor, m_config.m_worldW/12, c);

        m_goal = m_level.getGoal();
        m_powerUps = m_level.getPowerUps();
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
        if(m_cam.position.y > -m_levelData.m_worldHeight)
        {
            m_cam.setToBallPos(dt);
            m_cam.update();
            m_background.update(dt);
            m_background.setPosition(m_cam.getDeltaPosition());
            // Set timer position after camera is updated.
            m_time.update(dt, m_cam.getDeltaPosition());
        }

        // Check collision with goal when goal is on screen
        if( m_cam.isOnScreen( ((Collidable)m_goal).getPosition() ) )
        {
            if(m_goal.checkCollision(m_ball))
            {
                m_time.stop();
                m_sm.push(new LevelFinishedState(m_sm, m_time));
            }
        }

        // Start timer
        if(!m_time.isRunning())
        {
            m_time.start();
        }
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

        // Render only power ups that are in "camera view"
        for(PowerUp p : m_powerUps)
        {
            if(m_cam.isOnScreen(p.getPosition()))
            {
                p.render(sb);
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

        Gdx.app.log("RL", "RicState disposar");

    }

    @Override
    public void resize(int width, int height)
    {

    }
}
