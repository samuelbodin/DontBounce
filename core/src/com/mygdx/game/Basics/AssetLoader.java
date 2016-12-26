package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.Gdx.audio;

public class AssetLoader
{
    //Atlases
    private static TextureAtlas m_gameObjects = null;
    private static TextureAtlas m_levelBackgrounds = null;
    private static TextureAtlas m_level2Backgrounds = null;
    private static TextureAtlas m_menuBackgrounds = null;
    private static TextureAtlas m_AnimationAtlas = null;

    //Skins
    public static Skin buttonSkin = null;

    //Fonts
    public static BitmapFont slackeyfont = null;

    // Colors
    public static Color white = null;

    //GameObjects
    public static TextureRegion goal = null;
    public static TextureRegion passthrough = null;
    public static TextureRegion speedup = null;
    public static TextureRegion ultrarapid = null;
    public static TextureRegion flatballgrey = null;
    public static TextureRegion flatbwbody = null;
    public static TextureRegion flatbwleft = null;
    public static TextureRegion flatbwright = null;

    // Animation
    public static Animation m_splashAnimation = null;
    public static Animation m_powerUpAnimation = null;
    public static Array<TextureAtlas.AtlasRegion> m_splashRegion = null;
    public static Array<TextureAtlas.AtlasRegion> m_powerUpRegion = null;

    //levelBackgrounds
    public static TextureRegion flatbg01 = null;
    public static TextureRegion flatbg02 = null;
    public static TextureRegion flatbg03 = null;
    public static TextureRegion flatbgforeground = null;

    public static TextureRegion spacebg01 = null;
    public static TextureRegion spacebg02 = null;
    public static TextureRegion spacebg03 = null;
    public static TextureRegion spacebgforeground = null;

    //MenuBackgrounds
    public static TextureRegion black = null;
    public static TextureRegion clouds01 = null;
    public static TextureRegion clouds02 = null;
    public static TextureRegion cloudsbg = null;

    //Music
    public static Music m_musicMenu = null;
    public static Music m_musicChapterOne = null;
    public static Music m_musicChapterTwo = null;


    //Sounds
    public static Sound[] m_bounceSounds =
        {
            Gdx.audio.newSound(Gdx.files.internal("sound/bounce01.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/bounce02.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/bounce03.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/bounce04.wav"))
        };
    public static Sound m_powerUp = Gdx.audio.newSound(Gdx.files.internal("sound/powerup.wav"));

    public static void Load()
    {
        Gdx.app.log("PW", "AssLoad Load");
        TextureAtlas m_buttonElements;

        m_buttonElements = new TextureAtlas("buttons/buttons.atlas");
        buttonSkin = new Skin();
        buttonSkin.addRegions(m_buttonElements);

        m_gameObjects = new TextureAtlas("gameObjects/gameObjects.pack");
        m_levelBackgrounds = new TextureAtlas("levelBackgrounds/levelBackgrounds.pack");
        m_level2Backgrounds = new TextureAtlas("levelBackgrounds/level2Backgrounds.pack");
        m_menuBackgrounds = new TextureAtlas("menuBackgrounds/menuBackgrounds.pack");

        m_musicMenu = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_musicChapterOne = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));
        m_musicChapterTwo = Gdx.audio.newMusic(Gdx.files.internal("sound/gamemusic.wav"));


        white = new Color(1, 1, 1, 0.8f);


        loadTextures();
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

        m_AnimationAtlas = new TextureAtlas(Gdx.files.internal("gameObjects/collisioneffects.pack"));
        m_splashRegion = m_AnimationAtlas.findRegions("splash");
        m_powerUpRegion = m_AnimationAtlas.findRegions("powerup");
        m_splashAnimation = new Animation(0.05f, m_splashRegion);
        m_powerUpAnimation = new Animation(0.05f, m_powerUpRegion);

        //levelBackgrounds
        flatbg01 = m_levelBackgrounds.findRegion("flatbg01");
        flatbg02 = m_levelBackgrounds.findRegion("flatbg02");
        flatbg03 = m_levelBackgrounds.findRegion("flatbg03");
        flatbgforeground = m_levelBackgrounds.findRegion("flatbgforeground");

        spacebg01 = m_level2Backgrounds.findRegion("spacebg01");
        spacebg02 = m_level2Backgrounds.findRegion("spacebg02");
        spacebg03 = m_level2Backgrounds.findRegion("spacebg03");
        spacebgforeground = m_level2Backgrounds.findRegion("spacebgforeground");

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

        m_musicMenu.dispose();
        m_musicChapterOne.dispose();
        m_musicChapterTwo.dispose();

        for(Sound s : m_bounceSounds)
        {
            s.dispose();
        }

        m_powerUp.dispose();
    }
}
