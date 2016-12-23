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
    private int m_unlockedChapter = 1;

    private int m_currentChapter = 1;


    public LevelManager()
    {
        m_preferences = Gdx.app.getPreferences("dontbounce-preferences");
        m_preferences.clear();

        m_chapters = new ArrayList<Chapter>();
        m_chapters.add(new ChapterOne());
        m_chapters.add(new ChapterTwo());

        m_unlockedLevel = m_preferences.getInteger("unlocked_level", 1);
        m_unlockedChapter = m_preferences.getInteger("unlocked_chapter", 1);
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


    public LevelData getLastUnlockedLevel()
    {
        return getLevel(m_unlockedLevel);
    }

    public int getIdOfLastUnlockedLevel()
    {
        return getLevel(m_unlockedLevel).m_levelId;
    }

    public boolean isLastLevel()
    {
        ArrayList<Integer> levels = m_chapters.get(m_unlockedChapter - 1).getLevelIds();

        return (m_unlockedLevel) == levels.get(levels.size() - 1);
    }

    public LevelData getNextLevel()
    {
        if(isLastLevel())
        {
            if(hasNextChapter())
            {
                Chapter c = m_chapters.get(m_unlockedChapter);

                return c.getLevel(c.getLevelIds().get(0));
            }
        }
        else
        {
            return m_chapters.get(m_unlockedChapter).getLevel(m_unlockedLevel);
        }

        return null;
    }

    public boolean unlockNextLevel(LevelData level)
    {
        m_preferences.flush();

        // Move to next chapter
        if(isLastLevel() && hasNextChapter())
        {
            m_unlockedChapter++;
            m_preferences.putInteger("unlocked_chapter", m_unlockedChapter);

            m_unlockedLevel++;
            m_preferences.putInteger("unlocked_level", m_unlockedLevel);

            return true;
        }
        else if((level.m_levelId == m_unlockedLevel) && !isLastLevel())
        {
            // Move to next level
            m_unlockedLevel++;
            m_preferences.putInteger("unlocked_level", m_unlockedLevel);

            return true;
        }
        else if(isLastLevel() && !hasNextChapter())
        {
            return false;
        }

        return true;
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

    public boolean hasNextChapter()
    {
        return (m_unlockedChapter) < m_chapters.size();
    }

    public ArrayList<Chapter> getChapters()
    {
        return m_chapters;
    }


    public boolean levelWasCompleted(TimeHandler th, LevelData level)
    {
        return true;
        //return (th.getTime() <= Math.ceil(level.getLevelTime()));
    }

}
