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
    float m_timer = 0;
    InputHandler m_input;
    float m_gravity = -30.0f;
    float m_maxSpeed = -750.0f;
    public boolean doTrail = false;

    Sprite m_trail[] = null;

    BallState()
    {
        m_input = new InputHandler();

    }
    BallState(float timerSeconds)
    {
        m_timer = timerSeconds;
    }
    BallState(Ball b)
    {
        m_ball = b;

    }
    protected void setBall(Ball b)
    {
        m_ball = b;
        initTrail();

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

    @Override
    public void update(float dt)
    {
        m_input.update();
        m_timer-=dt;

        if(m_timer <= 0)
        {
            m_ball.setState(new BallStateNormal(m_ball));
        }

        float x = m_ball.m_position.x;
        float y = m_ball.m_position.y;
        float r = m_ball.m_radius;
        m_sprite.setPosition(x-r, y-r);

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
                m_trail[i].setPosition(x - r, y - r);
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
    public void render(SpriteBatch sb)
    {
        if(doTrail)
        {
            for (Sprite s : m_trail)
            {
                s.draw(sb);
            }
        }

        //m_sprite.setPosition(x-r, y-r);
        //sb.draw(m_texture,x-r,y-r,r+r,r+r);
        m_sprite.draw(sb);
    }
    protected abstract void onCollision(Vector2 pos, int side);
    protected abstract void onCollision(Vector2 pos, int side, Vector2 pos1, Vector2 pos2);

    @Override
    public String toString()
    {
        String str = "m_ball: " + m_ball.toString() + "\n";
        str += "m_timer: " + m_timer + "\n";
        return str;
    }
}
