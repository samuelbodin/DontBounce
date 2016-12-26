package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.AudioHandler;
import com.mygdx.game.Basics.Drawable;
import com.mygdx.game.Basics.InputHandler;
import com.mygdx.game.Obstacles.Obstacle;

abstract class BallState extends Drawable
{
    Ball m_ball = null;
    float m_defaultGravityModifier = 1f;
    float m_gravityModifier = 1f;
    float m_onCollisionGravityModifier = 1f;
    AudioHandler m_ah = null;

    BallState()
    {
    }

    BallState(Ball b)
    {
        m_ball = b;
        m_ball.resetVariables();
    }

    void setAudioHandler(AudioHandler ah)
    {
        m_ah = ah;
    }

    public void setBall(Ball b)
    {
        m_ball = b;
    }

    public void update(float dt)
    {
        float gravity = m_ball.getGravity();
        float velY = m_ball.getVelocity().y;

        if(velY >= gravity && velY <= -gravity)
        {
            resetGravityModifier();
        }

        m_ball.applyGravity(m_gravityModifier);
    }

    protected void onCollision(Vector2 pos, int side)
    {
        /*
        0 - Ball is above obstacle
        1 - Ball is to the right of obstacle
        2 - Ball is below obstacle
        3 - Ball is to the left of obstacle
        */
        if(side == 0)
        {
            m_ball.alignAbovePosition(pos);
            if(m_ball.isOnGround())
            {
                m_ball.resetVelocityY();
            }
            else
            {
                m_gravityModifier = m_onCollisionGravityModifier;
                m_ball.flipVelocityY();
            }
        }
        else if(side == 1)
        {
            m_ball.alignToTheRightOfPosition(pos);
            resetGravityModifier();
        }
        else if(side == 2)
        {
            m_ball.alignBelowPosition(pos);
            resetGravityModifier();
            m_ball.flipVelocityY();
        }
        else if(side == 3)
        {
            m_ball.alignToTheLeftOfPosition(pos);
            resetGravityModifier();
        }

        if(!m_ball.isOnGround() )
        {
            if(Math.abs(m_ball.getVelocity().y) > Math.abs(m_ball.getGravity())*20)
            {
                m_ball.collisionEffect(new Vector2(pos.x - m_ball.getRadius() * 2, pos.y), side, AssetLoader.m_splashAnimation, 10);
            }
            m_ball.collisionSound(side);
        }
    }


    void resetGravityModifier()
    {
        m_gravityModifier = m_defaultGravityModifier;
    }

    protected abstract boolean hasHistory();
    protected abstract boolean hasOnCollision();
    protected abstract void updateSprite(float x, float y);
    protected abstract void setSpriteSize(float width, float height);

    protected void playBounceSound(float velY)
    {
        if(m_ah == null)
        {
            Gdx.app.log("JS", "null");
        }


        m_ah.playBounceSound(velY);
    }

    @Override
    public String toString()
    {
        String str = "m_ball: " + m_ball.toString() + "\n";
        return str;
    }
}
