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
    TextureAtlas m_atlas;
    Animation m_animation;
    float m_elapsedTime = 0;

    public CollisionEffect()
    {
        m_atlas = new TextureAtlas(Gdx.files.internal("gameObjects/splash.pack"));
        Array<TextureAtlas.AtlasRegion> splashRegion = m_atlas.findRegions("splash");
        m_animation = new Animation(0.05f, splashRegion);
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
                m_elapsedTime += dt;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        if (m_isCracked)
        {
            Gdx.app.log("RL", "CRACK!");
            sb.draw(m_animation.getKeyFrame(m_elapsedTime), m_Pos.x, m_Pos.y);
        }

    }

    public void setCracked(Vector2 pos, int side)
    {
        m_Pos = new Vector2(pos);
        m_isCracked = true;

    }

    @Override
    public void dispose()
    {
        m_atlas.dispose();
    }
}
