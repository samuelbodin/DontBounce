package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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

    @Override
    protected boolean isColliding(Circle c)
    {
        return  ! ( m_position.x > c.m_x + c.m_radius || m_position.x + m_width < c.m_x - c.m_radius
                || m_position.y + m_height < c.m_y - c.m_radius || m_position.y > c.m_y + c.m_radius);
    }

    protected abstract Vector2 getCollisionPosition(Circle c);

    protected int getPossibleCollisionIndex(Array<Circle> arr)
    {
        int side1 = -1;
        int side2 = -1;
        int lastIndex = arr.size-1;

        if(isColliding(arr.get(lastIndex)))
        {
            side1 = getSideOfThis(arr.get(lastIndex).m_x, arr.get(lastIndex).m_y);
            side2 = getSideOfThis(arr.get(lastIndex-1).m_x, arr.get(lastIndex-1).m_y);

            if( (side1 != -1 && side2 != -1) && side1 != side2 )
            {
                arr.get(lastIndex-1).m_side = side2;
                arr.pop();
                return lastIndex-1;
            }
            else
            {
                arr.get(lastIndex).m_side = side1;
                return lastIndex;
            }
        }


        for(int i = arr.size-1; i >= 1; i--)
        {
            side1 = getSideOfThis(arr.get(i).m_x, arr.get(i).m_y);
            side2 = getSideOfThis(arr.get(i-1).m_x, arr.get(i-1).m_y);

            if( (side1 != -1 && side2 != -1) && side1 != side2 )
            {
                arr.get(i-1).m_side = side2;
                arr.removeIndex(i);

                for(int j = lastIndex; j > (i); j--)
                {
                    arr.pop();
                }
                return i-1;
            }
        }
        return -1;
    }

    private int getSideOfThis(float x, float y)
    {
        int side = -1;

        if((x >= m_position.x && x <= m_position.x + m_width)
                && y >= m_position.y + m_height/2)
        {
            // Top
            side = 0;
        }
        else if((x >= m_position.x && x <= m_position.x + m_width)
                && y < m_position.y + m_height/2)
        {
            // Bottom
            side = 2;
        }
        else if(x >= m_position.x + m_width/2
                && y >= m_position.y && y <= m_position.y + m_height)
        {
            // Right
            side = 1;
        }
        else if(x < m_position.x + m_width/2
                && y >= m_position.y && y <= m_position.y + m_height)
        {
            // Left
            side = 3;
        }

        return side;
    }

    public boolean checkCollision(Ball b)
    {
        Array<Circle> arr = b.getCircles();

        int index = getPossibleCollisionIndex(arr);

        if(index != -1)
        {
            Vector2 collisionPosition = getCollisionPosition(arr.get(index));

            if(arr.get(index).m_side == -1)
            {
                if( arr.get(index).m_x >= m_position.x + m_width/2 )
                {
                    arr.get(index).m_side = 1;
                }
                else
                {
                    arr.get(index).m_side = 3;
                }
            }

            b.onCollision(collisionPosition, arr.get(index).m_side);
            return true;
        }
        return false;
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
