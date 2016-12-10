package com.mygdx.game.Ball;

import com.mygdx.game.Basics.Circle;
import com.badlogic.gdx.utils.Array;

public class BallPositionHistory
{
    private int m_maxIndeces = 3;
    Array<Circle> m_circles = null;

    public BallPositionHistory()
    {
        m_circles = new Array<Circle>();
    }

    public BallPositionHistory(int MaxIndeces)
    {
        m_maxIndeces = MaxIndeces;
        m_circles = new Array<Circle>();
    }

    public void addToHistory(Circle c)
    {
        int index = -1;

        for(int i = 0; i < m_circles.size; i++)
        {
            if(m_circles.get(i).m_x == c.m_x && m_circles.get(i).m_y == c.m_y)
            {
                index = i;
                break;
            }
        }
        if(index == -1)
        {
            if(m_circles.size == m_maxIndeces)
            {
                for(int i = 0; i < m_maxIndeces -1; i++)
                {
                    m_circles.set(i, m_circles.get(i+1));
                }
                m_circles.set(m_maxIndeces-1, c);
            }
            else
            {
                m_circles.add(c);
            }
        }
    }
}

