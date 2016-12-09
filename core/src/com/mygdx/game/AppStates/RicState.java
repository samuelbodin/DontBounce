package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

import java.util.Stack;

/**
 * Created by Rickard on 2016-12-07.
 */

public class RicState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;
    private static final float m_worldWidth = App.m_worldW;
    private static final float m_worldHeight = 10000;
    private static final float m_viewportWidth = App.m_worldW;
    private static final float m_viewportHeight = App.m_worldH;

    WorldBackground m_background;

    LevelGenerator m_level;
    Viewport m_viewport;

    public RicState(StateManager sm)
    {
        super(sm);


        m_ball = new Ball(m_viewportWidth/2, 0, m_viewportWidth/40);
        m_collidables = new Array<Collidable>();
        m_cam.setBall(m_ball);

        m_background = new WorldBackground(m_viewportWidth, m_viewportHeight);
        m_background.addFile("mountbg01.png");
        m_background.addFile("mountbg02.png");
        m_background.addFile("mountbg03.png");
        m_background.addFile("mountbg04.png");

        m_level = new LevelGenerator(5, m_worldHeight, 12, 15, 45, m_viewportWidth/6);
        m_level.addGoal(m_sm);
        m_collidables = m_level.getCollidables();
        m_viewport = new FitViewport(App.m_worldW, App.m_worldH, m_cam);
        m_viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

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
        m_background.setPosition(m_cam.getDeltaPosition());

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
