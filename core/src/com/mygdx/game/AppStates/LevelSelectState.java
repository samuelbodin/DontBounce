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

public class LevelSelectState extends State
{

    private StateManager m_sm;
    private Stage m_stage;
    private Skin m_btnSkin;
    private TextButton m_returnBtn;

    public LevelSelectState(StateManager sm)
    {

        super(sm);

        m_sm = sm;

        m_stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(m_stage);

        m_btnSkin = new Skin(Gdx.files.internal("uiskin.json"));

        m_returnBtn = new TextButton("RETURN", m_btnSkin);
        m_returnBtn.getLabel().setFontScale(5);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        setupClickListeners();


        table.add(m_returnBtn).expandX();

        m_stage.addActor(table);
    }

    private void setupClickListeners()
    {
        m_returnBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new MenuState(m_sm));
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
