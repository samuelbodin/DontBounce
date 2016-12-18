package com.mygdx.game.Basics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ball.Ball;

public class Cam extends OrthographicCamera
{
    private float m_modifier = 0.9f;
    private float m_lag = 150.0f;
    private Vector2 m_bPos = null;
    private Vector2 m_bVel = null;
    private Vector2 m_lastPos = null;
    private float m_worldW = 0f;
    private float m_worldH = 0f;

    public Cam()
    {
        super();
        super.setToOrtho(false);
    }

    public void setBall(Ball b)
    {
        m_bPos = b.getPosition();
        m_bVel = b.getVelocity();
        m_lastPos = new Vector2();
    }

    public void setToBallPos(float dt)
    {
        m_lastPos.x = this.position.x;
        m_lastPos.y = this.position.y;

        this.position.y = m_bPos.y - m_worldH /4;
    }

    public Vector2 getDeltaPosition()
    {
        Vector2 pos = new Vector2(this.position.x-m_lastPos.x,this.position.y-m_lastPos.y);
        return pos;
    }

    public float getY()
    {
        return position.y;
    }

    public void setWorldDimensions(float worldW, float worldH)
    {
        m_worldW = m_worldW;
        m_worldH = worldH;
    }
    public boolean isOnScreen(Vector2 pos)
    {
        return !(pos.y < m_bPos.y - m_worldH || pos.y > m_bPos.y + m_worldH);
    }
}

