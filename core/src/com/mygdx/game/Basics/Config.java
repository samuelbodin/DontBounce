package com.mygdx.game.Basics;

import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;

import java.util.ArrayList;

public class Config
{

    private ArrayList<Chapter> m_chapters;
    private static Config m_instance = null;


    private Config()
    {
        m_chapters.add(new ChapterOne());
    }

    private static Config getInstance()
    {
        if(m_instance == null)
        {
            m_instance = new Config();
        }

        return m_instance;
    }


    public LevelData getLevel(int level)
    {
        for (int i = 0; i < m_chapters.size(); i++)
        {
            Chapter chapter = m_chapters.get(i);

            for(Integer j : chapter.getLevels())
            {
                if(level == j)
                {
                    return chapter.getLevel(level);
                }
            }
        }

        return null;
    }
}
