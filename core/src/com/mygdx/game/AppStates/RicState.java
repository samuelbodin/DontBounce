package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Ball.BallStateFast;
import com.mygdx.game.Ball.BallStateMoveable;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.WorldBackground;
import com.mygdx.game.Obstacles.LevelGoal;

/**
 * Created by Rickard on 2016-12-07.
 */

public class RicState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private static final float m_worldWidth = App.m_worldW;
    private static final float m_worldHeight = 15000;
    private static final float m_viewportWidth = App.m_worldW;
    private static final float m_viewportHeight = App.m_worldH;
    private WorldBackground m_background = null;
    private LevelGenerator m_level = null;
    private LevelGoal m_goal = null;
    private Viewport m_viewport = null;
    Music m_music = null;

    LevelData m_levelData;

    public RicState(StateManager sm)
    {
        super(sm);
    }

    //Ändras till att ta in en int och requesta leveldata från "config"
    public RicState(StateManager sm, LevelData levelData)
    {
        super(sm);

        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f, 2.0f));
        m_ball.doTrail();


        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight, m_cam, levelData.m_foreground);

        for(String s : levelData.m_backgroundFiles)
        {
            m_background.addBackgroundImage(s);
            m_background.addForegroundImage(levelData.m_foregroundFile);
        }

        m_level = new LevelGenerator(levelData.m_seed, m_worldHeight, levelData.m_obstacleSizeFactor, levelData.m_obstacleSeparationFactor, levelData.m_obstacleMinSpacingFactor, m_viewportWidth/12);
        m_goal = m_level.getGoal();
        m_collidables = m_level.getCollidables();
        m_viewport = new FitViewport(App.m_worldW, App.m_worldH, m_cam);
        m_viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

        m_music = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_music.setLooping(true);
        m_music.setVolume(0.5f);
        //m_music.play();
    }

    @Override
    public void update(float dt)
    {

        handleInput();
        m_ball.update(dt);
        float balldia = 32;

        for (Collidable c : m_collidables)
        {
            if(c.isOnScreen(m_ball.getPosition()))
            {
                c.update(dt);
                c.checkCollision(m_ball);
            }
        }

        if(m_cam.position.y > -m_worldHeight)
        {
            m_cam.setToBallPos(dt);
            m_cam.update();
            m_background.update(dt);
            m_background.setPosition(m_cam.getDeltaPosition());
        }
        if( ((Collidable)m_goal).isOnScreen(m_ball.getPosition()) )
        {
            if(m_goal.checkCollision(m_ball))
            {
                m_sm.set(new UserTestMenu(m_sm));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(m_cam.combined);

        m_background.render(sb);


        for(Collidable c : m_collidables)
        {
            if(c.isOnScreen(m_ball.getPosition()))
            {
                c.render(sb);
            }
        }
        m_goal.render(sb);
        m_ball.render(sb);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            m_music.stop();
            m_sm.push(new PauseState(m_sm));
        }
    }

    @Override
    public void dispose()
    {
        m_music.stop();
        m_music.dispose();
    }

    @Override
    public void resize(int width, int height)
    {

    }


}
