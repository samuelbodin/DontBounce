package com.mygdx.game.Basics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Rickard on 2016-12-07.
 */

public class InputHandler
{

    public float m_deltaMove;
    private Application.ApplicationType m_platform;
    private final float m_desktopMoveFactor;

    //For user testing: Values 0-2 to change steering mode
    private int m_steeringMode = 0;

    public InputHandler()
    {
        m_desktopMoveFactor = 1f;
        m_platform = Gdx.app.getType();
    }

    public InputHandler(float desktopMoveFactor)
    {
        m_desktopMoveFactor = desktopMoveFactor;
        m_platform = Gdx.app.getType();
    }

    public void update()
    {
        switch(m_platform) {
            case Android:
                switch (m_steeringMode)
                {
                    case 0:
                        //Original steering implementation
                        m_deltaMove = -Gdx.input.getAccelerometerX();
                        break;
                    case 1:
                        //Rotate phone around Z-axle steering
                        m_deltaMove = -Gdx.input.getGyroscopeZ()*5;
                        break;
                    case 2:
                        //Original steering but done with gyro
                        m_deltaMove = Gdx.input.getGyroscopeY()*5;
                        break;
                }
                break;
            case WebGL:
            case Desktop:
                if ( (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ||
                        (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) )
                {
                    m_deltaMove = 0;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    m_deltaMove = -m_desktopMoveFactor;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    m_deltaMove = m_desktopMoveFactor;
                }
                break;
        }
    }


}
