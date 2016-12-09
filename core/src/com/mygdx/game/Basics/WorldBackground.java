package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rickard on 2016-12-07.
 */

public class WorldBackground extends Drawable
{
    Sprite m_sprite;
    private float m_viewportWidth;
    private float m_viewportHeight;
    private float m_lagFactor = 0.997f;
    public WorldBackground(float viewportWidth, float viewportHeight )
    {
        m_viewportWidth = viewportWidth;
        m_viewportHeight = viewportHeight;
        m_sprite = new Sprite(new Texture("appbg.png"));

        // Make background a bit larger than viewport to allow sideways parallax
        m_sprite.setSize(m_viewportWidth*1.2f,m_viewportWidth*1.2f*(m_sprite.getHeight()/m_sprite.getWidth()));

        // Center on width and align background top with viewport top.
        m_sprite.setPosition((m_viewportWidth-m_sprite.getWidth())/2,(m_viewportHeight-m_sprite.getHeight()));
    }

    public void setPosition(Vector2 pos)
    {
        // Attach background to camera and scroll background up with a factor
        m_sprite.setPosition(m_sprite.getX(), m_sprite.getY() + (pos.y*m_lagFactor));
    }

    @Override
    public void update(float dt)
    {

    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }
}
