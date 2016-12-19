package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Basics.Drawable;
import com.mygdx.game.Basics.InputHandler;
import com.mygdx.game.Obstacles.Obstacle;

abstract class BallState extends Drawable
{
    Ball m_ball = null;

    BallState()
    {
    }

    BallState(Ball b)
    {
        m_ball = b;
        m_ball.resetVariables();
    }
    public void setBall(Ball b)
    {
        m_ball = b;
    }


    protected void onCollision(Vector2 pos, int side)
    {
    }

    protected abstract boolean hasOnCollision();
    protected abstract void updateSprite(float x, float y);
    protected abstract void setSpriteSize(float width, float height);

    protected void playBounceSound(float velY)
    {
    }

    @Override
    public String toString()
    {
        String str = "m_ball: " + m_ball.toString() + "\n";
        return str;
    }
}
