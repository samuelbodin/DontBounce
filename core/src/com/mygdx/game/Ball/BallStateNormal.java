package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BallStateNormal extends BallState
{
    public BallStateNormal(Ball b)
    {
        m_ball = b;
        m_texture = new Texture("ball2.png");
    }

    @Override
    public void update(float dt)
    {

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
