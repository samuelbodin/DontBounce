package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Basics.AssetLoader;

/**
 * Created by Rickard on 2016-12-09.
 */

public class UserTestMenu extends State
{

    private StateManager m_sm;
    private Stage m_stage;
    private AssetLoader m_al;
    private Skin m_btnSkin;
    private TextButton m_oneBtn;
    private TextButton m_twoBtn;
    private TextButton m_threeBtn;
    private TextButton m_fourBtn;
    private TextButton m_fiveBtn;



    public UserTestMenu(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(m_stage);

        m_btnSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

        m_oneBtn = new TextButton("Test 1", m_btnSkin);
        m_oneBtn.getLabel().setFontScale(5);

        m_twoBtn = new TextButton("Test 2", m_btnSkin);
        m_twoBtn.getLabel().setFontScale(5);

        m_threeBtn = new TextButton("Test 3", m_btnSkin);
        m_threeBtn.getLabel().setFontScale(5);

        m_fourBtn = new TextButton("Test 4", m_btnSkin);
        m_fourBtn.getLabel().setFontScale(5);

        m_fiveBtn = new TextButton("Test 5", m_btnSkin);
        m_fiveBtn.getLabel().setFontScale(5);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        setupClickListeners();


        table.add(m_oneBtn).expandX();
        table.row();
        table.add(m_twoBtn).expandX().padTop(50f);
        table.row();
        table.add(m_threeBtn).expandX().padTop(50f);
        table.row();
        table.add(m_fourBtn).expandX().padTop(50f);
        table.row();
        table.add(m_fiveBtn).expandX().padTop(50f);

        m_stage.addActor(table);

    }

    private void setupClickListeners()
    {
        m_oneBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new RicState(m_sm, 1, 8, 15, 10, false));
            }
        });

        m_twoBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new RicState(m_sm, 2, 13, 15, 15, false));
            }
        });

        m_threeBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new RicState(m_sm, 3, 8, 25, 10, false));
            }
        });

        m_fourBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new RicState(m_sm, 500, 13, 25, 15,false));
            }
        });

        m_fiveBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new RicState(m_sm, 5, 10, 10, 10, true));
            }
        });
    }

    @Override
    public void update(float dt)
    {

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

    @Override
    public void resize(int width, int height)
    {

    }
}
