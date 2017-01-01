package com.mygdx.game.Obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Circle;

/**
 * Created by Rickard on 2017-01-01.
 */

public class MovingObstacle extends Obstacle
{
    ObstacleBuilder m_ob;
    int m_direction = 1;
    float m_range = 5;
    float m_speed = 10;
    float m_deltaX = 0;

    public MovingObstacle(float x, float y, float w, float h)
    {
        this(x,y,w,h, Color.ORANGE);
    }

    public MovingObstacle(float x, float y, float w, float h, Color tint)
    {
        super(x,y,w,h);
        m_ob = new ObstacleBuilder(x,y,w,h,tint);
    }

    @Override
    protected Vector2 getCollisionPosition(Circle c)
    {
        float x = MathUtils.clamp(c.m_x, m_position.x, m_position.x + m_width);
        float y = MathUtils.clamp(c.m_y, m_position.y, m_position.y + m_height);
        if(y < m_position.y+(m_height/2))
        {
            y = m_position.y;
        }
        else
        {
            y = m_position.y + m_height;
        }

        return new Vector2(x,y);
    }

    @Override
    public void update(float dt)
    {
        m_deltaX += (m_speed * dt * m_direction);
        Gdx.app.log("RL", "Deltax:" + m_deltaX);
        if(Math.abs(m_deltaX) > m_range)
        {
            m_deltaX = m_range * m_direction;
            m_direction *= -1;
        }
        m_ob.update(dt, m_deltaX);
        setPosition(m_ob.getPosition());
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_ob.render(sb);
    }

    @Override
    public void dispose()
    {
        m_ob.dispose();
    }
}
