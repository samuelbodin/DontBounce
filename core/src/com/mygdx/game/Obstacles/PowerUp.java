package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Circle;

/**
 * Created by Rickard on 2016-12-19.
 */

public class PowerUp extends Obstacle
{
    Sprite m_sprite;

    public PowerUp(float x, float y, float w, float h)
    {
        super(x,y,w,h);
        m_texture = new Texture("speedup.png");
        m_sprite = new Sprite(m_texture);
        m_sprite.setSize(w, h);
        m_sprite.setPosition(x,y);
    }

    @Override
    protected Vector2 getCollisionPosition(Circle c)
    {
        return null;
    }

    @Override
    public boolean checkCollision(Ball b)
    {
        Array<Circle> arr = b.getCircles();

        int index = getPossibleCollisionIndex(arr);

        if(index != -1)
        {
            return true;
        }
        return false;
    }

    @Override
    public void update(float dt)
    {

    }

    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
    }
}
