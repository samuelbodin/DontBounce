package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Rickard on 2016-12-21.
 */

public class AudioHandler implements Disposable
{
    public static Music musicLevelNormal = null;
    public static Music musicLevelSpeed = null;

    public static Sound powerUp = null;
    private static Array<Music> m_allMusic;

    public static void load()
    {
        m_allMusic = new Array<Music>();

        musicLevelNormal = Gdx.audio.newMusic(Gdx.files.internal("sound/dbNormal.wav"));
        musicLevelNormal.setLooping(true);
        musicLevelNormal.setVolume(0.5f);
        m_allMusic.add(musicLevelNormal);

        musicLevelSpeed = Gdx.audio.newMusic(Gdx.files.internal("sound/dbSpeed.wav"));
        musicLevelSpeed.setLooping(true);
        musicLevelSpeed.setVolume(0.5f);
        m_allMusic.add(musicLevelSpeed);


        powerUp = Gdx.audio.newSound(Gdx.files.internal("sound/powerup.wav"));
    }

    public static void musicLevelNormalStart()
    {
        if(nowPlaying() != musicLevelNormal)
        {
            if(nowPlaying() != null)
            {
                nowPlaying().stop();
            }

            //musicLevelNormal.play();
        }
    }

    public static void musicLevelNormalStart( float position)
    {
        if(nowPlaying() != musicLevelNormal)
        {
            if(nowPlaying() != null)
            {
                nowPlaying().stop();
            }
            musicLevelNormal.setPosition(position/1.5f);
            //musicLevelNormal.play();
        }
    }

    public static void musicLevelSpeedStart()
    {
        if(nowPlaying() != musicLevelSpeed)
        {
            if(nowPlaying() != null)
            {
                nowPlaying().stop();
            }
            //musicLevelSpeed.play();
        }
    }

    public static void musicLevelSpeedStart( float position)
    {
        if(nowPlaying() != musicLevelSpeed)
        {
            if(nowPlaying() != null)
            {
                nowPlaying().stop();
            }
            musicLevelSpeed.setPosition(position/1.5f);
            //musicLevelSpeed.play();
        }

    }

    public static Music nowPlaying()
    {
        for(Music m : m_allMusic)
        {
            if(m.isPlaying())
            {
                return m;
            }
        }

        return null;
    }

    public static void musicStop()
    {
        for(Music m : m_allMusic)
        {
            m.stop();
        }
    }

    public static void powerUp()
    {
        powerUp.play(0.1f);
    }

    public void dispose()
    {
        for(Music m : m_allMusic)
        {
            m.dispose();
        }
        powerUp.dispose();
    }


}
