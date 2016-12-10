package com.mygdx.game.AppStates;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Collidable;
import com.mygdx.game.Obstacles.StaticObstacle;

public class TestState extends State
{
    private Ball m_ball = null;
    private Array<Collidable> m_collidables = null;

    public TestState(StateManager sm)
    {
        super(sm);

        m_ball = new Ball(0, 0, 25);
        m_collidables = new Array<Collidable>();
        m_collidables.add(new StaticObstacle(0,-500,100,25));
        m_cam.setPos(0,0);
        m_cam.setBall(m_ball);
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
    }

    @Override
    public void render(SpriteBatch sb)
    {

        sb.setProjectionMatrix(m_cam.combined);
        m_ball.render(sb);

        for(Collidable c : m_collidables)
        {
            c.render(sb);
        }
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }
}
