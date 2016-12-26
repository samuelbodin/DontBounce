package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.Basics.Config;
import com.mygdx.game.Basics.LevelData;

import java.util.ArrayList;

public abstract class Chapter
{

    protected ArrayList<LevelData> m_levels = null;
    protected ArrayList<TextureRegion> m_backgrounds = null;
    protected TextureRegion m_foreground = null;
    protected LevelDictionary m_ld = null;
    protected Color m_tint;
    protected int m_id = 0;

    public Chapter(int id)
    {
        m_ld = LevelDictionary.getInstance();
        m_id = id;
        m_levels = m_ld.getLevelsByChapter(m_id);
        m_backgrounds = new ArrayList<TextureRegion>();
        m_foreground = null;
    }

    public LevelData getLevel(int levelId)
    {
        for(LevelData ld : m_levels)
        {
            if(ld.m_levelId == levelId)
            {
                ld.m_backgroundFiles = m_backgrounds;
                ld.m_foregroundFile = m_foreground;
                ld.m_tint = m_tint;

                return ld;
            }
        }

        return null;
    }

    public TextureRegion getForeground()
    {
        return m_foreground;
    }


    public ArrayList<Integer> getLevelIds()
    {
        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (LevelData ld : m_levels)
        {
            tmp.add(ld.m_levelId);
        }

        return tmp;
    }

    boolean hasLevel(int levelId)
    {
        for(LevelData l : m_levels)
        {
            if(l.m_levelId == levelId)
            {
                return true;
            }
        }
        return false;
    }

    int getChapterId()
    {
        return m_id;
    }

    int getIdOfLastLevelInChapter()
    {
        return m_levels.get(m_levels.size() - 1).m_levelId;
    }


    public ArrayList<TextureRegion> getBackgrounds()
    {
        return m_backgrounds;
    }

}
