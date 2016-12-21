package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AppStates.StateManager;
import com.mygdx.game.AppStates.MenuState;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.Config;

public class App extends ApplicationAdapter
{
    private StateManager m_sm = null;
    private SpriteBatch m_sb = null;
	private Config m_config = null;

	@Override
	public void create ()
	{
		AssetLoader.Load();
        m_config = new Config();
        m_sm = new StateManager();
        m_sm.m_config = m_config;
        m_sb = new SpriteBatch();
        m_sm.set(new MenuState(m_sm));
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render ()
	{
		super.render();
        m_sm.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        m_sb.begin();
		m_sb.enableBlending();
        m_sm.render(m_sb);
        m_sb.end();
	}

	@Override
	public void resize(int width, int height)
	{
		m_sm.resize(width, height);
	}

	@Override
	public void dispose ()
	{
		super.dispose();
		Gdx.app.log("RL", "App desposar");
	}
}
