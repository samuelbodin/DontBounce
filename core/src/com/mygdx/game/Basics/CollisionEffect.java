package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Rickard on 2016-12-21.
 */

public class CollisionEffect extends Drawable
{
    boolean m_isCracked = false;
    Vector2 m_Pos = null;
    TextureAtlas m_atlas = null;
    Animation m_animation;
    Array<TextureAtlas.AtlasRegion> m_region;
    float m_elapsedTime = 0;
    int m_spriteMove = 0;

    public CollisionEffect()
    {
        m_atlas = new TextureAtlas(Gdx.files.internal("gameObjects/collisioneffects.pack"));
        m_region = m_atlas.findRegions("splash");
        m_animation = new Animation(0.05f, m_region);
    }

    @Override
    public void update(float dt)
    {
        if (m_isCracked)
        {
            if(m_animation.isAnimationFinished(m_elapsedTime))
            {
                m_elapsedTime = 0;
                m_isCracked = false;
            }
            else
            {
                m_Pos.y -= m_spriteMove*dt;
                m_elapsedTime += dt;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        if (m_isCracked)
        {
            sb.draw(m_animation.getKeyFrame(m_elapsedTime), m_Pos.x, m_Pos.y);
        }

    }

    public void startEffect(Vector2 pos, int side)
    {
        startEffect(pos, side, 0);
    }

    public void startEffect(Vector2 pos, int side, int spriteMove)
    {
        m_Pos = new Vector2(pos);
        m_spriteMove = spriteMove;
        m_isCracked = true;
    }

    public void setAnimation(String name)
    {
        if(m_atlas.findRegions(name) != null)
        {
            m_region = m_atlas.findRegions(name);
            m_animation = new Animation(0.05f, m_region);
        }
    }

    @Override
    public void dispose()
    {
        m_atlas.dispose();
    }
}
