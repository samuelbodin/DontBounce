package com.mygdx.game.Ball;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-19.
 */

public class BallStateUltraRapid extends BallState
{

    float m_timer;
    Sound m_sound[] = null;

    public BallStateUltraRapid()
    {

    }

    public BallStateUltraRapid(Ball b)
    {
        super(b);
        setupSprite();
        m_sound = setupSound();
        m_ball.setDtModifier(0.5f);
        m_timer = 5f;
        m_ball.collisionEffect(m_ball.getPosition(), 1, "powerup", 250);
    }

    public void setBall(Ball b)
    {
        m_ball = b;
    }

    @Override
    protected boolean hasOnCollision()
    {
        return false;
    }

    private void setupSprite()
    {
        m_sprite = new Sprite(m_ball.getBallTexture());
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
        m_ball.applyGravity();

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
        if(m_sound != null)
        {
            Random rnd = new Random();
            m_sound[rnd.nextInt(m_sound.length)].play(1.0f * (velY/700) + 0.2f, 1f * (velY/700) + 1f, 0);
        }
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
        m_texture.dispose();
    }

}
