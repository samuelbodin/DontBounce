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
    private float m_lagFactor = 0.995f;
    public WorldBackground(float viewportWidth, float viewportHeight )
    {
        m_viewportWidth = viewportWidth*1.2f;
        m_viewportHeight = viewportHeight;
        m_sprite = new Sprite(new Texture("appbg.png"));
        m_sprite.setSize(m_viewportWidth,m_viewportWidth*(m_sprite.getHeight()/m_sprite.getWidth()));
        m_sprite.setPosition(-m_sprite.getWidth()/2, -(m_sprite.getHeight()/2));
    }

    public void setPosition(Vector2 pos)
    {
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
