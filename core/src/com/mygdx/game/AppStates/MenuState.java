package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Basics.AssetLoader;

public class MenuState extends State
{

    private StateManager m_sm;
    private Stage m_stage;
    private AssetLoader m_al;
    private Skin m_btnSkin;
    private TextButton m_playBtn;
    private TextButton m_levelBtn;
    private TextButton m_continueBtn;

    public MenuState(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(App.m_worldW, App.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        m_btnSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

        m_playBtn = new TextButton("PLAY", m_btnSkin);
        m_playBtn.getLabel().setFontScale(3);

        m_levelBtn = new TextButton("SELECT LEVEL", m_btnSkin);
        m_levelBtn.getLabel().setFontScale(3);

        m_continueBtn = new TextButton("CONTINUE", m_btnSkin);
        m_continueBtn.getLabel().setFontScale(3);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        setupClickListeners();


        table.add(m_playBtn).expandX();
        table.row();
        table.add(m_continueBtn).expandX().padTop(30f);
        table.row();
        table.add(m_levelBtn).expandX().padTop(30f);

        m_stage.addActor(table);

    }

    private void setupClickListeners()
    {
        m_playBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // PlayState
               //m_sm.set(new TestState(m_sm));
            }
        });

        m_continueBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                //m_sm.pop();
            }
        });

        m_levelBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new LevelSelectState(m_sm));
            }
        });
    }

    @Override
    public void update(float dt)
    {

    }


    public void resize(int width, int height)
    {
        m_stage.getViewport().update(width, height);
    }

    @Override
    public void render(SpriteBatch sr)
    {
        m_stage.draw();
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void dispose()
    {

    }
}
