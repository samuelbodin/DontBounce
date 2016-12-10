package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AppStates.RicState;
import com.mygdx.game.AppStates.StateManager;
import com.mygdx.game.AppStates.MenuState;
import com.mygdx.game.AppStates.UserTestMenu;

public class App extends ApplicationAdapter
{
    public static final int m_worldW = 720;
    public static final int m_worldH = 1280;
    public static int m_screenW = 0;
    public static int m_screenH = 0;
    public static float m_aspectR = 0.0f;

    private StateManager m_sm = null;
    private SpriteBatch m_sb = null;


	private void getScreenDimensions()
	{
		m_screenH = Gdx.graphics.getHeight();
		m_screenW = Gdx.graphics.getWidth();
		m_aspectR = (m_screenH/m_screenW);
	}
	
	@Override
	public void create ()
	{
        getScreenDimensions();
        m_sm = new StateManager();
        m_sb = new SpriteBatch();
		m_sm.set(new UserTestMenu(m_sm));
		m_sb.enableBlending();
        Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render ()
	{
		super.render();
        m_sm.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        m_sb.begin();
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

	}
}
