package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;

import java.util.ArrayList;

public class Config
{

    private ArrayList<Chapter> m_chapters = null;
    public int m_currentLevel = 0;
    public int m_currentChapter = 0;

    public final int m_worldW = 720;
    public final int m_worldH = 1280;
    public int m_screenW = 0;
    public int m_screenH = 0;
    public float m_aspectR = 0.0f;

    public Preferences m_preferences = null;

    public Config(AssetLoader assetLoader)
    {
        m_preferences = Gdx.app.getPreferences("dontbounce-preferences");
        m_preferences.clear();

        m_chapters = new ArrayList<Chapter>();
        m_chapters.add(new ChapterOne(assetLoader));

        m_screenH = Gdx.graphics.getHeight();
        m_screenW = Gdx.graphics.getWidth();
        m_aspectR = (m_screenH/m_screenW);

        m_currentLevel = m_preferences.getInteger("current_level", 0);
        m_currentChapter = m_preferences.getInteger("current_chapter", 0);
    }


    public LevelData getLevel(int level)
    {
        for (int i = 0; i < m_chapters.size(); i++)
        {
            Chapter chapter = m_chapters.get(i);

            for(Integer j : chapter.getLevels())
            {
                if(j == level)
                {
                    return chapter.getLevel(level);
                }
            }
        }

        return null;
    }

    public LevelData getCurrentLevel()
    {
        return getLevel(m_currentLevel + 1);
    }

    public boolean isLastLevel()
    {
        ArrayList<Integer> levels = m_chapters.get(m_currentChapter).getLevels();

        return (m_currentLevel + 1) == levels.get(levels.size() - 1);
    }

    public boolean isLastLevel(int chapter)
    {
        ArrayList<Integer> levels = m_chapters.get(chapter).getLevels();

        return (m_currentLevel + 1) == levels.get(levels.size() - 1);
    }

    public LevelData getNextLevel()
    {
        if(isLastLevel())
        {
            if(hasNextChapter())
            {
                Chapter c = m_chapters.get(m_currentChapter + 1);

                return c.getLevel(c.getLevels().get(0));
            }
        }
        else
        {
            return m_chapters.get(m_currentChapter).getLevel(m_currentLevel + 1);
        }

        return null;
    }

    public boolean hasNextChapter()
    {
        return (m_currentChapter + 1) < m_chapters.size();
    }

    public boolean levelComplete(float time)
    {
        return true;
    }
}
