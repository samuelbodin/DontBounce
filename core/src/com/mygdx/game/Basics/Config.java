package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;
import com.mygdx.game.levels.ChapterTwo;
import com.mygdx.game.levels.LevelManager;

import java.util.ArrayList;

public class Config
{
    private LevelManager m_lm = null;

    public final int m_worldW = 720;
    public final int m_worldH = 1280;
    public int m_screenW = 0;
    public int m_screenH = 0;
    public float m_aspectR = 0.0f;

    public Preferences m_preferences = null;

    public Config()
    {

        m_screenH = Gdx.graphics.getHeight();
        m_screenW = Gdx.graphics.getWidth();
        m_aspectR = (m_screenH/m_screenW);

        m_lm = new LevelManager();
    }


    public LevelManager getLevelManager()
    {
        return m_lm;
    }

}
