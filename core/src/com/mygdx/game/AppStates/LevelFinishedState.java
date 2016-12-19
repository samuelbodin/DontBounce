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
    private float m_viewportW = App.m_worldW;
    private float m_viewportH = App.m_worldH;
    private float m_finishTime = 25.50f;
    private Stage m_stage;
    private Table m_rootTable, m_buttonTable, m_labelTable;
    private ImageButton m_continue, m_restart, m_mainMenu;
    private TextureAtlas m_icons;
    private Skin m_buttonSkin;
    private Label m_finishTimeLabel, m_headerLabel;
    private Label.LabelStyle m_fontStyle;
    private BitmapFont m_font;

    public LevelFinishedState(StateManager sm)
    {
        super(sm);
        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_viewportW, m_viewportH));

        m_rootTable = new Table();
        m_buttonTable = new Table();
        m_labelTable = new Table();

        m_icons = new TextureAtlas("icons/levelMenuIcons/levelMenuIcons.pack");
        m_buttonSkin = new Skin(m_icons);

        m_continue = new ImageButton(m_buttonSkin.getDrawable("continue"));
        m_restart = new ImageButton(m_buttonSkin.getDrawable("restart"));
        m_mainMenu = new ImageButton(m_buttonSkin.getDrawable("mainmenu"));

        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));
        m_fontStyle = new Label.LabelStyle(m_font, Color.WHITE);

        m_headerLabel = new Label("LEVEL COMPLETE", m_fontStyle);
        m_finishTimeLabel = new Label("Time: " + Float.toString(m_finishTime), m_fontStyle);

        fillStage();
        setupClickListeners();

        Gdx.input.setInputProcessor(m_stage);
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
