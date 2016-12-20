package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Drawable;

/**
 * Created by Rickard on 2016-12-10.
 */

public class ObstacleBuilder extends Drawable
{

    Sprite m_start, m_body, m_end;
    float m_posX, m_posY;

    Sprite m_crack;
    Texture[] m_texture;
    boolean m_isCracked = false;
    float m_frameTime = 0;
    int m_currentFrame = 0;
    int m_numOfFrames = 5;

    public ObstacleBuilder(float x, float y, float width, float height, Color tint)
    {
        m_posX = x;
        m_posY = y;

        m_start = new Sprite(new Texture("flatbwleft.png"));
        m_body = new Sprite(new Texture("flatbwbody.png"));
        m_end = new Sprite(new Texture("flatbwright.png"));

        //m_start.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //m_body.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //m_end.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        m_start.setSize(height, height);
        m_end.setSize(height, height);
        m_body.setSize(width-(2*height),height);

        m_start.setPosition(x,y);
        m_body.setPosition(x+m_start.getWidth(),y);
        m_end.setPosition(m_body.getX()+m_body.getWidth(),y);

        Color c = new Color(0.25f,0.5f,0.75f,1);
        m_start.setColor(tint);
        m_body.setColor(tint);
        m_end.setColor(tint);

        m_texture = new Texture[m_numOfFrames];
        for(int i = 0; i < m_texture.length; i++)
        {
            m_texture[i] = new Texture("animation/bounce0"+(i+1)+".png");
        }
        m_crack = new Sprite(m_texture[0]);
        m_crack.setSize(32,32);
        m_crack.setColor(tint);

    }

    public void setCracked(Vector2 pos)
    {
        m_crack.setPosition(pos.x,pos.y);
        m_isCracked = true;
    }

    @Override
    public void update(float dt)
    {
        if(m_isCracked)
        {
            if(m_frameTime > 0.05 && m_currentFrame < m_numOfFrames-1)
            {
                m_currentFrame = (m_currentFrame+1);
                m_crack.setTexture(m_texture[m_currentFrame]);
                m_frameTime = 0;
            }
            m_frameTime += dt;

            if(m_currentFrame >= m_numOfFrames-1)
            {
                m_currentFrame = 0;
                m_isCracked = false;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_start.draw(sb);
        m_body.draw(sb);
        m_end.draw(sb);
        if(m_isCracked)
        {
            m_crack.draw(sb);
        }
    }

    @Override
    public void dispose()
    {
        m_crack.getTexture().dispose();
        for(Texture t : m_texture)
        {
            t.dispose();
        }
    }
}
