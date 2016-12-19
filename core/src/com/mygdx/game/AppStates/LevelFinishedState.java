package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.App;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.Basics.TimeHandler;

public class LevelFinishedState extends State
{

    private StateManager m_sm;
    private TimeHandler m_timeHandler;
    private Stage m_stage;
    private Table m_rootTable, m_buttonTable, m_labelTable;
    private ImageButton m_continue, m_restart, m_mainMenu;
    private Label m_finishTimeLabel, m_headerLabel;

    public LevelFinishedState(StateManager sm)
    {
        super(sm);
        m_sm = sm;

        float viewportW = App.m_worldW;
        float viewportH = App.m_worldH;
        TextureAtlas icons;
        Skin buttonSkin;
        Label.LabelStyle fontStyle;
        BitmapFont font;

        m_stage = new Stage(new StretchViewport(viewportW, viewportH));

        m_rootTable = new Table();
        m_buttonTable = new Table();
        m_labelTable = new Table();

        icons = new TextureAtlas("icons/levelMenuIcons/levelMenuIcons.pack");
        buttonSkin = new Skin(icons);

        m_continue = new ImageButton(buttonSkin.getDrawable("continue"));
        m_restart = new ImageButton(buttonSkin.getDrawable("restart"));
        m_mainMenu = new ImageButton(buttonSkin.getDrawable("mainmenu"));

        font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));
        fontStyle = new Label.LabelStyle(font, Color.WHITE);

        m_headerLabel = new Label("LEVEL COMPLETE", fontStyle);
        m_finishTimeLabel = new Label("", fontStyle);

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
                m_sm.set(new RicState(m_sm, new LevelData()));
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

            }
        });
    }

    private void fillStage()
    {
        float btnSize = 150f;
        float btnPad = 10f;
        //Get value from config in the future
        Boolean lastLvl = false;

        m_rootTable.setFillParent(true);

        m_headerLabel.setFontScale(0.7f);
        m_finishTimeLabel.setFontScale(0.6f);

        m_labelTable.add(m_headerLabel);
        m_labelTable.row();
        m_labelTable.add(m_finishTimeLabel);

        m_buttonTable.add(m_restart).size(btnSize).pad(btnPad);
        if (!lastLvl)
        {
            m_buttonTable.add(m_continue).size(btnSize).pad(btnPad);
        }
        m_buttonTable.add(m_mainMenu).size(btnSize).pad(btnPad);

        m_rootTable.add(m_labelTable);
        m_rootTable.row();
        m_rootTable.add(m_buttonTable);

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

    }

    @Override
    public void resize(int width, int height)
    {

    }
}
