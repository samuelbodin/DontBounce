package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.AppStates.PauseState;
import com.mygdx.game.AppStates.State;
import com.mygdx.game.AppStates.StateManager;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Ball.BallStateFast;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.WorldBackground;

/**
* Created by Rickard on 2016-12-14.
*/

public class UserTest01State extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private static final float m_worldWidth = App.m_worldW;
    private static final float m_worldHeight = 14000;
    private static final float m_viewportWidth = App.m_worldW;
    private static final float m_viewportHeight = App.m_worldH;
    private WorldBackground m_background;
    private LevelGenerator m_level;
    private Viewport m_viewport;
    Music m_music;
    float m_countDown = 1f;
    Sprite m_blackScreen;
    boolean m_init = false;

    public UserTest01State(StateManager sm)
    {
        super(sm);

        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight, m_cam, true);
        m_background.addFile("mountbg01.png");
        m_background.addFile("mountbg01.png");
        m_background.addFile("mountbg03.png");
        m_background.addFile("mountbg04.png");

        m_level = new LevelGenerator(5, m_worldHeight, 12, 15, 45, m_viewportWidth/6);
        m_level.addGoal(m_sm);
        m_collidables = m_level.getCollidables();
        m_viewport = new FitViewport(App.m_worldW, App.m_worldH, m_cam);
        m_viewport.setScreenBounds(0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

        m_music = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_music.setLooping(true);
        m_music.setVolume(0.5f);
        //m_music.play();

        m_blackScreen = new Sprite(new Texture("black.png"));
    }


    public UserTest01State(StateManager sm, int seed, float obstacleSizeFactor, float obstacleSeparationFactor, float obstacleMinSpacingFactor, boolean fast, boolean foreground)
    {
        super(sm);

        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        if(fast)
        {
            m_ball.setState(new BallStateFast(m_ball));
        }
        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight, m_cam, foreground);
        m_background.addFile("mountbg01.png");
        m_background.addFile("mountbg01.png");
        m_background.addFile("mountbg03.png");
        m_background.addFile("mountbg04.png");

        m_level = new LevelGenerator(seed, m_worldHeight, obstacleSizeFactor, obstacleSeparationFactor, obstacleMinSpacingFactor, m_viewportWidth/12);
        m_level.addGoal(m_sm);
        m_collidables = m_level.getCollidables();
        m_viewport = new FitViewport(App.m_worldW, App.m_worldH, m_cam);
        m_viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

        m_blackScreen = new Sprite(new Texture("black.png"));
    }

    @Override
    public void update(float dt)
    {

        if(m_countDown < 0 || m_init == false)
        {
            m_init = true;
            handleInput();

            for (Collidable c : m_collidables)
            {
                c.update(dt);
                c.checkCollision(m_ball);
            }

            m_ball.update(dt);
            m_cam.setToBallPos(dt);
            m_cam.update();
            m_background.update(dt);
            m_background.setPosition(m_cam.getDeltaPosition());

        }
        if(m_countDown > 0)
        {
            m_blackScreen.setPosition(0, -m_cam.viewportHeight+m_ball.getPosition().y);
            m_countDown -= dt;
            m_blackScreen.setAlpha(m_countDown);
            return;
        }


    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(m_cam.combined);

        if(m_countDown > 0)
        {
            m_blackScreen.draw(sb);
        }

        m_background.render(sb);


        for(Collidable c : m_collidables)
        {
            c.render(sb);
        }

        m_ball.render(sb);

        if(m_countDown > 0)
        {
            m_blackScreen.draw(sb);
        }
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            m_sm.push(new PauseState(m_sm));
            //m_sm.set(new UserTestMenu(m_sm));
        }
    }

    @Override
    public void dispose()
    {
        m_music.dispose();
    }

    @Override
    public void resize(int width, int height)
    {

    }

}