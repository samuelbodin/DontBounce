package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TimeHandler
{

    private float m_timer;
    private boolean m_running;
    private Vector2 m_drawPosition;
    private BitmapFont m_font;
    private Texture m_bgTexture;
    private Sprite m_background;
    private Vector2 m_bgOffset;

    public TimeHandler(float x, float y)
    {

        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));
        m_font.setColor(1,1,1,0.8f);
        m_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_font.getData().setScale(0.6f);

        m_drawPosition = new Vector2(x,y);
        m_timer = 0f;
        m_running = false;

        m_bgOffset = new Vector2(-200,-90);
        m_bgTexture = new Texture("gameObjects/timebg.png");
        m_background = new Sprite(m_bgTexture);
        m_background.setAlpha(0.3f);
        m_background.setPosition(x+m_bgOffset.x,y+m_bgOffset.y);
        m_background.setOriginCenter();
        m_background.setScale(0.6f);

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
        m_background.draw(sb);
        if(m_timer < 10)
        {
            m_font.draw(sb, String.format("%.2f", m_timer), m_drawPosition.x, m_drawPosition.y);
        }
        else
        {
            m_font.draw(sb, String.format("%.1f", m_timer), m_drawPosition.x, m_drawPosition.y);
        }
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

        m_background.setPosition(m_drawPosition.x+m_bgOffset.x, m_drawPosition.y+m_bgOffset.y);
    }

    public void dispose()
    {
        m_font.dispose();
    }

}
