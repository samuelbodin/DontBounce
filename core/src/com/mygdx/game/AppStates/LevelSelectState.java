package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.MenuBackground;
import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;

import java.util.ArrayList;


public class LevelSelectState extends State
{

    private int m_currentChapter = 0;
    private int m_currentLevel = 0;
    private int m_unlockedChapter = 0;
    private int m_unlockedLevel = 0;

    private ArrayList<Chapter> m_chapters;
    private BitmapFont m_font;
    private ImageButton m_nextBtn, m_prevBtn;
    private Skin m_skin;
    private MenuBackground m_background;
    private Stage m_stage;
    private StateManager m_sm;
    private Table m_table, m_controlTable, m_levelTable;
    private TextureAtlas m_icons;
    private TextButtonStyle m_levelBtnSkin, m_levelBtnDisabledSkin;
    private Chapter m_chapter;

    public LevelSelectState(StateManager sm)
    {

        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        // Load saved levels
        m_unlockedLevel = m_config.m_currentLevel;
        m_unlockedChapter = m_config.m_currentChapter;

        // Background
        TextureRegion[] bg = {m_assets.cloudsbg,
                m_assets.clouds01,
                m_assets.clouds02};
        m_background = new MenuBackground(bg, m_config.m_worldW, m_config.m_worldH);

        //Icons and font
        m_skin = m_assets.buttonSkin;
        m_font = m_assets.slackeyfont;

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

        // Disabled level button skin
        m_levelBtnDisabledSkin = new TextButtonStyle();
        m_levelBtnDisabledSkin.up = m_skin.getDrawable("btngrey");
        m_levelBtnDisabledSkin.down = m_skin.getDrawable("btngrey");
        m_levelBtnDisabledSkin.font = m_font;

        // Previous chapter button
        ImageButtonStyle prevBtnStyle = new ImageButtonStyle();
        prevBtnStyle.up = m_skin.getDrawable("prev");
        prevBtnStyle.down = m_skin.getDrawable("prev");
        m_prevBtn = new ImageButton(prevBtnStyle);

        // Next chapter button
        ImageButtonStyle nextBtnStyle = new ImageButtonStyle();
        nextBtnStyle.up = m_skin.getDrawable("next");
        nextBtnStyle.down = m_skin.getDrawable("next");
        m_nextBtn = new ImageButton(nextBtnStyle);

        m_controlTable.add(m_nextBtn).width(m_config.m_worldW/4).height(m_config.m_worldW/4).left().pad(10);
        m_controlTable.add(m_prevBtn).width(m_config.m_worldW/4).height(m_config.m_worldW/4).right().pad(10);
    }

    private void setChapter()
    {
        m_levelTable.clear();
        m_chapter = m_chapters.get(m_currentChapter);

        //Gdx.app.log("SB levelSelectState:", "current level " + m_currentLevel);
        //Gdx.app.log("SB levelSelectState:", "current chapter " + m_currentChapter);

        for (int i = 0; i < m_chapter.getLevels().size(); i++)
        {
            TextButton tmp;
            m_currentLevel = m_chapter.getLevels().get(i);

            if((m_currentLevel - 1) > m_unlockedLevel)
            {
                tmp = new TextButton(Integer.toString(m_currentLevel), m_levelBtnDisabledSkin);
                //Gdx.app.log("SB", "currentLevel" + m_currentLevel);
                //Gdx.app.log("SB", "unlockedLevel" + m_unlockedLevel);
            }
            else
            {
                tmp = new TextButton(Integer.toString(m_currentLevel), m_levelBtnSkin);
                tmp.addListener(new ChangeListener()
                {
                    @Override
                    public void changed(ChangeEvent event, Actor actor)
                    {
                        m_sm.set(new PlayState(m_sm, m_config.getLevel(Integer.parseInt(actor.getName()))));
                    }
                });
            }

            tmp.setName(Integer.toString(m_currentLevel));
            tmp.getLabel().setFontScale(0.5f);
            m_levelTable.add(tmp).width(200).height(100).padRight(20).expandX();

            if ((i+1) % 3 == 0)
            {
                m_levelTable.row().padTop(20);
            }

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
        m_background.update(dt);
    }

    @Override
    public void render(SpriteBatch sr)
    {
        m_stage.getBatch().begin();
        m_background.render(m_stage.getBatch());
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
        m_background.dispose();
    }

    @Override
    public void resize(int width, int height)
    {

    }
}
