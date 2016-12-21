package com.mygdx.game.Obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Basics.Drawable;

/**
 * Created by Rickard on 2016-12-10.
 */

public class ObstacleBuilder extends Drawable
{

    Sprite m_start, m_body, m_end;
    float m_posX, m_posY;
/*
    boolean m_isCracked = false;
    Vector2 m_collisionPos = null;
    TextureAtlas m_atlas;
    Animation m_animation;
    float m_elapsedTime = 0;*/

    public ObstacleBuilder(float x, float y, float width, float height, Color tint)
    {
        m_posX = x;
        m_posY = y;

        m_start = new Sprite(new Texture("flatbwleft.png"));
        m_body = new Sprite(new Texture("flatbwbody.png"));
        m_end = new Sprite(new Texture("flatbwright.png"));

        //m_start.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //m_body.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //m_end.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        m_start.setSize(height, height);
        m_end.setSize(height, height);
        m_body.setSize(width - (2 * height), height);

        m_start.setPosition(x, y);
        m_body.setPosition(x + m_start.getWidth(), y);
        m_end.setPosition(m_body.getX() + m_body.getWidth(), y);

        Color c = new Color(0.25f, 0.5f, 0.75f, 1);
        m_start.setColor(tint);
        m_body.setColor(tint);
        m_end.setColor(tint);

        /*m_atlas = new TextureAtlas(Gdx.files.internal("gameObjects/splash.pack"));
        Array<TextureAtlas.AtlasRegion> splashRegion = m_atlas.findRegions("splash");
        m_animation = new Animation(0.05f, splashRegion);*/
    }

/*    public void setCracked(Vector2 pos)
    {
        m_collisionPos = new Vector2(pos);
        m_isCracked = true;
    }*/

    @Override
    public void update(float dt)
    {
/*        if (m_isCracked)
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
        }*/
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_start.draw(sb);
        m_body.draw(sb);
        m_end.draw(sb);
/*        if (m_isCracked)
        {
            sb.draw(m_animation.getKeyFrame(m_elapsedTime), m_collisionPos.x, m_collisionPos.y);
        }*/
    }

    @Override
    public void dispose()
    {
        /*m_atlas.dispose();*/
    }

}
