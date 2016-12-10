package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Drawable;
import com.mygdx.game.Basics.InputHandler;

abstract class BallState extends Drawable
{
    Ball m_ball = null;
    float m_timer = 0;
    InputHandler m_input;
    float m_gravity = -30.0f;
    float m_maxSpeed = -750.0f;

    BallState()
    {
        m_input = new InputHandler();
    }
    BallState(float timerSeconds)
    {
        m_timer = timerSeconds;
    }
    BallState(Ball b)
    {
        m_ball = b;
    }
    protected void setBall(Ball b)
    {
        m_ball = b;
    }

    @Override
    public void update(float dt)
    {
        m_input.update();
        m_timer-=dt;

        if(m_timer <= 0)
        {
            m_ball.setState(new BallStateNormal(m_ball));
        }
    }
    @Override
    public void render(SpriteBatch sb)
    {
        float x = m_ball.m_position.x;
        float y = m_ball.m_position.y;
        float r = m_ball.m_radius;

        sb.draw(m_texture,x-r,y-r,r+r,r+r);
    }
    protected abstract void onCollision(Vector2 pos, int side);

    @Override
    public String toString()
    {
        String str = "m_ball: " + m_ball.toString() + "\n";
        str += "m_timer: " + m_timer + "\n";
        return str;
    }
}
