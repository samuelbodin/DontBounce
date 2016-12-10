package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;


/**
 * Created by Rickard on 2016-12-10.
 */

public class PauseState extends State
{
    private StateManager m_sm;
    private static final float m_viewportWidth = App.m_worldW;
    private static final float m_viewportHeight = App.m_worldH;
    private Viewport m_viewport;
    private Sprite m_box;
    private Ball m_ball;
    private BitmapFont font;

    public PauseState(StateManager sm)
    {
        super(sm);
        m_sm = sm;
        font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey.fnt"),Gdx.files.internal("slackeyfont/slackey.png"),false);
        m_box = new Sprite(new Texture("obs5.png"));

        m_viewport = new FitViewport(m_viewportWidth, m_viewportHeight, m_cam);
        m_viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        m_viewport.apply(true);

        m_box.setOrigin(0,0);
        m_box.setScale(m_viewportWidth/m_box.getWidth());
        m_box.setPosition(0,m_viewportHeight/2);

        m_ball = new Ball(m_box.getX(), m_box.getY(), m_viewportWidth/40);
        m_cam.setBall(m_ball);
        m_cam.update();
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sr)
    {
        sr.setProjectionMatrix(m_cam.combined);

        m_box.draw(sr);
        font.draw(sr, "PAUS", App.m_worldW/2,App.m_worldH/2);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            m_sm.pop();
            //m_sm.set(new UserTestMenu(m_sm));
        }
    }

    @Override
    public void dispose()
    {
        m_box.getTexture().dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        
    }
}
