package com.mygdx.game.Basics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Pontus on 2016-12-07.
 */

public class AssetLoader
{
    private TextureAtlas m_gameElements;
    private TextureAtlas m_buttonElements;
    private Skin skin;

    public static TextureRegion ball;
    public static TextureRegion obstacleRed;
    public static TextureRegion obstacleWood;
    public static TextureRegion obstacleGrass;
    public static TextureRegion obstacleCyber;
    public static TextureRegion obstacleIce;

    public static TextureRegion btnPlay;

    public AssetLoader()
    {
        m_buttonElements = new TextureAtlas("buttonPack/buttons.pack");
        skin = new Skin();
        skin.addRegions(m_buttonElements);

        m_gameElements = new TextureAtlas("texturePack/gameElements.pack");
        loadTextures();
    }

    private void loadTextures()
    {
        ball = m_gameElements.findRegion("ball1");
        obstacleRed = m_gameElements.findRegion("obs1");
        obstacleWood = m_gameElements.findRegion("obs2");
        obstacleGrass = m_gameElements.findRegion("obs3");
        obstacleCyber = m_gameElements.findRegion("obs4");
        obstacleIce = m_gameElements.findRegion("obs5");
    }

    public Skin getSkin()
    {
        return skin;
    }

    public void dispose()
    {
        m_gameElements.dispose();
    }
}
