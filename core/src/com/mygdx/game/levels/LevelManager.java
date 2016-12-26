package com.mygdx.game.levels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.TimeHandler;

import java.util.ArrayList;

public class LevelManager
{
    private Preferences m_preferences = null;
    private ArrayList<Chapter> m_chapters = null;
    private int m_unlockedLevel = 1;
    private int m_currentChapter = 1;


    public LevelManager()
    {
        m_preferences = Gdx.app.getPreferences("dontbounce-preferences");
        m_preferences.clear();

        m_chapters = new ArrayList<Chapter>();
        m_chapters.add(new ChapterOne());
        m_chapters.add(new ChapterTwo());

        m_unlockedLevel = m_preferences.getInteger("unlocked_level", 1);
        m_currentChapter = getIdOfLastUnlockedChapter();

    }

    public LevelData getLevel(int level)
    {
        for (int i = 0; i < m_chapters.size(); i++)
        {
            Chapter chapter = m_chapters.get(i);

            for(Integer j : chapter.getLevelIds())
            {
                if(j == level)
                {
                    return chapter.getLevel(level);
                }
            }
        }

        return null;
    }

    private int getIdOfLastLevel()
    {
        return m_chapters.get( m_chapters.size() - 1 ).getIdOfLastLevelInChapter();
    }

    private int getIdOfLastUnlockedChapter()
    {
        int id = 1;

        for(Chapter c : m_chapters)
        {
            if(c.hasLevel(m_unlockedLevel))
            {
                id = c.getChapterId();
                break;
            }
        }

        return id;
    }


    public LevelData getLastUnlockedLevel()
    {
        return getLevel(m_unlockedLevel);
    }

    public int getIdOfLastUnlockedLevel()
    {
        return m_unlockedLevel;
    }

    public LevelData getNextLevel(LevelData ld)
    {
        return getLevel(ld.m_levelId + 1);
    }

    public boolean isLastLevel(LevelData ld)
    {
        return ld.m_levelId == getIdOfLastLevel();
    }

    public boolean unlockNextLevel(LevelData level, boolean wasCompleted)
    {
        if(level.m_levelId < m_unlockedLevel)
        {
            return true;
        }
        else if(level.m_levelId == m_unlockedLevel && wasCompleted)
        {
            m_unlockedLevel++;
            m_preferences.putInteger("unlocked_level", m_unlockedLevel);
            m_preferences.flush();

            return true;
        }
        return false;
    }

    public Chapter getNextChapter()
    {
        return m_chapters.get(incrementChapter());
    }

    public Chapter getPreviousChapter()
    {
        return m_chapters.get(decrementChapter());
    }

    private int incrementChapter()
    {
       m_currentChapter++;

        if(m_currentChapter > m_chapters.size())
        {
            m_currentChapter = 1;
        }

        return m_currentChapter - 1;
    }

    private int decrementChapter()
    {
        m_currentChapter--;

        if(m_currentChapter <= 0)
        {
            m_currentChapter = m_chapters.size();
        }

        return m_currentChapter - 1;
    }

    public Chapter getCurrentChapter()
    {
        return m_chapters.get(m_currentChapter - 1);
    }

    public int getCurrentChapterId()
    {
        return m_currentChapter;
    }

    public boolean levelWasCompleted(TimeHandler th, LevelData level)
    {
        return true;
        //return (th.getTime() <= Math.ceil(level.getLevelTime()));
    }

}
