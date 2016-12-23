package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Basics.AssetLoader;

/**
 * Created by Rickard on 2016-12-22.
 */

public class ChapterTwo extends Chapter
{
    public ChapterTwo()
    {
        super(2);

        m_backgrounds.add(AssetLoader.spacebg01);
        m_backgrounds.add(AssetLoader.spacebg02);
        m_backgrounds.add(AssetLoader.spacebg03);
        m_foreground  = AssetLoader.spacebgforeground;

        m_tint = new Color(0.25f,0.95f,0.25f,1);
    }
}
