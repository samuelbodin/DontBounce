package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TimeHandler
{

    private float m_timer;
    private boolean m_running;
    private Vector2 m_drawPosition;
    private BitmapFont m_font;

    public TimeHandler(float x, float y)
    {

        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));
        m_font.setColor(1,1,1,0.8f);
        m_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_font.getData().setScale(0.6f);

        m_drawPosition = new Vector2(x,y);
        m_timer = 0f;
        m_running = false;
    }

    public void start()
    {
        m_running = true;
    }

    public void stop()
    {
        m_running = false;
    }

    public boolean isRunning()
    {
        return m_running;
    }

    public float getTime()
    {
        return m_timer;
    }

    public String getTimeString()
    {
        return String.format("%.2f", m_timer);
    }

    public void render(SpriteBatch sb)
    {
        m_font.draw(sb, String.format("%.2f", m_timer), m_drawPosition.x, m_drawPosition.y);
    }

    public void updatePosition(Vector2 deltaMove)
    {
        m_drawPosition.y += deltaMove.y;
    }

    public void update(float dt)
    {
        if(m_running)
        {
            m_timer += dt;
        }
    }

    public void dispose()
    {
        m_font.dispose();
    }

}
