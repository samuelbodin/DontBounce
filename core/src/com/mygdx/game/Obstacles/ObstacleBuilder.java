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
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.Config;
import com.mygdx.game.Basics.Drawable;

/**
 * Created by Rickard on 2016-12-10.
 */

public class ObstacleBuilder extends Drawable
{

    Sprite m_start, m_body, m_end;
    float m_posX, m_posY;

    public ObstacleBuilder(float x, float y, float width, float height, Color tint)
    {
        m_posX = x;
        m_posY = y;

        m_start = new Sprite(AssetLoader.flatbwleft);
        m_body = new Sprite(AssetLoader.flatbwbody);
        m_end = new Sprite(AssetLoader.flatbwright);

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



    }


    @Override
    public void update(float dt)
    {

    }

    public void update(float dt, float deltaX)
    {
        update(dt);
        m_posX += deltaX;
        m_start.setPosition(m_posX, m_posY);
        m_body.setPosition(m_start.getX() + m_start.getWidth(), m_posY);
        m_end.setPosition(m_body.getX() + m_body.getWidth(), m_posY);
    }

    public Vector2 getPosition()
    {
        return new Vector2(m_posX, m_posY);
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_start.draw(sb);
        m_body.draw(sb);
        m_end.draw(sb);
    }

    @Override
    public void dispose()
    {

    }

}
