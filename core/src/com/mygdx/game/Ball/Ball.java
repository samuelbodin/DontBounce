package com.mygdx.game.Ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Basics.LevelData;

public class Ball
{
    float m_radius = 0f;
    Vector2 m_position = null;
    Vector2 m_velocity = null;
    private BallState m_state = null;
    private BallPositionHistory m_history = null;
    float m_worldW = 0f;
    float m_gravity = 0f;
    float m_maxSpeed = 0f;
    float m_defaultMaxSpeed = 0f;
    float m_userInput = 0f;
    int m_iterator = 0;

    public Ball (float x, float y, float r, float worldW, LevelData ld)
    {
        m_worldW = worldW;
        m_position = new Vector2(x,y);
        m_velocity = new Vector2(0,0);
        m_radius = r;
        m_gravity = ld.m_ballGravity;
        m_defaultMaxSpeed = ld.m_ballMaxSpeed;


        m_history = new BallPositionHistory(3);
        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position,m_radius, m_iterator++));

        m_state = new BallStateMoveable(this);
        m_state.setSpriteSize(m_radius*2,m_radius*2);
    }

    public void addToPositionX(float value)
    {
        m_userInput = value;
    }

    void handleUserInput()
    {
        m_position.x += m_userInput;
        m_userInput = 0f;
    }

    void applyGravity()
    {
        applyGravity(1);
    }

    void applyGravity(float modifier)
    {
        m_velocity.y += modifier * m_gravity;
    }

    void resetMaxSpeed()
    {
        m_maxSpeed = m_defaultMaxSpeed;
    }

    void setMaxSpeed(float maxSpeed)
    {
        m_maxSpeed = maxSpeed;
    }

    void setState(BallState s)
    {
        m_state = s;
        m_state.setSpriteSize(m_radius*2,m_radius*2);
    }

    public void update(float dt)
    {
        m_state.update(dt);

        if(m_velocity.y < m_maxSpeed)
        {
            m_velocity.y = m_maxSpeed;
        }

        m_position.add(m_velocity.x*dt,m_velocity.y*dt);

        handleUserInput();

        handleEdges();

        m_state.updateSprite(m_position.x - m_radius, m_position.y - m_radius);

        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position, m_radius, m_iterator++));
    }
    private void handleEdges()
    {
        if(m_position.x-m_radius < 0)
        {
            m_velocity.scl(0,1);
            m_position.x = m_radius;
        }
        else if(m_position.x+m_radius > m_worldW)
        {
            m_velocity.scl(0,1);
            m_position.x = m_worldW - m_radius;
        }
    }

    public void render(SpriteBatch sb)
    {
        m_state.render(sb);
    }

    public void onCollision(Vector2 pos, int side)
    {
        if(m_state.hasOnCollision())
        {
            m_state.onCollision(pos, side);
        }
        else
        {
            handleCollision(pos, side);
        }
    }

    private void handleCollision(Vector2 pos, int side)
    {
        /*
            0 - Ball is above obstacle
            1 - Ball is to the right of obstacle
            2 - Ball is below obstacle
            3 - Ball is to the left of obstacle
         */
        if(side == 0)
        {
            alignAbovePosition(pos);
            if(isOnGround())
            {
                resetVelocityY();
            }
        }
        else if(side == 1)
        {
            alignToTheRightOfPosition(pos);
        }
        else if(side == 2)
        {
            alignBelowPosition(pos);
        }
        else if(side == 3)
        {
            alignToTheLeftOfPosition(pos);
        }

        if(!isOnGround())
        {
            m_state.playBounceSound(m_velocity.y);
        }

        flipVelocityY();
    }

    void alignAbovePosition(Vector2 pos)
    {
        m_position.y = pos.y+m_radius;
    }

    void alignToTheRightOfPosition(Vector2 pos)
    {
        m_position.x = pos.x+m_radius+1;
        m_position.y--;
    }

    void alignBelowPosition(Vector2 pos)
    {
        m_position.y = pos.y-m_radius-1;
    }

    void alignToTheLeftOfPosition(Vector2 pos)
    {
        m_position.x = pos.x-m_radius-1;
        m_position.y--;
    }

    void flipVelocityY()
    {
        m_velocity.scl(0,-0.6f);
    }

    void resetVelocityY()
    {
        m_velocity.y = 0;
    }

    boolean isOnGround()
    {
        return (m_velocity.y > 0 && m_velocity.y <= -m_gravity);
    }

    public Vector2 getPosition()
    {
        return m_position;
    }
    public Vector2 getVelocity()
    {
        return m_velocity;
    }

    public Array<com.mygdx.game.Basics.Circle> getCircles()
    {
        return m_history.m_circles;
    }

    public void dispose()
    {
        m_state.dispose();
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "m_position: " + m_position.toString() + "\n";
        str += "m_radius: " + m_radius + "\n";
        str += "m_velocity: " + m_velocity.toString() + "\n";
        str += "m_state: " + m_state.toString() + "\n";

        return str;
    }
}
