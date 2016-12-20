package com.mygdx.game.Ball;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-19.
 */

public class BallStateSuperSpeed extends BallState
{

    float m_timer;
    Sound m_sound[] = null;

    public BallStateSuperSpeed()
    {

    }

    public BallStateSuperSpeed(Ball b)
    {
        super(b);
        setupSprite();
        m_sound = setupSound();
        m_ball.setMaxSpeed(-3000f);
        m_timer = 3f;

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
        m_texture = new Texture("flatball.png");
        m_sprite = new Sprite(m_texture);
        m_sprite.setColor(0.5f,0.8f,0.8f,1);
        m_sprite.setOriginCenter();
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

        m_sprite.setColor(0.7f,0.9f,0.5f,1);
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
