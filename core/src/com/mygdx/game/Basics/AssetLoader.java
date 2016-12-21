package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader
{
    private TextureAtlas m_gameObjects;
    private TextureAtlas m_levelBackgrounds;
    private TextureAtlas m_menuBackgrounds;

    public Skin buttonSkin;

    public TextureRegion ball;

    //GameObjects
    public TextureRegion goal;
    public TextureRegion passthrough;
    public TextureRegion speedup;
    public TextureRegion ultrarapid;
    public TextureRegion flatball;
    public TextureRegion flatballgrey;
    public TextureRegion flatbwbody;
    public TextureRegion flatbwleft;
    public TextureRegion flatbwright;

    //levelBackgrounds
    public TextureRegion flatbg01;
    public TextureRegion flatbg02;
    public TextureRegion flatbg03;
    public TextureRegion flatbgforeground;

    //MenuBackgrounds
    public TextureRegion black;
    public TextureRegion clouds01;
    public TextureRegion clouds02;
    public TextureRegion cloudsbg;

    public AssetLoader()
    {
        TextureAtlas m_buttonElements;

        m_buttonElements = new TextureAtlas("buttons/buttons.atlas");
        buttonSkin = new Skin();
        buttonSkin.addRegions(m_buttonElements);

        m_gameObjects = new TextureAtlas("gameObjects/gameObjects.pack");
        m_levelBackgrounds = new TextureAtlas("levelBackgrounds/levelBackgrounds.pack");
        m_menuBackgrounds = new TextureAtlas("menuBackgrounds/menuBackgrounds.pack");

        loadTextures();
    }

    private void loadTextures()
    {
        //GameObjects
        goal = m_gameObjects.findRegion("goal");
        passthrough = m_gameObjects.findRegion("passthrough");
        speedup = m_gameObjects.findRegion("speedup");
        ultrarapid = m_gameObjects.findRegion("ultrarapid");
        flatball = m_gameObjects.findRegion("flatball");
        flatballgrey = m_gameObjects.findRegion("flattballgrey");
        flatbwbody = m_gameObjects.findRegion("flatbwbody");
        flatbwleft = m_gameObjects.findRegion("flatbwleft");
        flatbwright = m_gameObjects.findRegion("flatbwright");

        //levelBackgrounds
        flatbg01 = m_levelBackgrounds.findRegion("flatbg01");
        flatbg02 = m_levelBackgrounds.findRegion("flatbg02");
        flatbg03 = m_levelBackgrounds.findRegion("flatbg03");
        flatbgforeground = m_levelBackgrounds.findRegion("flatbgforeground");

        //menuBackgrounds
        black = m_menuBackgrounds.findRegion("black");
        clouds01 = m_menuBackgrounds.findRegion("clouds01");
        clouds02 = m_menuBackgrounds.findRegion("clouds02");
        cloudsbg = m_menuBackgrounds.findRegion("cloudsbg");
    }

    public void dispose()
    {
        m_gameObjects.dispose();
        m_levelBackgrounds.dispose();
        m_menuBackgrounds.dispose();
    }
}
