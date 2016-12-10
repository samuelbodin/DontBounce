package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.App;
import com.mygdx.game.Basics.Drawable;

public class Ball
{
    boolean m_isOnGround = false;
    float m_radius = 0.0f;
    Vector2 m_position = null;
    Vector2 m_velocity = null;
    private BallState m_state = null;
    private BallPositionHistory m_history = null;
    int m_iterator = 0;

    public Ball (float x, float y, float r)
    {
        m_position = new Vector2(x,y);
        m_velocity = new Vector2(0,0);
        m_radius = r;
        m_state = new BallStateMoveable(this);
        m_history = new BallPositionHistory(3);
        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position,m_radius, m_iterator++));
    }

    public void setState(BallState s)
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

            m_velocity.add(0,m_state.m_gravity);
        }

        if(m_velocity.y < m_state.m_maxSpeed)
        {
            m_velocity.y = m_state.m_maxSpeed;
        }

        m_position.add(m_velocity.x*dt,m_velocity.y*dt);

        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position, m_radius, m_iterator++));
    }
    public void render(SpriteBatch sb)
    {
        m_state.render(sb);
    }
    public void onCollision(Vector2 pos, int side)
    {
        m_state.onCollision(pos, side);
    }
    public Vector2 getPosition()
    {
        return m_position;
    }
    public Vector2 getVelocity()
    {
        return m_velocity;
    }
    public void addToPositionX(float value)
    {
        m_position.x+=value;
    }

    public Array<com.mygdx.game.Basics.Circle> getCircles()
    {
        return m_history.m_circles;
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "m_position: " + m_position.toString() + "\n";
        str += "m_radius: " + m_radius + "\n";
        str += "m_velocity: " + m_velocity.toString() + "\n";
        str += "m_state: " + m_state.toString() + "\n";

        return str;
    }
}
