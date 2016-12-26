package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.levels.LevelManager;


public class Config
{
    private LevelManager m_lm = null;
    private AudioHandler m_ah = null;

    public final int m_worldW = 720;
    public final int m_worldH = 1280;
    public int m_screenW = 0;
    public int m_screenH = 0;
    private int m_returnCount = 0;

    public Config()
    {
        m_screenH = Gdx.graphics.getHeight();
        m_screenW = Gdx.graphics.getWidth();

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
