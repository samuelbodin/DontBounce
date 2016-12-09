package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Basics.InputHandler;
import com.mygdx.game.Basics.LevelGenerator;
import com.mygdx.game.Basics.SimplexNoise;
import com.mygdx.game.Basics.WorldBackground;
import com.mygdx.game.Obstacles.StaticObstacle;

/**
 * Created by Rickard on 2016-12-07.
 */

public class RicState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private static final float m_worldWidth = App.m_worldW;
    private static final float m_worldHeight = 3000;
    private static final float m_viewportWidth = App.m_worldW;
    private static final float m_viewportHeight = App.m_worldH;

    WorldBackground m_background;

    LevelGenerator m_level;
    Viewport viewport;

    public RicState(StateManager sm)
    {
        super(sm, m_viewportWidth, m_viewportHeight);


        m_ball = new Ball(0, 0, m_viewportWidth/40);
        m_collidables = new Array<Collidable>();
        m_cam.setPos(0,0);
        m_cam.setBall(m_ball);
        m_cam.position.set(0,0,0);
        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight);
        m_level = new LevelGenerator(3, m_worldHeight, 12, 25, 45, 150);
        m_collidables = m_level.getCollidables();
        //viewport = new FitViewport(m_viewportWidth,m_viewportHeight,m_cam);
        //viewport.apply();

    }

    @Override
    public void update(float dt)
    {
        for(Collidable c : m_collidables)
        {
            c.update(dt);
            c.checkCollision(m_ball);
        }

        m_ball.update(dt);

        m_cam.setToBallPos(dt);
        m_cam.update();
        m_background.setPosition(m_ball.m_deltaPosition);

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(m_cam.combined);

        m_background.render(sb);


        for(Collidable c : m_collidables)
        {
            c.render(sb);
        }

        m_ball.render(sb);

    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void dispose()
    {

    }

}
