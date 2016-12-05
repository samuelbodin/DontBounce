package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Collidable;

public abstract class Obstacle extends Collidable
{
    protected float m_width = 0.0f;
    protected float m_height = 0.0f;

    public Obstacle(float x, float y, float w, float h)
    {
        super(x,y);
        m_width = w;
        m_height = h;
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.draw(m_texture,m_position.x,m_position.y,m_width,m_height);
    }

    protected abstract Vector2 getCollisionPos(Ball b);
    protected abstract boolean isColliding(Ball b);
    public void checkCollision(Ball b)
    {
        if(isColliding(b))
        {
            b.onCollision(getCollisionPos(b));
        }
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "m_width: " + m_width + "\n";
        str += "m_height: " + m_height + "\n";
        return str;
    }
}
