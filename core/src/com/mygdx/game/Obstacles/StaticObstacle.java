package com.mygdx.game.Obstacles;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Circle;

import org.w3c.dom.css.Rect;

public class StaticObstacle extends Obstacle
{
    public StaticObstacle(float x, float y, float w, float h)
    {
        super(x,y,w,h);
        m_texture = new Texture("obs1.png");
    }

    @Override
    protected Vector2 getCollisionPosition(Circle c)
    {
        float x = MathUtils.clamp(c.m_x, m_position.x, m_position.x + m_width);
        float y = MathUtils.clamp(c.m_y, m_position.y, m_position.y + m_height);

        float distanceX = c.m_x - x;
        float distanceY = c.m_y - y;

        float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

        return new Vector2(x,y);
    }

    @Override
    public void update(float dt)
    {
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
    }
}
