package com.mygdx.game.Basics;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;


/**
 * Created by Rickard on 2016-12-07.
 */

public class WorldBackground extends Drawable
{
    private Stack<Sprite> m_sprite = null;
    private float m_viewportWidth;
    private float m_viewportHeight;
    private float m_lagFactor = 0.99f;
    public WorldBackground(float viewportWidth, float viewportHeight )
    {
        m_sprite = new Stack<Sprite>();
        m_viewportWidth = viewportWidth;
        m_viewportHeight = viewportHeight;

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

    }

    @Override
    public void render(SpriteBatch sb)
    {
        for(Sprite s : m_sprite)
        {
            s.draw(sb);
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
