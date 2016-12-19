package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;
import com.mygdx.game.Basics.InputHandler;

import org.w3c.dom.ranges.Range;

import java.util.Random;

/**
 * Created by Rickard on 2016-12-07.
 */

public class BallStateMoveable extends BallState
{
    private float m_prevDeltaMove = 0f;
    private float m_currentDeltaMove = 0f;
    private boolean m_deltaMoveHasUpdated = false;
    private boolean m_doTrail = false;
    private Sprite m_trail[] = null;
    private float m_steeringFactor;

    Sound sound[];

    public BallStateMoveable(Ball b)
    {
        this(b, -100f, -1400f, 1.5f);
    }

    @Override
    protected boolean hasDeltaMove()
    {
        return true;
    }

    @Override
    protected void setDeltaMove(float deltaMove)
    {
        m_currentDeltaMove = deltaMove;
        m_deltaMoveHasUpdated = true;
    }

    @Override
    public void updateSprite()
    {
        m_sprite.setOriginCenter();
        m_sprite.setSize(m_ball.m_radius*2, m_ball.m_radius*2);
        m_sprite.setPosition(m_ball.m_position.x-m_ball.m_radius, m_ball.m_position.y-m_ball.m_radius);
    }


    public BallStateMoveable(Ball b, float gravity, float maxSpeed, float steeringFactor)
    {
        m_ball = b;
        m_texture = new Texture("flatball.png");
        m_sprite = new Sprite(m_texture);
        m_gravity = gravity;
        m_maxSpeed = maxSpeed;
        m_steeringFactor = steeringFactor;

        m_doTrail = false;
        //sound = new Sound[4];
        //sound[0] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce01.wav"));
        //sound[1] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce02.wav"));
        //sound[2] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce03.wav"));
        //sound[3] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce04.wav"));
    }

    private void initTrail()
    {
        m_trail = new Sprite[5];
        float alpha = 0.12f;
        for(int i = 0; i < m_trail.length; i++)
        {
            m_trail[i] = new Sprite(new Texture("flatball.png"));
            m_trail[i].setOriginCenter();
            float shrinkFactor = i*m_ball.m_radius/10;
            m_trail[i].setSize(m_ball.m_radius*2-shrinkFactor, m_ball.m_radius*2-shrinkFactor);
            m_trail[i].setPosition(0,0);
            if(i==0)
            {
                /*First trail is a hidden tracking object.
                * Alpha is therefor set to 0 (invisible).
                * */
                m_trail[i].setAlpha(0f);
            }
            else
            {
                m_trail[i].setAlpha(alpha);
            }

            alpha -= 0.02f;
        }
    }

    private void updateTrail()
    {
        if(m_trail == null)
        {
            initTrail();
        }

        float lastX = 0, lastY = 0;
        for(int i = 0; i < m_trail.length; i++)
        {
            if(i == 0)
            {
                //m_trail[i].setPosition(m_trail[i+1].getX(), m_trail[i+1].getY());
                lastX = m_trail[i].getX();
                lastY = m_trail[i].getY();
                m_trail[i].setPosition(m_ball.m_position.x-m_ball.m_radius, m_ball.m_position.y-m_ball.m_radius);
            }
            else
            {
                //m_trail[i].setPosition(x - r, y - r + 10 * i);
                float tmpLastX = m_trail[i].getX();
                float tmpLastY = m_trail[i].getY();
                m_trail[i].setPosition(lastX, lastY);
                lastX = tmpLastX;
                lastY = tmpLastY;
            }
        }
    }

    @Override
    public void update(float dt)
    {
        if(m_deltaMoveHasUpdated)
        {
            float move = (m_prevDeltaMove+m_currentDeltaMove)/2;

            m_ball.m_position.x += move * 300 * dt * m_steeringFactor;
            m_ball.m_velocity.x += move * 20 * dt * m_steeringFactor;

            m_deltaMoveHasUpdated = false;
        }

        m_prevDeltaMove = m_currentDeltaMove;

        updateTrail();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        if(m_doTrail)
        {
            for (Sprite s : m_trail)
            {
                s.draw(sb);
            }
        }

        m_sprite.draw(sb);
    }

    @Override
    protected void onCollision(Vector2 pos, int side)
    {
        Random rnd = new Random();

        // Move ball from overlapping obstacle
        switch (side)
        {
            case 0: // Top
                m_ball.m_position.y = pos.y+m_ball.m_radius;

                if(m_ball.m_velocity.y > 0 && m_ball.m_velocity.y <= -m_gravity)
                {

                    m_ball.m_isOnGround = true;
                    m_ball.m_velocity.y = 0;
                }

                break;
            case 1: // Right
                m_ball.m_position.x = pos.x+m_ball.m_radius+1;
                m_ball.m_position.y--;
                break;
            case 2: // Bottom
                m_ball.m_position.y = pos.y-m_ball.m_radius-1;
                break;
            case 3: // Left
                m_ball.m_position.x = pos.x-m_ball.m_radius-1;
                m_ball.m_position.y--;
                break;
        }

        if(!m_ball.m_isOnGround)
        {
            //sound[rnd.nextInt(sound.length)].play(1.0f * (m_ball.m_velocity.y/700) + 0.2f, 1f * (m_ball.m_velocity.y/700) + 1f, 0);
        }

        m_ball.m_velocity.scl(0,-0.6f);
    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
    }

}
