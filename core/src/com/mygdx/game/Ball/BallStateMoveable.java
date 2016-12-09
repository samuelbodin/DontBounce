package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.InputHandler;

/**
 * Created by Rickard on 2016-12-07.
 */

public class BallStateMoveable extends BallState
{

    public BallStateMoveable(Ball b)
    {
        m_ball = b;
        m_texture = new Texture("ball1.png");

    }


    @Override
    public void update(float dt)
    {

    }

    public void update(float dt, float deltaMove)
    {
        m_ball.m_position.x += deltaMove*300*dt;
        m_ball.m_velocity.x += deltaMove*20*dt;
    }

    @Override
    protected void onCollision(Vector2 pos)
    {
        m_ball.m_position.y = pos.y+m_ball.m_radius-1;

        if(m_ball.m_velocity.y >= 0 && m_ball.m_velocity.y <= -m_ball.m_gravity)
        {
            m_ball.m_isOnGround = true;
            m_ball.m_velocity.y = 0;
        }
        else
        {
            m_ball.m_velocity.scl(0,-0.90f);
        }
    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

}
