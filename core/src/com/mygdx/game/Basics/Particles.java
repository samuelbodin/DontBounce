package com.mygdx.game.Basics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by Rickard on 2016-12-20.
 */

public class Particles implements Disposable
{

    Sprite[] m_sprites;
    float m_height, m_width, m_offsetX, m_offsetY;
    int m_numberOfParticles;
    float m_xSpread = 0;
    float m_ySpread = 0;
    boolean tail = true;
    boolean m_firstUpdate = true;

    public Particles(Texture texture, int numberOfParticles, float width, float height)
    {
        this(texture, numberOfParticles, width, height, 0, 0);
    }

    public Particles(Texture texture, int numberOfParticles, float width, float height, float offsetX, float offsetY)
    {
        m_height = height;
        m_width = width;
        m_offsetX = offsetX;
        m_offsetY = offsetY;
        m_numberOfParticles = numberOfParticles;
        m_sprites = new Sprite[numberOfParticles];
        for(int i = 0; i<numberOfParticles; i++)
        {
            m_sprites[i] = new Sprite(texture);
            m_sprites[i].setOrigin(0,0);
            m_sprites[i].setSize(width,height);
        }
    }

    public void setPosition(Vector2 pos)
    {
        if(tail || m_firstUpdate)
        {
            m_firstUpdate = false;

            Vector2 lastPos = new Vector2();
            lastPos.x = pos.x + m_offsetX;
            lastPos.y = pos.y + m_offsetY;
            for (int i = 0; i < m_sprites.length; i++)
            {
                float x = lastPos.x;
                float y = lastPos.y;
                lastPos = new Vector2(m_sprites[i].getX() + getXSpread(i), m_sprites[i].getY() + getYSpread(i));
                m_sprites[i].setPosition(x, y);
            }
        }
        else
        {
            for(Sprite s : m_sprites)
            {
                if(s.getColor().a <= 0)
                {
                    s.setPosition(pos.x, pos.y);
                    s.setAlpha(1);
                }
            }
        }
    }

    private float getXSpread(int i)
    {
        if(i%2 == 0)
        {
            return m_xSpread;
        }
        return -m_xSpread;
    }

    private float getYSpread(int i)
    {
        if(i%2 == 0)
        {
            return m_ySpread;
        }
        return -m_ySpread;
    }

    public void setColor(Color color)
    {
        for(Sprite s : m_sprites)
        {
            s.setColor(color);
        }
    }

    public void setSize(float width, float height)
    {
        for(Sprite s : m_sprites)
        {
            s.setSize(width, height);
        }
    }

    public void setSize(float size)
    {
        setSize(size, size);
    }

    public void setFade(float start, float end)
    {
        float factor = start-end;
        factor /= m_numberOfParticles;
        int i = 1;
        for(Sprite s : m_sprites)
        {
            s.setAlpha(1-(factor*i));
            i++;
        }

    }

    public void update(float dt)
    {
        if(!tail)
        {
            for(Sprite s : m_sprites)
            {
                s.setAlpha(s.getColor().a - (5*dt));
            }
        }
    }
    public void render(SpriteBatch sb)
    {
        for(Sprite s : m_sprites)
        {
            s.draw(sb);
        }
    }

    @Override
    public void dispose()
    {
        for(Sprite s : m_sprites)
        {
            s.getTexture().dispose();
        }
    }
}
