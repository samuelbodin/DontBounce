package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.CollisionEffect;
import com.mygdx.game.Basics.LevelData;

public class Ball
{
    private float m_radius = 0f;
    private Vector2 m_position = null;
    private Vector2 m_velocity = null;
    private BallState m_state = null;
    private BallPositionHistory m_history = null;
    private float m_worldW = 0f;
    private float m_gravity = 0f;
    private float m_maxSpeed = 0f;
    private float m_defaultMaxSpeed = 0f;
    private float m_userInput = 0f;
    private int m_iterator = 0;
    private float m_dtModifier = 1f;
    private CollisionEffect m_collisionEffect = null;
    private AssetLoader m_assetLoader = null;

    float timer = 0;

    public Ball (float x, float y, float r, float worldW, LevelData ld, AssetLoader assetLoader)
    {
        m_worldW = worldW;
        m_position = new Vector2(x,y);
        m_velocity = new Vector2(0,0);
        m_radius = r;
        m_gravity = ld.m_ballGravity;
        m_maxSpeed = m_defaultMaxSpeed = ld.m_ballMaxSpeed;
        m_assetLoader = assetLoader;

        m_history = new BallPositionHistory(3);
        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position,m_radius, m_iterator++));

        m_state = new BallStateNormal(this);
        m_state.setSpriteSize(m_radius*2,m_radius*2);

        m_collisionEffect = new CollisionEffect();
    }

    public TextureRegion getBallTexture()
    {
        return m_assetLoader.flatballgrey;
    }

    public void addToPositionX(float value)
    {
        float min = 0.5f;
        float max = 1f;
        float originFactor = Math.abs(m_velocity.y/m_defaultMaxSpeed);
        float speedFactor =  (max-min)*originFactor + min;

        m_userInput = value*speedFactor;
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

    public void setState(BallState s)
    {
        m_state = s;
        m_state.setSpriteSize(m_radius*2,m_radius*2);
        m_state.updateSprite(m_position.x, m_position.y);
        //m_state.updateSprite(m_position.x-m_radius, m_position.y-m_radius);
    }

    public void resetState()
    {
        m_state = new BallStateNormal(this);
        m_state.setSpriteSize(m_radius*2,m_radius*2);
    }

    public void resetVariables()
    {
        resetDtModifier();
        resetMaxSpeed();
    }

    public void update(float dt)
    {
        m_state.update(dt);

        if(m_velocity.y < m_maxSpeed)
        {
            m_velocity.y = m_maxSpeed;
        }

        m_position.add(m_velocity.x*dt,m_velocity.y*dt*m_dtModifier );

        handleUserInput();

        handleEdges();

        //m_state.updateSprite(m_position.x - m_radius, m_position.y - m_radius);
        m_state.updateSprite(m_position.x, m_position.y);

        m_history.addToHistory(new com.mygdx.game.Basics.Circle(m_position, m_radius, m_iterator++));

        m_collisionEffect.update(dt);

        timer += dt;

    }
    private void handleEdges()
    {
        if(m_position.x < 0)
        {
            m_velocity.scl(0,1);
            m_position.x = 0;
        }
        else if(m_position.x+m_radius*2 > m_worldW)
        {
            m_velocity.scl(0,1);
            m_position.x = m_worldW - m_radius*2;
        }
    }

    public void render(SpriteBatch sb)
    {
        m_state.render(sb);
        m_collisionEffect.render(sb);
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

        collisionEffect(pos, side, "splash", 100);
        collisionSound();

        flipVelocityY();
    }

    void collisionSound()
    {
        if(!isOnGround())
        {
            m_state.playBounceSound(m_velocity.y);
        }
    }

    void alignAbovePosition(Vector2 pos)
    {
        m_position.y = pos.y+1;
    }

    void alignToTheRightOfPosition(Vector2 pos)
    {
        m_position.x = pos.x+1;
        m_position.y--;
    }

    void alignBelowPosition(Vector2 pos)
    {
        m_position.y = pos.y-m_radius*2-1;
    }

    void alignToTheLeftOfPosition(Vector2 pos)
    {
        m_position.x = pos.x-m_radius*2-1;
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

    public void setDtModifier(float dtm)
    {
        m_dtModifier = dtm;
    }

    public float getDtModifier()
    {
        return m_dtModifier;
    }

    public void resetDtModifier()
    {
        m_dtModifier = 1f;
    }

    public void collisionEffect(Vector2 collisionPosition, int side, String name)
    {
        m_collisionEffect.setAnimation(name);
        m_collisionEffect.startEffect(collisionPosition, side);
    }

    public void collisionEffect(Vector2 collisionPosition, int side, String name, int spriteMove)
    {
        m_collisionEffect.setAnimation(name);
        m_collisionEffect.startEffect(collisionPosition, side, spriteMove);
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
