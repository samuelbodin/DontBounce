package com.mygdx.game.levels;

public class ChapterOne extends Chapter
{
    private int m_levels[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String[] m_backgrounds = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
    private String m_foreground = "flatbgforeground.png";

    @Override
    public int[] getLevels()
    {
        return m_levels;
    }

    @Override
    public String[] getBackgrounds()
    {
       return m_backgrounds;
    }

    @Override
    public String getForeground()
    {
        return m_foreground;
    }
}
