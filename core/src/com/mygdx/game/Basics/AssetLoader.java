package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader
{
    private TextureAtlas m_gameElements;
    private TextureAtlas m_buttonElements;

    public Skin buttonSkin;

    public TextureRegion ball;

    public AssetLoader()
    {
        Gdx.app.log("PW", "AS constructor");
        m_buttonElements = new TextureAtlas("buttons/buttons.atlas");
        buttonSkin = new Skin();
        buttonSkin.addRegions(m_buttonElements);

        //m_gameElements = new TextureAtlas("texturePack/gameElements.pack");
        loadTextures();
    }

    private void loadTextures()
    {
       //ball = m_gameElements.findRegion("ball1");
    }

    public Skin getButtonSkin()
    {
        return buttonSkin;
    }

    public void dispose()
    {
        m_gameElements.dispose();
    }
}
