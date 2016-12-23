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
        loadChapterTwo();
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
            new LevelData(1, 1, 1, true, 13, 12, 10, 80, false, -10f, -1500f, 2f, new int[] {0,0,0,0,0,0,0})
        );

        m_data.add(
            new LevelData(2, 1, 2, true, 22, 13, 11, 80, false, -10f, -1500f, 2f, new int[] {0,1,0,1,0,0})
        );

        m_data.add(
            new LevelData(3, 1, 3, true, 35, 13, 11, 80, true, -10f, -1500f, 2f, new int[] {2,1,0,2,1})
        );

        m_data.add(
            new LevelData(4, 1, 4, true, 14, 14, 12, 80, false, -11f, -1500f, 2f, new int[] {0,1,1,0,1})
        );

        m_data.add(
            new LevelData(5, 1, 5, true, 15, 15, 13, 80, false, -11f, -1500f, 2f, new int[] {0,1,1,0,1})
        );

        m_data.add(
            new LevelData(6, 1, 6, true, 30, 13, 11, 80, true, -11f, -1500f, 2f, new int[] {2,1,0,2,0})
        );

        m_data.add(
            new LevelData(7, 1, 7, true, 16, 16, 14, 80, false, -12f, -1500f, 2f, new int[] {0,1,2,0,0})
        );

        m_data.add(
            new LevelData(8, 1, 8, true, 17, 17, 18, 80, false, -12f, -1500f, 2f, new int[] {0,1,2,1,0})
        );

        m_data.add(
            new LevelData(9, 1, 9, true, 25, 13, 11, 80, true, -12f, -1500f, 2f, new int[] {2,1,0,2,0,1})
        );
    }


    private void loadChapterTwo()
    {
        m_data.add(
                new LevelData(10, 2, 1, true, 13, 12, 10, 80, false, -10f, -1500f, 2f, new int[] {1,1,0,0,2,0})
        );

        m_data.add(
                new LevelData(11, 2, 2, true, 22, 13, 11, 80, false, -10f, -1500f, 2f, new int[] {2,1,0,0,2,0})
        );

        m_data.add(
                new LevelData(12, 2, 3, true, 35, 13, 11, 80, true, -10f, -1500f, 2f, new int[] {2,1,0,2,1})
        );

        m_data.add(
                new LevelData(13, 2, 4, true, 14, 14, 12, 80, false, -11f, -1500f, 2f, new int[] {0,1,0,1,0})
        );

        m_data.add(
                new LevelData(14, 2, 5, true, 15, 15, 13, 80, false, -11f, -1500f, 2f, new int[] {0,1,2,1,0})
        );

        m_data.add(
                new LevelData(15, 2, 6, true, 30, 13, 11, 80, true, -11f, -1500f, 2f, new int[] {2,1,0,2,1})
        );

        m_data.add(
                new LevelData(16, 2, 7, true, 16, 16, 14, 80, false, -12f, -1500f, 2f, new int[] {2,1,0,1,0})
        );

        m_data.add(
                new LevelData(17, 2, 8, true, 17, 17, 18, 80, false, -12f, -1500f, 2f, new int[] {0,1,2,1,0})
        );

        m_data.add(
                new LevelData(18, 2, 9, true, 25, 13, 11, 80, true, -12f, -1500f, 2f, new int[] {2,1,0,2,1})
        );
    }
}
