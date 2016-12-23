package com.mygdx.game.Ball;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.AudioHandler;
import com.mygdx.game.Basics.Particles;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-19.
 */

public class BallStateSuperSpeed extends BallState
{

    float m_timer = 0f;
    Color m_tint = null;
    Particles m_particles = null;


    public BallStateSuperSpeed()
    {
    }

    public BallStateSuperSpeed(Ball b)
    {
        super(b);
        m_tint = new Color(1f,0.80f,0,1);
        setupSprite();
        m_ball.scaleMaxSpeed(1.5f);
        m_timer = 3f;
        m_particles = new Particles(AssetLoader.flatballgrey, 15, 32, 32);
        m_particles.setColor(m_tint);
        m_particles.setFade(0.2f, 0.01f);
        m_defaultGravityModifier = 2.5f;
        m_gravityModifier = 2.5f;
        m_onCollisionGravityModifier = 0.75f;
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
        m_sprite.setColor(m_tint);
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
        m_particles.update(dt);
        m_particles.setPosition( m_ball.getPosition() );
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_particles.render(sb);
        m_sprite.draw(sb);
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
        m_particles.dispose();
    }

}
