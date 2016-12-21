package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Rickard on 2016-12-20.
 */

public class MenuBackground implements Disposable
{

    Array<Sprite> m_sprites;
    TextureRegion[] m_texture;
    float m_worldW, m_worldH;

    public MenuBackground(TextureRegion[] texture, float worldW, float worldH)
    {
        m_worldW = worldW;
        m_worldH = worldH;
        m_texture = texture;

        m_sprites = new Array<Sprite>();

        for(int i = 0; i < texture.length; i++)
        {
            m_sprites.add(new Sprite(texture[i]));
            m_sprites.peek().setScale(m_worldW/m_sprites.peek().getWidth()+0.1f);
            m_sprites.peek().setOrigin(-m_worldW/4,(m_sprites.peek().getY()-m_worldW));
            if(i != 0)
            {
                m_sprites.add(new Sprite(texture[i]));
                m_sprites.peek().setScale(m_worldW/m_sprites.peek().getWidth());
                m_sprites.peek().setOrigin(m_worldW/2,(m_sprites.peek().getY()-m_worldW));
            }
        }
    }

    public void update(float dt)
    {
        int i = 0;
        for(Sprite s : m_sprites)
        {
            s.setPosition(s.getX()-(dt*3*(i/2)),s.getY());
            if((s.getWidth()/2)-Math.abs(s.getX())<0)
            {
                s.setPosition(m_worldW,(m_sprites.peek().getY()-m_worldW));
            }
            i++;
        }
    }

    public void render(Batch sb)
    {
        for(Sprite s : m_sprites)
        {
            s.draw(sb);
        }

    }

    @Override
    public void dispose()
    {
        //Denna dispose kastar alla menuBackgrounds från assetloader :(//PW
        /*for(Sprite s : m_sprites)
        {
            s.getTexture().dispose();
            Gdx.app.log("PW", "MB disp");
        }*/
    }
}
