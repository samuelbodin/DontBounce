package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;
import com.mygdx.game.Basics.InputHandler;

import org.w3c.dom.ranges.Range;

import java.util.Random;


public class BallStateMoveable extends BallState
{

    Sound sound[] = null;

    public BallStateMoveable(Ball b)
    {
        super(b);
        setupSprite();
        m_ball.resetMaxSpeed();
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
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    protected void playBounceSound(float velY)
    {
        if(sound != null)
        {
            Random rnd = new Random();
            sound[rnd.nextInt(sound.length)].play(1.0f * (velY/700) + 0.2f, 1f * (velY/700) + 1f, 0);
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
