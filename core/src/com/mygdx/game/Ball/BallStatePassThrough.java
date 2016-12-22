package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.AssetLoader;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-19.
 */

public class BallStatePassThrough extends BallState
{

    float m_timer;
    Sound m_sound[] = null;
    float m_soundPlayed = 0f;

    public BallStatePassThrough()
    {

    }

    public BallStatePassThrough(Ball b)
    {
        super(b);
        setupSprite();
        m_sound = setupSound();
        m_timer = 4f;
        m_ball.collisionEffect(m_ball.getPosition(), 1, "powerup", 450);
        AssetLoader.audio.musicLevelSpeedStart();
    }

    public void setBall(Ball b)
    {
        m_ball = b;
    }

    @Override
    protected boolean hasHistory()
    {
        return false;
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
        m_sprite.setColor(0.2f,0.8f,0.45f,1);
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

        m_soundPlayed += dt;
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    protected void playBounceSound(float velY)
    {
        if(m_sound != null && m_soundPlayed > 0.9f)
        {
            m_soundPlayed = 0;
            Random rnd = new Random();
            m_sound[rnd.nextInt(m_sound.length)].play(1.0f * (velY/700) + 0.2f, 1f * (velY/700) + 1f, 0);
        }
    }

    @Override
    public void onCollision(Vector2 pos, int side)
    {
        playBounceSound(m_ball.getVelocity().y);
    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

    @Override
    Sound[] setupSound()
    {
        Sound[] sound = new Sound[3];
        sound[0] = Gdx.audio.newSound(Gdx.files.internal("sound/passthrough01.wav"));
        sound[1] = Gdx.audio.newSound(Gdx.files.internal("sound/passthrough02.wav"));
        sound[2] = Gdx.audio.newSound(Gdx.files.internal("sound/passthrough03.wav"));
        return sound;
    }


    @Override
    public void dispose()
    {

    }
}
