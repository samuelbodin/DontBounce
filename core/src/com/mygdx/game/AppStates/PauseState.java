package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.AudioHandler;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.levels.LevelManager;

import static com.mygdx.game.Basics.AssetLoader.buttonSkin;


public class PauseState extends State
{
    private StateManager m_sm;
    private Stage m_stage;
    private Skin m_skin;
    private BitmapFont m_font;
    private ImageButton m_return, m_restart, m_mainMenu, m_soundButton;
    private ImageButton.ImageButtonStyle soundButtonStyle;
    private Table m_labelTable, m_buttonTable, m_rootTable, m_footerTable;
    private Label m_header;
    private LevelManager m_lm = null;
    private Label.LabelStyle m_headerSkin;
    private TextureRegion m_background;
    private int m_input = -1;
    private LevelData m_ld = null;

    public PauseState(StateManager sm, LevelData ld)
    {
        super(sm);
        m_sm = sm;
        m_ld = ld;
        m_lm = m_config.getLevelManager();
        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));

        loadAssets();
        setupTables();
        setupLabels();
        setupButtons();

        fillStage();
        setupClickListeners();
        Gdx.input.setInputProcessor(m_stage);
    }

    private void setupButtons()
    {
        //Styles
        soundButtonStyle = new ImageButton.ImageButtonStyle();
        soundButtonStyle.up = buttonSkin.getDrawable("audioon");
        soundButtonStyle.checked = buttonSkin.getDrawable("audiooff");

        //Buttons
        m_return = new ImageButton(m_skin.getDrawable("play"));
        m_restart = new ImageButton(m_skin.getDrawable("restart"));
        m_mainMenu = new ImageButton(m_skin.getDrawable("home"));
        m_soundButton = new ImageButton(soundButtonStyle);
    }

    private void setupLabels()
    {
        //Styles
        m_headerSkin = new Label.LabelStyle(m_font, Color.WHITE);
        //Labels
        m_header = new Label("PAUSED", m_headerSkin);
    }
    private void setupTables()
    {
        m_labelTable = new Table();
        m_rootTable = new Table();
        m_buttonTable = new Table();
        m_footerTable = new Table();
    }

    private void loadAssets()
    {
        m_font = AssetLoader.slackeyfont;
        m_background = AssetLoader.black;
        m_skin = buttonSkin;
    }

    private void fillStage()
    {
        float buttonSize = 150f;
        float buttonPad = 10f;
        m_rootTable.setFillParent(true);
        m_rootTable.padTop(400);

        m_header.setFontScale(0.7f);

        m_labelTable.add(m_header);

        m_buttonTable.add(m_restart).size(buttonSize).pad(buttonPad);
        m_buttonTable.add(m_return).size(buttonSize).pad(buttonPad);
        m_buttonTable.add(m_mainMenu).size(buttonSize).pad(buttonPad);

        if(m_ah.isMuted())
        {
            m_soundButton.setChecked(true);
        }

        m_footerTable.add(m_soundButton).size(100f);

        m_rootTable.add(m_labelTable);
        m_rootTable.row();
        m_rootTable.add(m_buttonTable);
        m_rootTable.row();
        m_rootTable.add(m_footerTable).expandY().align(Align.bottomRight).padBottom(20f);

        m_stage.addActor(m_rootTable);
    }

    private void setupClickListeners()
    {
        m_return.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_input = 1;
            }
        });
        m_mainMenu.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_input = 2;
            }
        });
        m_restart.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_input = 0;
            }
        });
        m_soundButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            m_input = 3;
            }
        });
    }

    private void resetInput()
    {
        m_input = -1;
        m_stage.unfocusAll();
    }

    @Override
    public void update(float dt)
    {
        if(m_input == 0)
        {
            m_sm.set(new PlayState(m_sm, m_ld));
            resetInput();
        }
        else if(m_input == 1)
        {
            m_sm.pop();
            resetInput();
        }
        else if(m_input == 2)
        {
            m_sm.set(new MenuState(m_sm));
            resetInput();
        }
        else if(m_input == 3)
        {
            m_ah.toggleMute();
            resetInput();
        }
    }

    @Override
    public void render(SpriteBatch sr)
    {
        Batch temp = m_stage.getBatch();
        temp.begin();
        temp.setColor(0, 0, 0, 0.5f);
        temp.draw(m_background, 0, 0);
        temp.end();
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
