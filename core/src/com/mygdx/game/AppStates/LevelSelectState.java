package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.App;
import com.mygdx.game.Basics.LevelData;
import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;

import java.util.ArrayList;


public class LevelSelectState extends State
{

    private int m_currentChapter = 0;

    private ArrayList<Chapter> m_chapters;
    private BitmapFont m_font;
    private ImageButton m_nextBtn, m_prevBtn;
    private Skin m_skin;
    private Sprite m_background;
    private Stage m_stage;
    private StateManager m_sm;
    private Table m_table, m_controlTable, m_levelTable;
    private TextureAtlas m_icons;
    private TextButtonStyle m_levelBtnSkin;
    private Chapter m_chapter;
    private Integer m_currentLevel;

    public LevelSelectState(StateManager sm)
    {

        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(App.m_worldW, App.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        // Background
        Texture bg = new Texture(Gdx.files.internal("menubg.png"));
        m_background = new Sprite(bg, 0, 0, bg.getWidth(), bg.getHeight());
        m_background.setSize(bg.getWidth(), bg.getHeight());

        // Load icons and font
        m_icons = new TextureAtlas("icons/icons.pack");
        m_skin = new Skin(m_icons);
        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));

        // Load chapters
        m_chapters = new ArrayList<Chapter>();
        m_chapters.add(new ChapterOne());

        // Setup table
        m_table = new Table();
        m_table.center();
        m_table.setFillParent(true);

        m_levelTable = new Table();
        m_controlTable = new Table();

        setupBtns();
        setChapter();

        m_table.add(m_levelTable);
        m_table.row();
        m_table.add(m_controlTable).padTop(40);

        m_stage.addActor(m_table);

        setupClickListeners();
    }

    private void setupBtns()
    {
        // Level button skin
        m_levelBtnSkin = new TextButtonStyle();
        m_levelBtnSkin.up = m_skin.getDrawable("btn");
        m_levelBtnSkin.down = m_skin.getDrawable("btn");
        m_levelBtnSkin.font = m_font;

        // Next chapter button
        ImageButtonStyle nextBtnStyle = new ImageButtonStyle();
        nextBtnStyle.up = m_skin.getDrawable("next");
        nextBtnStyle.down = m_skin.getDrawable("next");
        m_nextBtn = new ImageButton(nextBtnStyle);

        // Previous chapter button
        ImageButtonStyle prevBtnStyle = new ImageButtonStyle();
        prevBtnStyle.up = m_skin.getDrawable("prev");
        prevBtnStyle.down = m_skin.getDrawable("prev");
        m_prevBtn = new ImageButton(prevBtnStyle);

        m_controlTable.add(m_prevBtn).expandX();
        m_controlTable.add(m_nextBtn).expandX();
    }

    private void setChapter()
    {
        m_levelTable.clear();
        m_chapter = m_chapters.get(m_currentChapter);

        for (int i = 0; i < m_chapter.getLevels().length; i++)
        {
            m_currentLevel = m_chapter.getLevels()[i];

            TextButton tmp = new TextButton(m_currentLevel.toString(), m_levelBtnSkin);
            tmp.getLabel().setFontScale(0.7f);
            m_levelTable.add(tmp).width(200).height(100).padRight(20).expandX();

            if ((i+1) % 3 == 0)
            {
                m_levelTable.row().padTop(20);
            }

            tmp.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {

                    m_sm.set(new RicState(m_sm, new LevelData()));
                }
            });

        }
    }

    private void setupClickListeners()
    {
        /*
        m_returnBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new MenuState(m_sm));
            }
        });
        */
        m_nextBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                if (m_chapters.size()-1 != m_currentChapter)
                {
                    m_currentChapter++;
                    setChapter();
                }
            }
        });
        m_prevBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                if (m_currentChapter != 0)
                {
                    m_currentChapter--;
                    setChapter();
                }
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
        m_stage.getBatch().begin();
        m_stage.getBatch().draw(m_background, 0, 0, App.m_worldW, App.m_worldH);
        m_stage.getBatch().end();

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
