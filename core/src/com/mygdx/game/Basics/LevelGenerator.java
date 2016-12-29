package com.mygdx.game.Basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Obstacles.LevelGoal;
import com.mygdx.game.Obstacles.PowerUpSuperSpeed;
import com.mygdx.game.Obstacles.PowerUpUltraRapid;
import com.mygdx.game.Obstacles.PowerUpPassThrough;
import com.mygdx.game.Obstacles.StaticObstacle;

import com.badlogic.gdx.graphics.Color;


public class LevelGenerator
{
    public float m_worldWidth = 0, m_worldHeight = 0, m_levelHeight = 0;
    private Array<Collidable> m_collidables = null;
    private SimplexNoise m_noise = null;
    private float m_obstacleX, m_obstacleY, m_lastObstacleX, m_lastObstableY, m_minObstacleYSpace, m_obstacleYSpaceFactor, m_obstacleWidth, m_snapMargin;
    private Array<Vector2> m_powerUps;
    private Color m_tint;
    private LevelData m_levelData;

    int m_powerUpSpacing = 0;
    int m_lastPowerUp = 0;
    int m_puIndex = 0;
    int m_puSize = 64;

    public LevelGenerator(int seed)
    {
        m_collidables = new Array<Collidable>();
        m_powerUps = new Array<Vector2>();
        m_noise = new SimplexNoise(seed);
        generate();
    }

    public LevelGenerator(LevelData levelData, float worldWidth, float worldHeight)
    {
        m_worldWidth = worldWidth;
        m_worldHeight = worldHeight;
        m_levelData = levelData;
        m_levelHeight = m_levelData.m_levelHeight;

        m_minObstacleYSpace = m_worldWidth/(m_levelData.m_obstacleMinSpacingFactor/15);
        m_obstacleYSpaceFactor = (m_worldWidth/2)/(m_levelData.m_obstacleSeparationFactor/10);
        m_obstacleWidth = (m_worldWidth/4)*(m_levelData.m_obstacleSizeFactor/15);
        m_snapMargin = m_levelData.m_obstacleSnapMargin;

        m_tint = m_levelData.m_tint;

        m_powerUpSpacing = (int)(m_levelHeight/(m_levelData.m_numOfPowerUp.length+1));

        m_collidables = new Array<Collidable>();
        m_noise = new SimplexNoise(m_levelData.m_seed);

        generate();

    }

    private void generate()
    {
        int i=0;
        do
        {
            renewParameters(i);

            // Add obstacle
            if(m_levelData.m_hasHoles)
            {
                if((int)Math.abs(m_lastObstableY)%6==0)
                {
                    addPlatform(m_obstacleWidth/2);
                }
                else
                {
                    addHole();
                }

            }
            else
            {
                addPlatform();
            }

            // Randomize powerups.
            addPowerUp();

            // Store last positions
            m_lastObstableY -= m_obstacleY;
            m_lastObstacleX = m_obstacleX;

            i++;
        } while(m_lastObstableY > -m_levelHeight +(m_minObstacleYSpace*2));

    }

    private void generateInverted()
    {
        int i=0;
        do
        {
            renewParameters(i);

            addHole();

            // Add powerups.
            addPowerUp();

            m_lastObstableY -= m_obstacleY;
            m_lastObstacleX = m_obstacleX;
            i++;
        } while(m_lastObstableY > -m_levelHeight +(m_minObstacleYSpace*2));
    }

    public LevelGoal getGoal()
    {
        return new LevelGoal(0, -m_levelHeight -(m_worldHeight/2), m_worldWidth, 100);
    }

    private void addPowerUp()
    {
        if(m_lastPowerUp != 0 && Math.abs(m_lastObstableY - m_obstacleY) > m_lastPowerUp )
        {
            float puX = m_obstacleX;

            if(m_levelData.m_hasHoles)
            {
                if(puX <= m_worldWidth/2)
                {
                    puX = m_obstacleX-m_puSize;
                }
                else
                {
                    puX = m_obstacleX+m_puSize;
                }

            }
            else
            {
                if(puX <= m_worldWidth/2)
                {
                    puX += m_obstacleWidth;
                }
                else
                {
                    puX -= m_obstacleWidth;
                }
            }

            switch (m_levelData.m_numOfPowerUp[m_puIndex])
            {
                case 0:
                    m_collidables.add(new PowerUpSuperSpeed(puX,(m_lastObstableY - m_obstacleY)-(m_minObstacleYSpace/4),m_puSize,m_puSize));
                    break;
                case 1:
                    m_collidables.add(new PowerUpPassThrough(puX,(m_lastObstableY - m_obstacleY)-(m_minObstacleYSpace/4),m_puSize,m_puSize));
                    break;
                case 2:
                    m_collidables.add(new PowerUpUltraRapid(puX,(m_lastObstableY - m_obstacleY)-(m_minObstacleYSpace/4),m_puSize,m_puSize));
                    break;
            }
            m_puIndex++;
            m_lastPowerUp += m_powerUpSpacing;
        }
        else if(m_lastPowerUp == 0 &&  Math.abs(m_lastObstableY - m_obstacleY) > m_powerUpSpacing)
        {
            m_lastPowerUp = m_powerUpSpacing;
        }
    }

    private void renewParameters(int i)
    {
        // Get next Simplex Noise value
        m_obstacleX = Math.abs((float) m_noise.noise(1, i*0.1)) % 1f;

        m_obstacleY = Math.abs((float) m_noise.noise(100, i*0.1)) % 1f;
        if (m_obstacleY < 0.2f)
        {
            //m_obstacleY = 0.2f;
        }

        // Distribute obstacles over screen width
        m_obstacleX = (m_obstacleX * m_worldWidth);

        // Prohibit stacking obstables (Obstacles with same or near same x value)
        if(Math.abs(m_obstacleX - m_lastObstacleX) <  m_worldWidth/6)
        {
            // Push obstacle a sixth of the screen width to the right
            m_obstacleX = ((m_obstacleX+m_worldWidth/6))%m_worldWidth;
        }

        // Snap obstacles close to the borders
        if (m_obstacleX + m_obstacleWidth > m_worldWidth - m_snapMargin)
        {
            m_obstacleX = m_worldWidth - m_obstacleWidth;
        } else if (m_obstacleX < m_snapMargin)
        {
            m_obstacleX = 0;
        }

        // Calculate Y posistion
        m_obstacleY = (m_obstacleY * m_obstacleYSpaceFactor) + m_minObstacleYSpace;
    }

    private void addPlatform()
    {
        addPlatform(m_obstacleWidth);
    }

    private void addPlatform(float width)
    {
        // Add obstacle
        m_collidables.add(new StaticObstacle(m_obstacleX, m_lastObstableY - m_obstacleY, width, 32, m_tint));
    }

    private void addHole()
    {
        if( m_worldWidth-(m_obstacleX+m_obstacleWidth) < 64)
        {
            m_obstacleX -= 64;
        }
        if(m_obstacleX < 64)
        {
            m_obstacleX = 64;
        }
        m_collidables.add(new StaticObstacle(0, m_lastObstableY - m_obstacleY, m_obstacleX, 32, m_tint));
        m_collidables.add(new StaticObstacle(m_obstacleX+m_obstacleWidth, m_lastObstableY - m_obstacleY, m_worldWidth-(m_obstacleX+m_obstacleWidth), 32, m_tint));
    }

    public Array<Collidable> getCollidables()
    {
        return m_collidables;
    }

}
