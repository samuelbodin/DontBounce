package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;

/**
 * Created by Rickard on 2016-12-09.
 */

public class WorldBorders extends Obstacle
{
    public WorldBorders(float x, float y, float w, float h)
    {
        super(x,y,w,h);
        m_texture = new Texture("obs5.png");
    }

    @Override
    protected Vector2 getCollisionPos(Ball b)
    {
        return new Vector2(0,m_position.y+m_height);
    }

    @Override
    protected boolean isColliding(Ball b)
    {
        Rectangle rect = new Rectangle(m_position.x,m_position.y,m_width,m_height);

        return rect.overlaps(b.getRect());
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
