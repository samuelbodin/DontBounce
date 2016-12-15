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
    private TextButton m_sixBtn;



    public UserTestMenu(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(m_stage);

        m_btnSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

        m_oneBtn = new TextButton("Test 1", m_btnSkin);
        m_oneBtn.getLabel().setFontScale(2);

        m_twoBtn = new TextButton("Test 2", m_btnSkin);
        m_twoBtn.getLabel().setFontScale(2);

        m_threeBtn = new TextButton("Test 3", m_btnSkin);
        m_threeBtn.getLabel().setFontScale(2);

        m_fourBtn = new TextButton("Test 4", m_btnSkin);
        m_fourBtn.getLabel().setFontScale(2);

        m_fiveBtn = new TextButton("Test 5", m_btnSkin);
        m_fiveBtn.getLabel().setFontScale(2);

        m_sixBtn = new TextButton("Test 6", m_btnSkin);
        m_sixBtn.getLabel().setFontScale(2);

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
        table.row();
        table.add(m_sixBtn).expandX().padTop(50f);

        m_stage.addActor(table);

    }

    private void setupClickListeners()
    {
        m_oneBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Slow moving
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
                m_sm.set(new RicState(m_sm, 1, 8, 15, 10, 3, false, bgFiles, "flatbgforeground.png"));
            }
        });

        m_twoBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Normal moving speed
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
                m_sm.set(new RicState(m_sm, 1, 8, 15, 10, 4, false, bgFiles, "flatbgforeground.png"));
            }
        });

        m_threeBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Fast moving
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
                m_sm.set(new RicState(m_sm, 1, 8, 15, 10, 5, false, bgFiles, "flatbgforeground.png"));
            }
        });

        m_fourBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Ball trail
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
                m_sm.set(new RicState(m_sm, 3, 8, 25, 10, 1, false, bgFiles, "flatbgforeground.png"));
            }
        });

        m_fiveBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Foreground
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg03.png"};
                m_sm.set(new RicState(m_sm, 4, 8, 25, 10, 2, true, bgFiles, "flatbgforeground.png"));
            }
        });

        m_sixBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // Ball trail and foreground
                String [] bgFiles = {"flatbg01.png", "flatbg02.png", "flatbg01.png"};
                m_sm.set(new RicState(m_sm, 5, 8, 25, 10, 1, true, bgFiles, "flatbgforeground.png"));
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
