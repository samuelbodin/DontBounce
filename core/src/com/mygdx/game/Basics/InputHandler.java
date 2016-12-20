package com.mygdx.game.Basics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler
{

    private float m_prevDeltaMove = 0f;
    private float m_currentDeltaMove = 0f;
    private float m_moveFactor = 0f;
    private float m_moveValue = 0f;
    private Application.ApplicationType m_platform = null;

    private int m_left = 0;
    private int m_right = 0;

    private float[] m_accData;
    private int m_DataInputIndex = 0;

    // 0: no smooth, 1-1.5: normal
    private float smoothFactor = 1.5f;
    // 0: no smooth, 5-10: normal
    private int smoothSize = 8;

    public InputHandler()
    {
        m_moveFactor = 1.6f;
        setupPlatform();
        m_accData = new float[smoothSize];
    }


     public float getMoveValue()
     {
         return m_moveValue;
     }

    private void setupPlatform()
    {
        m_platform = Gdx.app.getType();

        if(m_platform == Application.ApplicationType.Desktop)
        {
            m_left = Input.Keys.LEFT;
            m_right = Input.Keys.RIGHT;
        }
    }

    private void handlePlatform()
    {
        if(m_platform == Application.ApplicationType.Android)
        {
            m_currentDeltaMove = getInputAndroid();
        }
        else if(m_platform == Application.ApplicationType.Desktop)
        {
            m_currentDeltaMove = getInputDesktop();
        }
    }

    private float getInputAndroid()
    {
        //return -Gdx.input.getAccelerometerX();
        return filteredAccelerometerData(-Gdx.input.getAccelerometerX());
    }

    private float getInputDesktop()
    {
        if ( (Gdx.input.isKeyPressed(m_left) && Gdx.input.isKeyPressed(m_right)) ||
                (!Gdx.input.isKeyPressed(m_left) && !Gdx.input.isKeyPressed(m_right)) )
        {
            return 0f;
        }
        else if(Gdx.input.isKeyPressed(m_left))
        {
            return -1f;
        }
        else if(Gdx.input.isKeyPressed(m_right))
        {
            return 1f;
        }

        return 0f;
    }

    private float filteredAccelerometerData(float newAccData)
    {
        if(smoothSize == 0)
        {
            return newAccData;
        }

        float result = 0f;
        int dataArrayLength = m_accData.length;

        for(int i = 0; i < dataArrayLength;i++)
        {
            result += m_accData[i]*smoothFactor;
        }

        result += newAccData;
        m_accData[m_DataInputIndex] = newAccData;
        m_DataInputIndex = (m_DataInputIndex+1)%dataArrayLength;



        return result/(dataArrayLength*smoothFactor+1);
    }

    public void update(float dt)
    {
        handlePlatform();

        m_moveValue =(m_prevDeltaMove+m_currentDeltaMove)/2;
        m_moveValue = m_moveValue * 320 * dt * m_moveFactor;
        m_prevDeltaMove = m_currentDeltaMove;
    }

}
