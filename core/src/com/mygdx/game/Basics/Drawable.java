package com.mygdx.game.Basics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Drawable
{
    protected int m_zIndex = 0;
    protected Texture m_texture = null;

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);

    @Override
    public String toString()
    {
        String str = "";
        str += "m_zIndex: " + m_zIndex + "\n";
        str += "m_texture: " + m_texture.toString() + "\n";

        return str;
    }
}
