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
    protected boolean hasDeltaMove()
    {
        return false;
    }

    @Override
    public void updateSprite()
    {
        m_sprite.setOriginCenter();
        m_sprite.setSize(m_ball.m_radius*2, m_ball.m_radius*2);
        m_sprite.setPosition(m_ball.m_position.x-m_ball.m_radius, m_ball.m_position.y-m_ball.m_radius);
    }

    @Override
    public void update(float dt)
    {

    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    protected void onCollision(Vector2 pos, int side)
    {
        if(side != 0)
        {
            Gdx.app.log("JS", "HERE!! IT HAPPENED!! side: " + side);
        }


        /*if(side == 0 && m_ball.m_velocity.y <= -m_ball.m_gravity)
        {
            m_ball.m_isOnGround = true;
            m_ball.m_position.y = pos.y + m_ball.m_radius;
            m_ball.m_velocity.y = 0;
        }
            else */if(side == 0)
        {
            m_ball.m_position.y = pos.y + m_ball.m_radius;
            m_ball.m_velocity.y = Math.abs(m_ball.m_velocity.y);
        }
        else if(side == 1)
        {
            m_ball.m_position.x = pos.x + m_ball.m_radius;
            m_ball.m_velocity.scl(-1f,1f);
        }
        else if(side == 2)
        {
            m_ball.m_position.y = pos.y - m_ball.m_radius;
            m_ball.m_velocity.y = Math.abs(m_ball.m_velocity.y);
        }
        else if(side == 3)
        {
            m_ball.m_position.x = pos.x - m_ball.m_radius;
            m_ball.m_velocity.scl(-1f,1f);
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
