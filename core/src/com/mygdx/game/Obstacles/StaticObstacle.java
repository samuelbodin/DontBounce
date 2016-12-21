package com.mygdx.game.Obstacles;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Circle;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.graphics.Color;

public class StaticObstacle extends Obstacle
{
    ObstacleBuilder m_ob;

    public StaticObstacle(float x, float y, float w, float h)
    {
        this(x,y,w,h, Color.ORANGE);
    }

    public StaticObstacle(float x, float y, float w, float h, Color tint)
    {
        super(x,y,w,h);
        m_ob = new ObstacleBuilder(x,y,w,h,tint);
    }

    @Override
    protected Vector2 getCollisionPosition(Circle c)
    {
        float x = MathUtils.clamp(c.m_x, m_position.x, m_position.x + m_width);
        float y = MathUtils.clamp(c.m_y, m_position.y, m_position.y + m_height);

        float distanceX = c.m_x - x;
        float distanceY = c.m_y - y;

        float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
        //m_ob.setCracked(new Vector2(x,y));
        return new Vector2(x,y);
    }

    @Override
    public void update(float dt)
    {
        m_ob.update(dt);
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_ob.render(sb);
        //sb.draw(m_texture,m_position.x,m_position.y,m_width,m_height);
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
        m_ob.dispose();
    }
}
