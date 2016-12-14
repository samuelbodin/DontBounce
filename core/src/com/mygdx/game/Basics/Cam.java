package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;

public class Cam extends OrthographicCamera
{
    private float m_modifier = 0.9f;
    private float m_lag = 150.0f;
    private Vector2 m_bPos = null;
    private Vector2 m_bVel = null;
    private Vector2 m_lastPos = null;


    public Cam()
    {
        super();
        super.setToOrtho(false);
    }

    public Cam(float viewportWidth, float viewportHeight)
    {
        super();
        super.setToOrtho(false,viewportWidth,viewportHeight);
    }

    public Cam(float viewportWidth)
    {
        super();
        float aspectRatio = 16/9;
        super.setToOrtho(false,viewportWidth,viewportWidth*aspectRatio);
    }

    public void setPos(float x, float y)
    {
        //this.position.x = x;
        //this.position.y = y;
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
        //float x = (this.position.x + m_bVel.x * dt) * (1.0f - m_modifier);
        //float y = (this.position.y + m_bVel.y *dt) * (1.0f - m_modifier);

        // Cameras x position should not update!
        //this.position.x = x + m_bPos.x * m_modifier;
        //this.position.y = y + m_bPos.y * m_modifier - App.m_worldH/4;
        this.position.y = m_bPos.y - App.m_worldH/4;


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
}
