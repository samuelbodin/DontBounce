package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.Drawable;

/**
 * Created by Rickard on 2016-12-10.
 */

public class ObstacleBuilder extends Drawable
{

    Sprite m_start, m_body, m_end;
    float m_posX, m_posY;

    public ObstacleBuilder(float x, float y, float width, float height)
    {
        m_posX = x;
        m_posY = y;

        m_start = new Sprite(new Texture("flatobleft.png"));
        m_body = new Sprite(new Texture("flatobbody.png"));
        m_end = new Sprite(new Texture("flatobright.png"));

        m_start.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_body.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_end.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        m_start.setSize(height, height);
        m_end.setSize(height, height);
        m_body.setSize(width-(2*height),height);

        m_start.setPosition(x,y);
        m_body.setPosition(x+m_start.getWidth(),y);
        m_end.setPosition(m_body.getX()+m_body.getWidth(),y);

        //Color c = new Color(1,0.6f,0.2f,1);
        //m_start.setColor(c);
        //m_body.setColor(c);
        //m_end.setColor(c);

    }

    @Override
    public void update(float dt)
    {

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
