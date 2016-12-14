package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;

/**
 * Created by Rickard on 2016-12-09.
 */

public class BallStateFast extends BallState
{

    public BallStateFast(Ball b)
    {
        m_ball = b;
        m_texture = new Texture("ball2.png");
        m_timer = 400;
        m_gravity = -80.0f;
        m_maxSpeed = -1500.0f;
    }


    @Override
    public void update(float dt)
    {
        super.update(dt);

        float move = m_input.m_deltaMove * 300 * dt;
        if(m_ball.m_position.x-m_ball.m_radius+move < 0)
        {
            m_ball.m_velocity.x = 0;
            m_ball.m_position.x = m_ball.m_radius;
        }
        else if(m_ball.m_position.x+m_ball.m_radius+move > App.m_worldW)
        {
            m_ball.m_velocity.x = 0;
            m_ball.m_position.x =  App.m_worldW-m_ball.m_radius;
        } else
        {
            m_ball.m_position.x += m_input.m_deltaMove * 300 * dt;
            //m_ball.m_velocity.x += m_input.m_deltaMove * 50 * dt;
        }
    }

    @Override
    protected void onCollision(Vector2 pos, int side)
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
    protected void onCollision(Vector2 pos, int side, Vector2 pos1, Vector2 pos2)
    {

    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
    }
}
