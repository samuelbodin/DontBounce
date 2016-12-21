package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Basics.AssetLoader;

import java.util.ArrayList;

public class ChapterOne extends Chapter
{
    public ChapterOne()
    {
        super(1);

        m_backgrounds.add(AssetLoader.flatbg01);
        m_backgrounds.add(AssetLoader.flatbg02);
        m_backgrounds.add(AssetLoader.flatbg03);
        m_foreground  = AssetLoader.flatbgforeground;

        m_tint = new Color(0.25f,0.5f,0.75f,1);
    }



}
