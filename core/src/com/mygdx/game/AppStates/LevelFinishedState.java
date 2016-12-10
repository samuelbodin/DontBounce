package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.App;

/**
 * Created by Rickard on 2016-12-09.
 */

public class LevelFinishedState extends State
{

    StateManager m_sm;
    public LevelFinishedState(StateManager sm)
    {
        super(sm);
        m_sm = sm;
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sr)
    {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            m_sm.set(new RicState(m_sm));
        }
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }
}
