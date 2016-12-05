package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Drawable;

public class Ball
{

    final float m_gravity = -50.0f;
    private final float m_maxSpeed = -750.0f;
    boolean m_isOnGround = false;
    float m_radius = 0.0f;
    Vector2 m_position = null;
    Vector2 m_velocity = null;
    private BallState m_state = null;


    public Ball (float x, float y, float r)
    {
        m_position = new Vector2(x,y);
        m_velocity = new Vector2(0,0);
        m_radius = r;
        m_state = new BallStateNormal(this);
    }

    protected void setState(BallState s)
    {
        m_state = s;
    }
    public void update(float dt)
    {
        m_state.update(dt);

        if(m_isOnGround)
        {
            m_isOnGround = false;
        }
        else
        {

            m_velocity.add(0,m_gravity);
        }

        if(m_velocity.y < m_maxSpeed)
        {
            m_velocity.y = m_maxSpeed;
        }

        m_position.add(m_velocity.x*dt,m_velocity.y*dt);


    }
    public void render(SpriteBatch sb)
    {
        m_state.render(sb);
    }
    public void onCollision(Vector2 pos)
    {
        m_state.onCollision(pos);
    }
    public Vector2 getPosition()
    {
        return m_position;
    }
    public Vector2 getVelocity()
    {
        return m_velocity;
    }

    public Rectangle getRect()
    {
        return new Rectangle(m_position.x-m_radius, m_position.y-m_radius,m_radius+m_radius,m_radius+m_radius);
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "m_position: " + m_position.toString() + "\n";
        str += "m_gravity: " + m_gravity + "\n";
        str += "m_maxSpeed: " + m_maxSpeed + "\n";
        str += "m_radius: " + m_radius + "\n";
        str += "m_velocity: " + m_velocity.toString() + "\n";
        str += "m_state: " + m_state.toString() + "\n";

        return str;
    }
}
