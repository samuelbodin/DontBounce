package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.TimeHandler;
import com.mygdx.game.levels.LevelManager;

public class LevelFinishedState extends State
{

    private StateManager m_sm;
    private TimeHandler m_timeHandler;
    private Stage m_stage;
    private Table m_rootTable, m_buttonTable, m_labelTable;
    private ImageButton m_continue, m_restart, m_mainMenu;
    private Label m_finishTimeLabel, m_headerLabel;
    private TextureRegion m_background;
    private LevelManager m_lm = null;
    private LevelData m_level = null;

    private Animation m_animation;
    private float m_elapsedTime = 0;

    private LevelFinishedState(StateManager sm)
    {
        super(sm);
        m_sm = sm;

        m_lm = m_config.getLevelManager();

        float viewportW = m_config.m_worldW;
        float viewportH = m_config.m_worldH;
        Skin buttonSkin;
        Label.LabelStyle fontStyle;
        BitmapFont font;
        ImageButton.ImageButtonStyle continueButtonStyle;

        m_background = AssetLoader.black;
        buttonSkin = AssetLoader.buttonSkin;

        m_stage = new Stage(new StretchViewport(viewportW, viewportH));

        //Tables
        m_rootTable = new Table();
        m_buttonTable = new Table();
        m_labelTable = new Table();

        //Styles
        continueButtonStyle = new ImageButton.ImageButtonStyle();
        continueButtonStyle.up = buttonSkin.getDrawable("next");
        continueButtonStyle.disabled = buttonSkin.getDrawable("nextgrey");

        //Buttons
        m_continue = new ImageButton(continueButtonStyle);
        m_restart = new ImageButton(buttonSkin.getDrawable("restart"));
        m_mainMenu = new ImageButton(buttonSkin.getDrawable("home"));

        //Fonts
        font = AssetLoader.slackeyfont;
        fontStyle = new Label.LabelStyle(font, Color.WHITE);

        //Labels
        m_headerLabel = new Label("LEVEL COMPLETE", fontStyle);
        m_finishTimeLabel = new Label("", fontStyle);
        
        fillStage();
        setupClickListeners();

        Gdx.input.setInputProcessor(m_stage);
        confetti();
    }

    LevelFinishedState(StateManager sm, TimeHandler th, LevelData level)
    {
        this(sm);
        m_timeHandler = th;
        m_level = level;

        // ATM returns true for dev.
        if(m_lm.levelWasCompleted(th, level))
        {
            // Level was completed
            m_continue.setDisabled(false);


            if(!m_lm.unlockNextLevel(level))
            {
                Gdx.app.log("JS", "Game Complete! Now go buy DLC");
            }

            confetti();
        }
    }

    private void setupClickListeners()
    {
        m_continue.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new PlayState(m_sm, m_level));
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
                m_sm.set(new PlayState(m_sm, m_lm.getLevel(m_level.m_levelId)));
            }
        });
    }

    private void fillStage()
    {
        float btnSize = 150f;
        float btnPad = 10f;

        m_rootTable.setFillParent(true);

        m_headerLabel.setFontScale(0.7f);
        m_finishTimeLabel.setFontScale(0.6f);

        m_labelTable.add(m_headerLabel);
        m_labelTable.row();
        m_labelTable.add(m_finishTimeLabel);

        m_buttonTable.add(m_restart).size(btnSize).pad(btnPad);
        m_buttonTable.add(m_mainMenu).size(btnSize).pad(btnPad);
        m_buttonTable.add(m_continue).size(btnSize).pad(btnPad);

        m_rootTable.add(m_labelTable);
        m_rootTable.row();
        m_rootTable.add(m_buttonTable);

        m_stage.addActor(m_rootTable);
    }
    @Override
    public void update(float dt)
    {
        m_elapsedTime += dt;
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

        if(m_timeHandler.getTime() <= 0f) // IF NEXT LEVEL IS UNLOCKED
        {
            m_headerLabel.setText("LEVEL FAILED");
            m_finishTimeLabel.setText("");
            m_continue.setDisabled(true);
        }
        else
        {
            m_finishTimeLabel.setText(m_timeHandler.getTimeString() + "s");
        }

        //m_finishTimeLabel.setText(m_timeHandler.getTimeString() + "s");
        m_stage.draw();

        if(m_timeHandler.getTime() > 0f) // IF NEXT LEVEL IS UNLOCKED
        {
            m_stage.getBatch().begin();
            m_stage.getBatch().draw(m_animation.getKeyFrame(m_elapsedTime), (720 - 512) / 2, 0);
            m_stage.getBatch().end();
        }
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

    private void confetti()
    {
        TextureAtlas atlas = AssetLoader.m_confetti;
        Array<TextureAtlas.AtlasRegion> region = atlas.findRegions("confetti");
        m_animation = new Animation(0.05f, region);
    }
}
