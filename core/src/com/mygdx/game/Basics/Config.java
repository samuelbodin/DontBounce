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
    private AudioHandler m_ah = null;

    public final int m_worldW = 720;
    public final int m_worldH = 1280;
    public int m_screenW = 0;
    public int m_screenH = 0;
    public float m_aspectR = 0.0f;
    private int m_returnCount = 0;

    public Preferences m_preferences = null;

    public Config()
    {
        m_screenH = Gdx.graphics.getHeight();
        m_screenW = Gdx.graphics.getWidth();
        m_aspectR = (m_screenH/m_screenW);

        m_lm = new LevelManager();
        m_ah = new AudioHandler(checkMutePreferences());
    }

    private boolean checkMutePreferences()
    {
        return false;
    }

    public AudioHandler getAudioHandler()
    {
        if(m_returnCount == 0)
        {
            m_returnCount ++;
            return m_ah;
        }
        else
        {
            return null;
        }
    }

    public LevelManager getLevelManager()
    {
        return m_lm;
    }
}
