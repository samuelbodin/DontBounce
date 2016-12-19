package com.mygdx.game.Basics;

import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.App;

import java.util.ArrayList;
import java.util.List;

public class LevelData {

    public int m_levelId;
    public int m_chapterId;
    public float m_worldHeight = 15000;
    public Music m_music = null;
    public int m_seed;
    public float m_obstacleSizeFactor;
    public float m_obstacleSeparationFactor;
    public float m_obstacleMinSpacingFactor;
    public boolean m_foreground;
    public List<String> m_backgroundFiles;
    public String m_foregroundFile;
    public float m_ballGravity;
    public float m_ballMaxSpeed;
    public float m_ballSensitivity;
    public float m_obstacleSnapMargin;
    public Color m_tint;
    public boolean m_hasHoles = false;

    /*
    public LevelData()
    {
        m_backgroundFiles = new ArrayList<String>();
        // Default values
        m_seed = 4;
        m_backgroundFiles.add("flatbg01.png");
        m_backgroundFiles.add("flatbg02.png");
        m_backgroundFiles.add("flatbg03.png");
        m_foregroundFile = "flatbgforeground.png";
        m_foreground = true;
        m_obstacleSizeFactor = 8;
        m_obstacleSeparationFactor = 25;
        m_obstacleMinSpacingFactor = 10;
        m_obstacleSnapMargin = 80;
        m_ballGravity = -10f;
        m_ballMaxSpeed = -1500f;
        m_ballSensitivity = 2f;
    }
    */

    public LevelData(
        int levelId,
        int chapterId,
        int seed,
        boolean foreground,
        int obstacleSizeFactor,
        int obstacleSeparationFactor,
        float obstacleMinSpacingFactor,
        float obstacleSnapMargin,
        boolean hasHoles,
        float ballGravity,
        float ballMaxSpeed,
        float ballSensitivity
    )
    {
        m_levelId = levelId;
        m_chapterId = chapterId;
        m_seed = seed;
        m_foreground = foreground;
        m_obstacleSizeFactor = obstacleSizeFactor;
        m_obstacleSeparationFactor = obstacleSeparationFactor;
        m_obstacleMinSpacingFactor = obstacleMinSpacingFactor;
        m_obstacleSnapMargin = obstacleSnapMargin;
        m_hasHoles = hasHoles;
        m_ballGravity = ballGravity;
        m_ballMaxSpeed = ballMaxSpeed;
        m_ballSensitivity = ballSensitivity;

    }

}
