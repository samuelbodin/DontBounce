package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class AssetLoader
{
    //Atlases
    private static TextureAtlas m_gameObjects;
    private static TextureAtlas m_levelBackgrounds;
    private static TextureAtlas m_menuBackgrounds;

    //Skins
    public static Skin buttonSkin;

    //Fonts
    public static BitmapFont slackeyfont;

    //GameObjects
    public static TextureRegion goal;
    public static TextureRegion passthrough;
    public static TextureRegion speedup;
    public static TextureRegion ultrarapid;
    public static TextureRegion flatballgrey;
    public static TextureRegion flatbwbody;
    public static TextureRegion flatbwleft;
    public static TextureRegion flatbwright;

    //levelBackgrounds
    public static TextureRegion flatbg01;
    public static TextureRegion flatbg02;
    public static TextureRegion flatbg03;
    public static TextureRegion flatbgforeground;

    //MenuBackgrounds
    public static TextureRegion black;
    public static TextureRegion clouds01;
    public static TextureRegion clouds02;
    public static TextureRegion cloudsbg;

    public static AudioHandler audio;

    public static void Load()
    {
        Gdx.app.log("PW", "AssLoad Load");
        TextureAtlas m_buttonElements;

        m_buttonElements = new TextureAtlas("buttons/buttons.atlas");
        buttonSkin = new Skin();
        buttonSkin.addRegions(m_buttonElements);

        m_gameObjects = new TextureAtlas("gameObjects/gameObjects.pack");
        m_levelBackgrounds = new TextureAtlas("levelBackgrounds/levelBackgrounds.pack");
        m_menuBackgrounds = new TextureAtlas("menuBackgrounds/menuBackgrounds.pack");

        loadTextures();

        audio.load();
    }

    private static void loadTextures()
    {
        //Fonts
        slackeyfont = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));

        //GameObjects
        goal = m_gameObjects.findRegion("goal");
        passthrough = m_gameObjects.findRegion("passthrough");
        speedup = m_gameObjects.findRegion("speedup");
        ultrarapid = m_gameObjects.findRegion("ultrarapid");
        flatballgrey = m_gameObjects.findRegion("flatballgrey");
        flatbwbody = m_gameObjects.findRegion("flatbwbody");
        flatbwleft = m_gameObjects.findRegion("flatbwleft");
        flatbwright = m_gameObjects.findRegion("flatbwright");

        //levelBackgrounds
        flatbg01 = m_levelBackgrounds.findRegion("flatbg01");
        flatbg02 = m_levelBackgrounds.findRegion("flatbg02");
        flatbg03 = m_levelBackgrounds.findRegion("flatbg03");
        flatbgforeground = m_levelBackgrounds.findRegion("flatbgforeground");

        //menuBackgrounds
        black = m_menuBackgrounds.findRegion("blackbg");
        clouds01 = m_menuBackgrounds.findRegion("clouds01");
        clouds02 = m_menuBackgrounds.findRegion("clouds02");
        cloudsbg = m_menuBackgrounds.findRegion("cloudsbg");
    }

    public static void dispose()
    {
        slackeyfont.dispose();
        m_gameObjects.dispose();
        m_levelBackgrounds.dispose();
        m_menuBackgrounds.dispose();
    }
}
