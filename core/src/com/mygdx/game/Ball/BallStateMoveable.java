package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;
import com.mygdx.game.Basics.InputHandler;

/**
 * Created by Rickard on 2016-12-07.
 */

public class BallStateMoveable extends BallState
{
    float m_prevDeltaMove = 0;

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

        float move = m_input.m_deltaMove * 300 * dt;

        Gdx.app.log("DELTAMOVE", Float.toString(m_input.m_deltaMove));


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
            move = (m_prevDeltaMove+m_input.m_deltaMove)/2;
            m_ball.m_position.x += move * 300 * dt;
            m_ball.m_velocity.x += move * 150 * dt;
        }

        m_prevDeltaMove = m_input.m_deltaMove;
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
