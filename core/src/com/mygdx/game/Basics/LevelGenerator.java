package com.mygdx.game.Basics;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AppStates.Cam;
import com.mygdx.game.Obstacles.StaticObstacle;

/**
 * Created by Rickard on 2016-12-07.
 */

public class LevelGenerator
{
    public float m_worldWidth = 0, m_worldHeight = 0;
    private Array<Collidable> m_collidables = null;
    private SimplexNoise m_noise = null;
    float m_obstacleX, m_obstacleY, m_lastObstacleX = 0, m_lastObstableY = -500;

    float m_minObstacleYSpace;
    float m_obstacleYSpaceFactor;
    float m_obstacleWidth;
    float m_snapMargin;

    public LevelGenerator(int seed)
    {
        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(2);
        generate();
    }

    public LevelGenerator(int seed, Cam m_cam)
    {
        setHW(m_cam);

        m_minObstacleYSpace = m_worldWidth;
        m_obstacleYSpaceFactor = m_worldWidth*2;
        m_obstacleWidth = 400;
        m_snapMargin = 200;

        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(2);
        generate();
    }

    public LevelGenerator(int seed, float obstacleSizeFactor, float obstacleSeparationFactor, float obstacleMinSpacingFactor, float obstacleSnapMargin, Cam m_cam)
    {
        setHW(m_cam);

        m_minObstacleYSpace = m_worldWidth/(obstacleMinSpacingFactor/10);
        m_obstacleYSpaceFactor = (m_worldWidth*2)/(obstacleSeparationFactor/10);
        m_obstacleWidth = 400*(obstacleSizeFactor/10);
        m_snapMargin = obstacleSnapMargin;

        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(2);
        generate();
    }

    private void generate()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                m_obstacleX = Math.abs((float)m_noise.noise(i+50,j+50))%1f;
                m_obstacleY = Math.abs((float)m_noise.noise(j-50,i+50))%1f;
                if(m_obstacleY <0.2f) {
                    m_obstacleY = 0.2f;
                }
                m_obstacleX = (m_obstacleX *m_worldWidth)-m_worldWidth/2;
                if(m_obstacleX + m_obstacleWidth > m_worldWidth/2-m_snapMargin) {
                    m_obstacleX = (m_worldWidth/2) - m_obstacleWidth;
                } else if(m_obstacleX < m_snapMargin-m_worldWidth/2)
                {
                    m_obstacleX = -m_worldWidth/2;
                }
                m_obstacleY = m_obstacleY *m_obstacleYSpaceFactor+m_minObstacleYSpace;
                m_collidables.add(new StaticObstacle(m_obstacleX, m_lastObstableY - m_obstacleY,m_obstacleWidth,50));
                m_lastObstableY -= m_obstacleY;
                m_lastObstacleX = m_obstacleX;
            }
        }
    }

    public Array<Collidable> getCollidables()
    {
        return m_collidables;
    }

    public void setHW(Cam m_cam)
    {
        m_worldWidth = m_cam.viewportWidth;
        m_worldHeight = m_cam.viewportHeight;
    }
}
