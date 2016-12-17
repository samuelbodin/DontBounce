package com.mygdx.game.levels;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.Basics.Config;
import com.mygdx.game.Basics.LevelData;

import java.util.ArrayList;

public abstract class Chapter
{

    protected ArrayList<LevelData> m_levels = null;
    protected ArrayList<String> m_backgrounds = null;
    protected String m_foreground = null;
    protected LevelDictionary m_ld = null;
    protected int m_id = 0;

    public Chapter(int id)
    {
        m_ld = LevelDictionary.getInstance();
        m_id = id;
        m_levels = m_ld.getLevelsByChapter(m_id);
        m_backgrounds = new ArrayList<String>();
        m_foreground = "";
    }

    public LevelData getLevel(int levelId)
    {
        for(LevelData ld : m_levels)
        {
            if(ld.m_levelId == levelId)
            {
                ld.m_backgroundFiles = m_backgrounds;
                ld.m_foregroundFile = m_foreground;

                return ld;
            }
        }

        return null;
    }

    public String getForeground()
    {
        return m_foreground;
    }


    public ArrayList<Integer> getLevels()
    {
        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (LevelData ld : m_levels)
        {
            tmp.add(ld.m_levelId);
        }

        return tmp;
    }


    public ArrayList<String> getBackgrounds()
    {
        return m_backgrounds;
    }

}