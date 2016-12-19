package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class ChapterOne extends Chapter
{
    public ChapterOne()
    {
        super(1);

        m_backgrounds.add("flatbg01.png");
        m_backgrounds.add("flatbg02.png");
        m_backgrounds.add("flatbg03.png");
        m_foreground  = "flatbgforeground.png";
        m_tint = new Color(0.25f,0.5f,0.75f,1);
    }



}
