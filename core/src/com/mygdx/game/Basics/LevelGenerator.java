package com.mygdx.game.Basics;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.App;
import com.mygdx.game.AppStates.StateManager;
import com.mygdx.game.Obstacles.LevelGoal;
import com.mygdx.game.Obstacles.StaticObstacle;


/**
 * Created by Rickard on 2016-12-07.
 */

public class LevelGenerator
{
    public float m_worldWidth = 0, m_worldHeight = 0;
    private Array<Collidable> m_collidables = null;
    private SimplexNoise m_noise = null;
    private float m_obstacleX, m_obstacleY, m_lastObstacleX, m_lastObstableY, m_minObstacleYSpace, m_obstacleYSpaceFactor, m_obstacleWidth, m_snapMargin;

    public LevelGenerator(int seed)
    {
        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(seed);
        generate();
    }


    public LevelGenerator(int seed, float worldHeight, float obstacleSizeFactor, float obstacleSeparationFactor, float obstacleMinSpacingFactor, float obstacleSnapMargin)
    {
        m_worldWidth = App.m_worldW;
        m_worldHeight = worldHeight;

        m_minObstacleYSpace = m_worldWidth/(obstacleMinSpacingFactor/15);
        m_obstacleYSpaceFactor = (m_worldWidth/2)/(obstacleSeparationFactor/10);
        m_obstacleWidth = (m_worldWidth/4)*(obstacleSizeFactor/15);
        m_snapMargin = obstacleSnapMargin;

        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(seed);
    }

    private void generate()
    {
        int i=0;
        do
        {
            //m_obstacleX = Math.abs((float) m_noise.noise(i + 50, i + 25)) % 1f;
            //m_obstacleY = Math.abs((float) m_noise.noise(i + 25, i + 50)) % 1f;
            m_obstacleX = Math.abs((float) m_noise.noise(1, i*0.1)) % 1f;
            m_obstacleY = m_obstacleX;
            if (m_obstacleY < 0.2f)
            {
                m_obstacleY = 0.2f;
            }

            m_obstacleX = (m_obstacleX * m_worldWidth);

            if(Math.abs(m_obstacleX - m_lastObstacleX) <  m_worldWidth/6)
            {
                m_obstacleX = ((m_obstacleX+m_worldWidth/6))%m_worldWidth;
            }

            if (m_obstacleX + m_obstacleWidth > m_worldWidth - m_snapMargin)
            {
                m_obstacleX = m_worldWidth - m_obstacleWidth;
            } else if (m_obstacleX < m_snapMargin)
            {
                m_obstacleX = 0;
            }
            m_obstacleY = m_obstacleY * m_obstacleYSpaceFactor + m_minObstacleYSpace;
            m_collidables.add(new StaticObstacle(m_obstacleX, m_lastObstableY - m_obstacleY, m_obstacleWidth, 32));
            m_lastObstableY -= m_obstacleY;
            m_lastObstacleX = m_obstacleX;
            i++;
        } while(m_lastObstableY > -m_worldHeight+(m_minObstacleYSpace*2));
    }

    private void generateInverted()
    {
        int i=0;
        do
        {
            //m_obstacleX = Math.abs((float) m_noise.noise(i + 50, i + 25)) % 1f;
            //m_obstacleY = Math.abs((float) m_noise.noise(i + 25, i + 50)) % 1f;
            m_obstacleX = Math.abs((float) m_noise.noise(1, i*0.1)) % 1f;
            m_obstacleY = m_obstacleX;
            if (m_obstacleY < 0.2f)
            {
                m_obstacleY = 0.2f;
            }

            m_obstacleX = (m_obstacleX * m_worldWidth);

            if(Math.abs(m_obstacleX - m_lastObstacleX) <  m_worldWidth/6)
            {
                m_obstacleX = ((m_obstacleX+m_worldWidth/6))%m_worldWidth;
            }

            if (m_obstacleX + m_obstacleWidth > m_worldWidth - m_snapMargin)
            {
                m_obstacleX = m_worldWidth - m_obstacleWidth;
            } else if (m_obstacleX < m_snapMargin)
            {
                m_obstacleX = 0;
            }
            m_obstacleY = m_obstacleY * m_obstacleYSpaceFactor + m_minObstacleYSpace;
            //m_collidables.add(new StaticObstacle(m_obstacleX, m_lastObstableY - m_obstacleY, m_obstacleWidth, 32));
            m_collidables.add(new StaticObstacle(0, m_lastObstableY - m_obstacleY, m_obstacleX, 32));
            m_collidables.add(new StaticObstacle(m_obstacleX+m_obstacleWidth, m_lastObstableY - m_obstacleY, m_worldWidth-(m_obstacleX+m_obstacleWidth), 32));

            m_lastObstableY -= m_obstacleY;
            m_lastObstacleX = m_obstacleX;
            i++;
        } while(m_lastObstableY > -m_worldHeight+(m_minObstacleYSpace*2));
    }

    public LevelGoal getGoal()
    {
        return new LevelGoal(0, -m_worldHeight-(App.m_worldH/2), m_worldWidth, 100);
    }

    public Array<Collidable> getCollidables()
    {
        return m_collidables;
    }

}
