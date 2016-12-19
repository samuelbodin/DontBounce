package com.mygdx.game.Basics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Cam;

import java.util.Stack;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


public class WorldBackground extends Drawable
{
    private Stack<Sprite> m_sprite = null;
    private float m_viewportWidth;
    private float m_viewportHeight;
    private float m_lagFactor = 0.99f;

    private Sprite[] m_foreground;
    private Cam m_cam;
    private Texture m_texture = null;
    private boolean m_useForeground = false;

    public WorldBackground(float viewportWidth, float viewportHeight)
    {
        m_sprite = new Stack<Sprite>();
        m_viewportWidth = viewportWidth;
        m_viewportHeight = viewportHeight;

        for(Sprite s : m_foreground)
        {
            s.setSize(m_viewportWidth*1.2f,m_viewportWidth*1.2f*(s.getHeight()/s.getWidth()));
        }
    }

    public WorldBackground(float viewportWidth, float viewportHeight, Cam cam, boolean foreground)
    {
        m_cam = cam;
        m_sprite = new Stack<Sprite>();
        m_viewportWidth = viewportWidth;
        m_viewportHeight = viewportHeight;
        m_useForeground = foreground;
/*        if(m_useForeground)
        {
            m_texture = new Texture("flatbgforeground.png");


            m_foreground = new Sprite[2];
            m_foreground[0] = new Sprite(m_texture);
            m_foreground[1] = new Sprite(m_texture);
            for (Sprite s : m_foreground)
            {
                s.setSize(m_viewportWidth * 1.2f, m_viewportWidth * 1.2f * (s.getHeight() / s.getWidth()));
            }
            m_foreground[0].setPosition((m_viewportWidth - m_foreground[0].getWidth()) / 2, -m_cam.position.y);
            m_foreground[1].setPosition(m_foreground[0].getX(), m_foreground[0].getY() - m_foreground[1].getHeight());
        }*/
    }

    public void addBackgroundImage(String filename)
    {
        m_sprite.push( new Sprite(new Texture(filename)) );
        setSize();
        setPosition();
    }

    public void addForegroundImage(String filename)
    {
        m_texture = new Texture(filename);

        m_foreground = new Sprite[3];
        m_foreground[0] = new Sprite(m_texture);
        m_foreground[1] = new Sprite(m_texture);
        m_foreground[2] = new Sprite(m_texture);
        for (Sprite s : m_foreground)
        {
            s.setSize(m_viewportWidth * 1.2f, m_viewportWidth * 1.2f * (s.getHeight() / s.getWidth()));
        }
        m_foreground[0].setPosition((m_viewportWidth - m_foreground[0].getWidth()) / 2, -m_cam.position.y);
        m_foreground[1].setPosition(m_foreground[0].getX(), m_foreground[0].getY() - m_foreground[1].getHeight());
        m_foreground[2].setPosition(m_foreground[0].getX(), m_foreground[1].getY() - m_foreground[2].getHeight());
    }

    private void setSize()
    {
        for(Sprite s : m_sprite)
        {
            s.setSize(m_viewportWidth*1.2f,m_viewportWidth*1.2f*(s.getHeight()/s.getWidth()));
        }


    }

    private void setPosition()
    {
        int i = 0;
        for(Sprite s : m_sprite)
        {
            s.setPosition((m_viewportWidth-s.getWidth())/2,(m_viewportHeight-s.getHeight())-i*150);
            i++;
        }
    }


    public void setPosition(Vector2 pos)
    {
        // Attach background to camera and scroll background up with a factor
        int i = 0;
        for(Sprite s : m_sprite)
        {
            s.setPosition(s.getX(), s.getY() + pos.y*m_lagFactor );
            i++;
        }
    }

    @Override
    public void update(float dt)
    {
        if(m_useForeground)
        {
            for (int i = 0; i < m_foreground.length; i++)
            {
                m_foreground[i].setPosition(m_foreground[i].getX(), m_foreground[i].getY() + (m_cam.getDeltaPosition().y * 0.1f));

                if (m_foreground[i].getY()-m_foreground[i].getHeight() >= m_cam.position.y + (m_cam.viewportHeight) - (m_cam.viewportHeight / 4))
                {
                    m_foreground[i].setPosition(m_foreground[i].getX(), m_foreground[(i + 1) % m_foreground.length].getY() - (m_foreground[i].getHeight()*2));
                }

            }
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        for(Sprite s : m_sprite)
        {
            s.draw(sb);
        }
        if(m_useForeground)
        {
            for (Sprite s : m_foreground)
            {
                s.draw(sb);
            }
        }
    }

    @Override
    public void dispose()
    {
        for(Sprite s : m_sprite)
        {
            s.getTexture().dispose();
        }

        m_texture.dispose();

    }
}
