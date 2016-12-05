package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;

public class Cam extends OrthographicCamera
{
    private float m_modifier = 0.9f;
    private float m_lag = 150.0f;
    private Vector2 m_bPos = null;
    private Vector2 m_bVel = null;


    public Cam()
    {
        super();
        super.setToOrtho(false);
    }

    public void setPos(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    public void setBall(Ball b)
    {
        m_bPos = b.getPosition();
        m_bVel = b.getVelocity();
    }

    public void setToBallPos(float dt)
    {
        float x = (this.position.x + m_bVel.x * dt) * (1.0f - m_modifier);
        float y = (this.position.y + m_bVel.y *dt) * (1.0f - m_modifier);

        this.position.x = x + m_bPos.x * m_modifier;
        this.position.y = y + m_bPos.y * m_modifier;


    }
}

