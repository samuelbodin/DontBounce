package com.mygdx.game.AppStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Basics.AudioHandler;
import com.mygdx.game.Basics.Config;

import java.util.Stack;

public class StateManager
{
    public Stack<State> m_states = null;
    public Config m_config = null;
    public AudioHandler m_ah = null;

    public StateManager(Config config)
    {
        m_config = config;
        m_ah = m_config.getAudioHandler();
        m_states = new Stack();
    }

    public void set(State s)
    {
        while(!m_states.empty())
        {
            m_states.pop().dispose();
        }

        m_states.push(s);
    }

    public void push(State s)
    {
        m_states.push(s);
    }

    public void pop()
    {
        //Gdx.app.log("RL", m_states.peek().toString());
        //m_states.peek().dispose();
        m_states.pop().dispose();
    }

    public State peek()
    {
        return m_states.peek();
    }

    public void update(float dt)
    {
        m_states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        for(State s : m_states)
        {
            s.render(sb);
        }
    }

    public void resize(int width, int height)
    {
        m_states.peek().resize(width, height);
    }

}
