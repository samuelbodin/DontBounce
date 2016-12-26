package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.Particles;

public class BallStateSuperSpeed extends BallState
{

    private float m_timer = 0f;
    private Color m_tint = null;
    private Particles m_particles = null;

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
        m_onCollisionGravityModifier = 2.75f;
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
    public void dispose()
    {
        m_particles.dispose();
    }

}
