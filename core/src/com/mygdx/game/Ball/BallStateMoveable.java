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


public class BallStateMoveable extends BallState
{
    private float m_prevDeltaMove = 0f;
    private float m_currentDeltaMove = 0f;
    private boolean m_deltaMoveHasUpdated = false;

    private float m_steeringFactor;

    Sound sound[];

    public BallStateMoveable(Ball b)
    {
        this(b, -100f, -1400f, 1.5f);
        setupSprite();
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

    private void setupSprite()
    {
        m_sprite.setOriginCenter();
    }

    @Override
    public void updateSprite()
    {
        m_sprite.setPosition(m_ball.m_position.x-m_ball.m_radius, m_ball.m_position.y-m_ball.m_radius);
    }

    void setSpriteSize(float width , float height)
    {
        m_sprite.setSize(width, height);
    }

    public BallStateMoveable(Ball b, float gravity, float maxSpeed, float steeringFactor)
    {
        m_ball = b;
        m_texture = new Texture("flatball.png");
        m_sprite = new Sprite(m_texture);
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
        if(m_deltaMoveHasUpdated)
        {
            float move = (m_prevDeltaMove+m_currentDeltaMove)/2;

            m_ball.m_position.x += move * 300 * dt * m_steeringFactor;
            m_ball.m_velocity.x += move * 20 * dt * m_steeringFactor;

            m_deltaMoveHasUpdated = false;
        }

        m_prevDeltaMove = m_currentDeltaMove;

    }

    @Override
    public void render(SpriteBatch sb)
    {


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
