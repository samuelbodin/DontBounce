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
        m_timer = 400;
        m_gravity = -50.0f;
        m_maxSpeed = -1000.0f;
    }


    @Override
    public void update(float dt)
    {
        super.update(dt);
        if(!m_onBorder)
        {
            m_ball.m_position.x += m_input.m_deltaMove * 300 * dt;
            m_ball.m_velocity.x += m_input.m_deltaMove * 50 * dt;
        }
    }

    @Override
    protected void onCollision(Vector2 pos)
    {
        m_ball.m_position.y = pos.y+m_ball.m_radius-1;

        if(m_ball.m_velocity.y >= 0 && m_ball.m_velocity.y <= -m_gravity)
        {
            m_ball.m_isOnGround = true;
            m_ball.m_velocity.y = 0;
        }
        else
        {
            m_ball.m_velocity.scl(0,-0.95f);
        }
    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

}
