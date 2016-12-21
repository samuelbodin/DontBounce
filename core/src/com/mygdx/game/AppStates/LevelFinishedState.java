package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.TimeHandler;

public class LevelFinishedState extends State
{

    private StateManager m_sm;
    private TimeHandler m_timeHandler;
    private Stage m_stage;
    private Table m_rootTable, m_buttonTable, m_labelTable, m_footerTable;
    private ImageButton m_continue, m_restart, m_mainMenu, m_soundButton;
    private Label m_finishTimeLabel, m_headerLabel;
    private TextureRegion m_background;

    public LevelFinishedState(StateManager sm)
    {
        super(sm);
        m_sm = sm;

        float viewportW = m_config.m_worldW;
        float viewportH = m_config.m_worldH;
        Skin buttonSkin;
        Label.LabelStyle fontStyle;
        BitmapFont font;
        ImageButton.ImageButtonStyle soundButtonStyle;
        ImageButton.ImageButtonStyle continueButtonStyle;

        m_background = m_assets.black;
        buttonSkin = m_assets.buttonSkin;

        m_stage = new Stage(new StretchViewport(viewportW, viewportH));

        //Tables
        m_rootTable = new Table();
        m_buttonTable = new Table();
        m_labelTable = new Table();
        m_footerTable = new Table();

        //Styles
        soundButtonStyle = new ImageButton.ImageButtonStyle();
        soundButtonStyle.up = buttonSkin.getDrawable("audioon");
        soundButtonStyle.checked = buttonSkin.getDrawable("audiooff");

        continueButtonStyle = new ImageButton.ImageButtonStyle();
        continueButtonStyle.up = buttonSkin.getDrawable("play");
        continueButtonStyle.disabled = buttonSkin.getDrawable("playgrey");

        //Buttons
        m_continue = new ImageButton(continueButtonStyle);
        m_restart = new ImageButton(buttonSkin.getDrawable("restart"));
        m_mainMenu = new ImageButton(buttonSkin.getDrawable("home"));
        m_soundButton = new ImageButton(soundButtonStyle);

        //Fonts
        font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));
        fontStyle = new Label.LabelStyle(font, Color.WHITE);

        //Labels
        m_headerLabel = new Label("LEVEL COMPLETE", fontStyle);
        m_finishTimeLabel = new Label("", fontStyle);

        //Add more conditions: "Didn't finish level", "!Already finished level"
        if (m_config.getNextLevel() == null)
        {
            m_continue.setDisabled(true);
        }

        fillStage();
        setupClickListeners();

        Gdx.input.setInputProcessor(m_stage);
    }
    public LevelFinishedState(StateManager sm, TimeHandler th)
    {
        this(sm);
        m_timeHandler = th;
    }

    private void setupClickListeners()
    {
        m_continue.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new PlayState(m_sm, m_config.getNextLevel()));
            }
        });
        m_mainMenu.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new MenuState(m_sm));
            }
        });
        m_restart.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new PlayState(m_sm, m_config.getCurrentLevel()));
            }
        });
        m_soundButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                //Toggle sound on/off
            }
        });
    }

    private void fillStage()
    {
        float btnSize = 150f;
        float btnPad = 10f;

        m_rootTable.setFillParent(true);
        m_rootTable.padTop(400);

        m_headerLabel.setFontScale(0.7f);
        m_finishTimeLabel.setFontScale(0.6f);

        m_labelTable.add(m_headerLabel);
        m_labelTable.row();
        m_labelTable.add(m_finishTimeLabel);

        m_buttonTable.add(m_restart).size(btnSize).pad(btnPad);
        m_buttonTable.add(m_continue).size(btnSize).pad(btnPad);
        m_buttonTable.add(m_mainMenu).size(btnSize).pad(btnPad);

        m_footerTable.add(m_soundButton).size(100f);

        m_rootTable.add(m_labelTable);
        m_rootTable.row();
        m_rootTable.add(m_buttonTable);
        m_rootTable.row();
        m_rootTable.add(m_footerTable).expandY().align(Align.bottomRight).padBottom(20f);

        m_stage.addActor(m_rootTable);
    }
    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sr)
    {
        Batch temp = m_stage.getBatch();
        temp.begin();
        temp.setColor(0, 0, 0, 0.5f);
        temp.draw(m_background, 0, 0);
        temp.end();

        m_finishTimeLabel.setText(m_timeHandler.getTimeString() + "s");
        m_stage.draw();
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void dispose()
    {
        m_stage.dispose();
    }

    @Override
    public void resize(int width, int height)
    {

    }
}
