package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    float m_prevDeltaMove = 0;

    float m_steeringFactor;

    Sound sound[];

    public BallStateMoveable(Ball b)
    {
        this(b, -100f, -1400f, 1.5f);
    }


    public BallStateMoveable(Ball b, float gravity, float maxSpeed, float steeringFactor)
    {
        m_ball = b;
        m_texture = new Texture("flatball.png");
        m_sprite = new Sprite(m_texture);
        m_timer = 400;
        m_gravity = gravity;
        m_maxSpeed = maxSpeed;
        m_steeringFactor = steeringFactor;

        //sound = new Sound[4];
        //sound[0] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce01.wav"));
        //sound[1] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce02.wav"));
        //sound[2] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce03.wav"));
        //sound[3] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce04.wav"));
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);

        float move = m_input.m_deltaMove * 300 * dt * m_steeringFactor;

        if(m_ball.m_position.x-m_ball.m_radius+move < 0)
        {
            m_ball.m_velocity.x = 0;
            m_ball.m_position.x = m_ball.m_radius;
        }
        else if(m_ball.m_position.x+m_ball.m_radius+move > App.m_worldW)
        {
            m_ball.m_velocity.x = 0;
            m_ball.m_position.x =  App.m_worldW-m_ball.m_radius;
        } else
        {
            move = (m_prevDeltaMove+m_input.m_deltaMove)/2;
            m_ball.m_position.x += move * 300 * dt * m_steeringFactor;
            m_ball.m_velocity.x += move * 20 * dt * m_steeringFactor;
        }
        m_sprite.setOriginCenter();
        m_sprite.setSize(m_ball.m_radius*2, m_ball.m_radius*2);
        m_sprite.setPosition(m_ball.m_position.x-m_ball.m_radius, m_ball.m_position.y-m_ball.m_radius);
        //m_sprite.setSize(m_ball.m_radius*2, m_ball.m_radius*2);
        //m_sprite.setOriginCenter();

        m_prevDeltaMove = m_input.m_deltaMove;

    }

    @Override
    protected void onCollision(Vector2 pos, int side)
    {
        //m_ball.m_position.y = pos.y+m_ball.m_radius-1;
        Random rnd = new Random();


        if(m_ball.m_velocity.y >= -30 && m_ball.m_velocity.y <= -m_gravity)
        {
            m_ball.m_isOnGround = true;
            m_ball.m_velocity.y = 0;
        }
        else
        {
            m_ball.m_velocity.scl(0,-0.95f);
            //sound[rnd.nextInt(sound.length)].play(1.0f);
        }
    }

    @Override
    protected void onCollision(Vector2 pos, int side, Vector2 pos1, Vector2 pos2 )
    {
        //m_ball.m_position.y = pos.y+m_ball.m_radius-1;
        Random rnd = new Random();

        // Move ball from overlapping obstacle
        switch (side)
        {
            case 0: // Top
                //m_ball.m_position.y = pos1.y+pos2.y+m_ball.m_radius;
                m_ball.m_position.y = pos.y+m_ball.m_radius+1;
                break;
            case 1: // Right
                //m_ball.m_position.x = pos1.x+pos2.x+m_ball.m_radius;
                m_ball.m_position.x = pos.x+m_ball.m_radius+1;
                break;
            case 2: // Bottom
                //m_ball.m_position.y = pos1.y-m_ball.m_radius;
                m_ball.m_position.y = pos.y-m_ball.m_radius-1;
                break;
            case 3: // Left
                //m_ball.m_position.x = pos1.x-m_ball.m_radius;
                m_ball.m_position.x = pos.x-m_ball.m_radius-1;
                break;
            default:
                if(m_ball.m_position.x <= pos1.x+(pos2.x/2))
                {
                    m_ball.m_position.x = pos1.x-m_ball.m_radius-1;
                }
                else
                {
                    m_ball.m_position.x = pos1.x+pos2.x+m_ball.m_radius+1;
                }
                break;
        }

        if(m_ball.m_velocity.y >= -30 && m_ball.m_velocity.y <= -m_gravity)
        {

            m_ball.m_isOnGround = true;
            m_ball.m_velocity.y = 0;
        }
        else
        {
            //m_ball.m_velocity.scl(0,-0.90f);
            m_ball.m_velocity.y *= -0.6;

            //sound[rnd.nextInt(sound.length)].play(1.0f * (m_ball.m_velocity.y/700) + 0.2f, 1f * (m_ball.m_velocity.y/700) + 1f, 0);
        }
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
