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
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.WorldBackground;

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
    private WorldBackground m_background;
    private LevelGenerator m_level;
    private Viewport m_viewport;
    Music m_music;

    public RicState(StateManager sm)
    {
        super(sm);

        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight, m_cam, true);

        m_level = new LevelGenerator(5, m_worldHeight, 12, 15, 45, m_viewportWidth/6);
        m_level.addGoal(m_sm);
        m_collidables = m_level.getCollidables();
        m_viewport = new FitViewport(App.m_worldW, App.m_worldH, m_cam);
        m_viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

        m_music = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_music.setLooping(true);
        m_music.setVolume(0.5f);
        m_music.play();

    }


    public RicState(StateManager sm, int seed, float obstacleSizeFactor, float obstacleSeparationFactor, float obstacleMinSpacingFactor, int ballType, boolean foreground, String[] files, String file)
    {
        super(sm);

        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        switch (ballType)
        {
            case 1:
                m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f, 2.0f));
                m_ball.doTrail();
                break;
            case 2:
                m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f, 2.0f));
                break;
            case 3:
                m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f, 0.5f));
                break;
            case 4:
                m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f, 2.0f));
                break;
            case 5:
                m_ball.setState(new BallStateMoveable(m_ball, -10.0f, -1500.0f,5.0f));
                break;
            default:
                m_ball.setState(new BallStateMoveable(m_ball));
        }

        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight, m_cam, foreground);

        for(String s : files)
        {
            m_background.addBackgroundImage(s);
            m_background.addForegroundImage(file);
        }

        m_level = new LevelGenerator(seed, m_worldHeight, obstacleSizeFactor, obstacleSeparationFactor, obstacleMinSpacingFactor, m_viewportWidth/12);
        m_level.addGoal(m_sm);
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
            if(c.m_position.y < m_ball.getPosition().y - balldia || c.m_position.y > m_ball.getPosition().y + balldia)
            {
                continue;
            }
            c.update(dt);
            c.checkCollision(m_ball);
        }
        Gdx.app.log("DT", Float.toString(dt));
        if(m_cam.position.y > -m_worldHeight)
        {
            m_cam.setToBallPos(dt);
            m_cam.update();
            m_background.update(dt);
            m_background.setPosition(m_cam.getDeltaPosition());
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(m_cam.combined);

        m_background.render(sb);


        for(Collidable c : m_collidables)
        {
            if(c.m_position.y < m_ball.getPosition().y - App.m_worldH || c.m_position.y > m_ball.getPosition().y + App.m_worldH)
            {
                continue;
            }
            c.render(sb);
        }

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
