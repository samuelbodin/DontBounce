package com.mygdx.game.AppStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AppStates.State;

import java.util.Stack;

public class StateManager
{
    private Stack<State> m_states = null;

    public StateManager()
    {
        m_states = new Stack();
    }

    public void set(State s)
    {
        m_states.clear();
        m_states.push(s);
    }

    public void push(State s)
    {
        m_states.push(s);
    }

    public void pop()
    {
        m_states.pop();
    }

    public void update(float dt)
    {
        m_states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        m_states.peek().render(sb);
    }

    public void resize(int width, int height)
    {
        m_states.peek().resize(width, height);
    }

}
