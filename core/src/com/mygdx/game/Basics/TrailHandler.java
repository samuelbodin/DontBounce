package com.mygdx.game.Basics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class TrailHandler
{
    /*
    private boolean m_doTrail = false;
    private Sprite m_trail[] = null;

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

    if(m_doTrail)
    {
        for (Sprite s : m_trail)
        {
            s.draw(sb);
        }
    }
    */
}


