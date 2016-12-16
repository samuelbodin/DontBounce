package com.mygdx.game.levels;


import com.mygdx.game.Basics.LevelData;
import java.util.ArrayList;

public class LevelDictionary
{
    private ArrayList<LevelData> m_data = null;
    private static LevelDictionary m_instance = null;

    private LevelDictionary()
    {
        m_data = new ArrayList<LevelData>();
       loadChapterOne();

    }

    public static LevelDictionary getInstance()
    {
        if(m_instance == null)
        {
            m_instance = new LevelDictionary();
        }

        return m_instance;
    }

    public ArrayList<LevelData> getLevelsByChapter(int chapterId)
    {
        ArrayList<LevelData> levels = new ArrayList<LevelData>();

        for(LevelData ld : m_data)
        {
            if(ld.m_chapterId == chapterId)
            {
                levels.add(ld);
            }
        }
        return levels;
    }


    private void loadChapterOne()
    {
        m_data.add(
            new LevelData(1, 1, 1, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(2, 1, 2, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(3, 1, 3, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(4, 1, 4, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(5, 1, 5, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(6, 1, 6, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(7, 1, 7, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(8, 1, 8, true, 8, 25, 10, -10f, -1500f, 2f)
        );

        m_data.add(
            new LevelData(9, 1, 9, true, 8, 25, 10, -10f, -1500f, 2f)
        );
    }
}
