package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Basics.AssetLoader;

import java.util.ArrayList;

public class ChapterOne extends Chapter
{
    public ChapterOne(AssetLoader assetLoader)
    {
        super(1);

        m_backgrounds.add(assetLoader.flatbg01);
        m_backgrounds.add(assetLoader.flatbg02);
        m_backgrounds.add(assetLoader.flatbg03);
        m_foreground  = "flatbgforeground.png";

        m_tint = new Color(0.25f,0.5f,0.75f,1);
    }



}
