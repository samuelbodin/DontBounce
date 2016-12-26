package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-21.
 */

public class AudioHandler
{
    private Music m_music = null;
    private Sound m_powerUpSound = null;
    private Sound[] m_bounceSounds = null;
    private boolean m_isMuted = false;
    private float m_defaultVolume = 0.5f;
    public AudioHandler(boolean isMuted)
    {
        m_isMuted = isMuted;
        m_music = AssetLoader.m_musicMenu;
        setupMusic();

        m_bounceSounds = AssetLoader.m_bounceSounds;
        m_powerUpSound = AssetLoader.m_powerUp;
    }

    public void playMenuMusic()
    {
        stopMusic();
        m_music = AssetLoader.m_musicMenu;
        setupMusic();
        m_music.play();
    }

    private void setupMusic()
    {
        m_music.setLooping(true);

        if(m_isMuted)
        {
            m_music.setVolume(0f);
        }
        else
        {
            m_music.setVolume(0.5f);
        }
    }

    private void stopMusic()
    {
        if(m_music.isPlaying())
        {
            m_music.stop();
        }
    }

    public void playPlayStateMusic(int chapter)
    {
        stopMusic();
        if(chapter == 1)
        {
            m_music = AssetLoader.m_musicChapterOne;

        }
        else if(chapter == 2)
        {
            m_music = AssetLoader.m_musicChapterTwo;
        }
        setupMusic();
        m_music.play();
    }

    public void playBounceSound(float velY)
    {
        if(m_isMuted)
        {
            return;
        }
        Random rnd = new Random();

        m_bounceSounds[rnd.nextInt(m_bounceSounds.length)].play(1.0f * (velY/700) + 0.2f, 1f * (velY/700) + 1f, 0);
    }

    public void playPowerUpSound()
    {
        if(m_isMuted)
        {
            return;
        }
        m_powerUpSound.play(0.1f);
    }

    public void toggleMute()
    {
        m_isMuted = ! m_isMuted;

        if(m_isMuted)
        {
            m_music.setVolume(0);
        }
        else
        {
            m_music.setVolume(m_defaultVolume);
        }
    }

    public boolean isMuted()
    {
        return m_isMuted;
    }
}
