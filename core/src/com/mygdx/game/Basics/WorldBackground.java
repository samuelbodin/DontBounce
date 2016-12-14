package com.mygdx.game.Basics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Cam;

import java.util.Stack;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


/**
 * Created by Rickard on 2016-12-07.
 */

public class WorldBackground extends Drawable
{
    private Stack<Sprite> m_sprite = null;
    private float m_viewportWidth;
    private float m_viewportHeight;
    private float m_lagFactor = 0.99f;

    private Sprite[] m_foreground;
    private Cam m_cam;
    private Texture m_texture[];
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
        if(m_useForeground)
        {
            m_texture = new Texture[3];
            m_texture[0] = new Texture("skyscraper3.png");
            m_texture[1] = new Texture("skyscraper3d.png");
            m_texture[2] = new Texture("skyscraper3e.png");

            m_foreground = new Sprite[2];
            m_foreground[0] = new Sprite(m_texture[0]);
            m_foreground[1] = new Sprite(m_texture[0]);
            for (Sprite s : m_foreground)
            {
                s.setSize(m_viewportWidth * 1.2f, m_viewportWidth * 1.2f * (s.getHeight() / s.getWidth()));
            }
            m_foreground[0].setPosition((m_viewportWidth - m_foreground[0].getWidth()) / 2, -m_cam.position.y);
            m_foreground[1].setPosition(m_foreground[0].getX(), m_foreground[0].getY() - m_foreground[1].getHeight());
        }
    }

    public void addFile(String filename)
    {
        m_sprite.push( new Sprite(new Texture(filename)) );
        setSize();
        setPosition();
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
        for(Sprite s : m_sprite)
        {
            s.setPosition((m_viewportWidth-s.getWidth())/2,(m_viewportHeight-s.getHeight()));
        }
    }


    public void setPosition(Vector2 pos)
    {
        // Attach background to camera and scroll background up with a factor
        int i = 0;
        for(Sprite s : m_sprite)
        {
            s.setPosition(s.getX(), s.getY() + (pos.y * (m_lagFactor+(i/10)) ));
            i++;
        }
    }

    @Override
    public void update(float dt)
    {
        //Gdx.app.log("Camera ", Float.toString(m_cam.position.y));

        if(m_useForeground)
        {
            for (int i = 0; i < m_foreground.length; i++)
            {
                m_foreground[i].setPosition(m_foreground[i].getX(), m_foreground[i].getY() + (m_cam.getDeltaPosition().y * 0.1f));
                //Gdx.app.log("Spr ", Float.toString(m_foreground[i].getY()));

                if (m_foreground[i].getY() >= m_cam.position.y + (m_cam.viewportHeight) - (m_cam.viewportHeight / 4))
                {
                    //int val = (int)Math.ceil(m_cam.position.y/s.getHeight());

                    //float setToPos = (float)(val-2)*s.getHeight();
                    m_foreground[i].setPosition(m_foreground[i].getX(), m_foreground[(i + 1) % m_foreground.length].getY() - (m_foreground[i].getHeight()));
                    //Gdx.app.log("Cam ", Float.toString(m_cam.position.y));
                    //Gdx.app.log("Spr ", Float.toString(s.getY()));
                    //Gdx.app.log("Mod ", Float.toString(val));
                    //Gdx.app.log("Pos ", Float.toString(setToPos));
                }
            }


            if (Math.abs(m_cam.getDeltaPosition().y) > 10)
            {
                for (int i = 0; i < m_foreground.length; i++)
                {
                    m_foreground[i].setTexture(m_texture[2]);
                }

            } else
            {
                for (int i = 0; i < m_foreground.length; i++)
                {
                    m_foreground[i].setTexture(m_texture[0]);
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

    }
}
