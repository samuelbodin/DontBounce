package com.mygdx.game.Basics;

import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.App;

import java.util.ArrayList;
import java.util.List;

public class LevelData {

    public int m_levelId;
    public int m_chapterId;
    public float m_levelHeight = 30000;
    public Music m_music = null;
    public int m_seed;
    public float m_obstacleSizeFactor;
    public float m_obstacleSeparationFactor;
    public float m_obstacleMinSpacingFactor;
    public boolean m_foreground;
    public List<TextureRegion> m_backgroundFiles;
    public TextureRegion m_foregroundFile;
    public float m_ballGravity;
    public float m_ballMaxSpeed;
    public float m_ballSensitivity;
    public float m_obstacleSnapMargin;
    public Color m_tint;
    public boolean m_hasHoles = false;
    public int[] m_numOfPowerUp;

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

        m_numOfPowerUp = new int[4];
        m_numOfPowerUp[0] = 2;
        m_numOfPowerUp[1] = 2;
        m_numOfPowerUp[2] = 2;
        m_numOfPowerUp[3] = 2;


    }

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
            float ballSensitivity,
            int[] numOfPowerUp
    )
    {
        this(
         levelId,
         chapterId,
         seed,
         foreground,
         obstacleSizeFactor,
         obstacleSeparationFactor,
         obstacleMinSpacingFactor,
         obstacleSnapMargin,
         hasHoles,
         ballGravity,
         ballMaxSpeed,
         ballSensitivity
        );


        m_numOfPowerUp = numOfPowerUp;

    }

    public float getLevelTime()
    {
        // Necessary data.
        float gravity = Math.abs(m_ballGravity);
        float maxSpeed = Math.abs(m_ballMaxSpeed);
        float levelLength = Math.abs(m_levelHeight);
        float fps = 60;

        /* The balls acceleration is dependent on the fps.
        * Not good but that's how the physics is designed. */
        float acceleration = gravity*fps;

        // Time to max speed.
        float timeToMax = maxSpeed/acceleration;

        // The distance traveled during acceleration.
        float distanceAtMax = 0.5f*acceleration*timeToMax*timeToMax;

        // The time from max speed to goal.
        float timeWithMaxSpeed = (levelLength-distanceAtMax)/maxSpeed;

        // Sum the two times.
        float totalTime = timeToMax + timeWithMaxSpeed;

        /* Due to "bad" design and start delay we need error correction.
        * Add enough to be sure that the time really exceeds the minimum time.
        * Error is 7-9% */
        float TimeWidthErrorCorrection = totalTime * 1.1f;

        Interpolation i = Interpolation.exp5Out;
        TimeWidthErrorCorrection += 10 * i.apply(Math.min(1f, (m_levelId*(Math.abs(m_ballGravity)/10)/20)));

        return TimeWidthErrorCorrection;

    }

    public boolean isLevelComplete(float time)
    {
        return time < getLevelTime();
    }

}
