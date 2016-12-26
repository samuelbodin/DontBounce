package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.AssetLoader;

class BallStateNormal extends BallState
{
    BallStateNormal(Ball b)
    {
        super(b);
        setupSprite();
        m_defaultGravityModifier = 1.5f;
        m_gravityModifier = 1.5f;
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
        m_sprite.setColor(0.75f,0.2f,0.15f,1);
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
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    public void dispose()
    {

    }
}
