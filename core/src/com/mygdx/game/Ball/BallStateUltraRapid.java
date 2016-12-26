package com.mygdx.game.Ball;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.AssetLoader;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-19.
 */

public class BallStateUltraRapid extends BallState
{
    float m_timer;

    public BallStateUltraRapid()
    {

    }

    public BallStateUltraRapid(Ball b)
    {
        super(b);
        setupSprite();
        m_ball.setDtModifier(0.3f);
        m_timer = 10f;
        m_defaultGravityModifier = 1f;
        m_gravityModifier = 10f;
        m_onCollisionGravityModifier = 2.75f;
    }

    public void setBall(Ball b)
    {
        m_ball = b;
    }
    @Override
    protected boolean hasHistory()
    {
        return true;
    }
    @Override
    protected boolean hasOnCollision()
    {
        return true;
    }

    private void setupSprite()
    {
        m_sprite = new Sprite(AssetLoader.flatballgrey);
        m_sprite.setOriginCenter();
        m_sprite.setColor(0.6f,0.35f,0.7f,1);
    }

    @Override
    public void updateSprite(float x, float y)
    {
        m_sprite.setPosition(x, y);
    }

    @Override
    protected void setSpriteSize(float width , float height)
    {
        m_sprite.setSize(width, height);
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);

        m_timer -= dt;

        if(m_timer <= 0)
        {
            m_ball.resetState();
        }

    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    protected void playBounceSound(float velY)
    {
    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

    @Override
    public void dispose()
    {

    }

}
