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

public class MenuState extends State
{

    private Stage m_stage;

    private Skin m_btnSkin;
    private TextButton m_playBtn;
    private TextButton m_levelBtn;
    private TextButton m_continueBtn;


    public MenuState(StateManager sm)
    {
        super(sm);

        m_stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(m_stage);

        btnSkin = new Skin(Gdx.files.internal(""));

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        m_playBtn = new TextButton("PLAY", btnSkin);

        setupClickListeners();



        table.add(m_playBtn).expandX();
        table.row();
        table.add(m_levelBtn).expandX().padTop(30f);
        table.row();
        table.add(m_continueBtn).expandX().padTop(30f);

        m_stage.addActor(table);

    }

    private void setupClickListeners()
    {
        m_playBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.log("SB", "foobar");
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
}
