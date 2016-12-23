package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.MenuBackground;
import com.mygdx.game.levels.Chapter;
import com.mygdx.game.levels.ChapterOne;
import com.mygdx.game.levels.LevelManager;

import java.util.ArrayList;


public class LevelSelectState extends State
{
    private BitmapFont m_font;

    private ImageButton m_nextBtn, m_prevBtn, m_homeBtn;
    private Skin m_skin;
    private MenuBackground m_background;
    private Stage m_stage;
    private StateManager m_sm;
    private Table m_table, m_headerTable, m_controlTable, m_levelTable, m_levelTableInner;
    private TextButtonStyle m_levelBtnSkin, m_levelBtnDisabledSkin;
    private LevelManager m_lm = null;

    public LevelSelectState(StateManager sm)
    {

        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        m_lm = m_config.getLevelManager();

        // Background
        TextureRegion[] bg = {
            AssetLoader.cloudsbg,
            AssetLoader.clouds01,
            AssetLoader.clouds02
        };

        m_background = new MenuBackground(bg, m_config.m_worldW, m_config.m_worldH);

        //Icons and font
        m_skin = AssetLoader.buttonSkin;
        m_font = AssetLoader.slackeyfont;

        // Setup table
        m_table = new Table();
        m_table.center();
        m_table.setFillParent(true);

        m_headerTable = new Table();
        m_levelTable = new Table();
        m_levelTableInner = new Table();
        m_controlTable = new Table();

        setupBtns();
        setChapter(m_lm.getCurrentChapter());

        m_table.add(m_headerTable).expandY().bottom();
        m_table.row();

        m_table.add(m_levelTable).expandY().center();
        m_table.row();
        m_table.add(m_controlTable).expandY().top();

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

        // Home button
        ImageButtonStyle homeBtnStyle = new ImageButtonStyle();
        homeBtnStyle.up = m_skin.getDrawable("home");
        homeBtnStyle.down = m_skin.getDrawable("home");
        m_homeBtn = new ImageButton(homeBtnStyle);

        m_controlTable.add(m_prevBtn).width(100).height(100).right().padRight(30);
        m_controlTable.add(m_homeBtn).width(100).height(100).right();
        m_controlTable.add(m_nextBtn).width(100).height(100).left().padLeft(30);
    }

    private void setChapter(Chapter chapter)
    {
        setChapterTitle();

        m_levelTable.clear();
        m_levelTableInner.clear();
        ArrayList<Integer> levelIds = chapter.getLevelIds();


        for (int i = 0; i < levelIds.size(); i++)
        {
            TextButton tmp;
            int levelId = levelIds.get(i);

            if(levelId > m_lm.getIdOfLastUnlockedLevel())
            {
                tmp = new TextButton(Integer.toString(levelId), m_levelBtnDisabledSkin);
            }
            else
            {
                tmp = new TextButton(Integer.toString(levelId), m_levelBtnSkin);
                tmp.addListener(new ChangeListener()
                {
                    @Override
                    public void changed(ChangeEvent event, Actor actor)
                    {
                        m_sm.set(new PlayState(m_sm, m_lm.getLevel(Integer.parseInt(actor.getName()))));
                    }
                });
            }

            tmp.setName(Integer.toString(levelId));
            tmp.getLabel().setFontScale(0.5f);
            m_levelTableInner.add(tmp).width(200).height(100).padRight(20);

            if ((i+1) % 3 == 0)
            {
                m_levelTableInner.row().padTop(20);
            }

        }

        m_levelTableInner.padRight(-20);
        m_levelTable.add(m_levelTableInner);
    }

    private void setupClickListeners()
    {

        m_homeBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            m_sm.set(new MenuState(m_sm));
            }
        });

        m_nextBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            setChapter(m_lm.getNextChapter());
            }
        });
        m_prevBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            setChapter(m_lm.getPreviousChapter());
            }
        });
    }

    private void setChapterTitle()
    {
        m_headerTable.clear();

        Label.LabelStyle ls = new Label.LabelStyle(m_font, AssetLoader.white);
        Label label = new Label("Chapter " + m_lm.getCurrentChapterId(), ls);

        m_headerTable.add(label);
    }

    @Override
    public void update(float dt)
    {
        m_background.update(dt);
    }

    @Override
    public void render(SpriteBatch sb)
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
