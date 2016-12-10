package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.App;
import com.mygdx.game.AppStates.levels.Chapter;
import com.mygdx.game.AppStates.levels.ChapterOne;
import com.mygdx.game.AppStates.levels.ChapterTwo;

import java.util.ArrayList;


public class LevelSelectState extends State
{

    private StateManager m_sm;
    private Stage m_stage;
    private Skin m_btnSkin;
    private TextButton m_returnBtn;
    private TextButton m_nextBtn;
    private TextButton m_prevBtn;
    private ArrayList<Chapter> m_chapters;
    private int m_currentChapter = 0;

    private HorizontalGroup m_navBtns;
    private HorizontalGroup m_lvlRowTop;
    private HorizontalGroup m_lvlRowBot;

    public LevelSelectState(StateManager sm)
    {

        super(sm);
        m_sm = sm;

        m_chapters = new ArrayList<Chapter>();
        m_chapters.add(new ChapterOne());
        m_chapters.add(new ChapterTwo());

        m_stage = new Stage(new StretchViewport(App.m_worldW, App.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        m_btnSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

        m_returnBtn = new TextButton("RETURN", m_btnSkin);
        m_nextBtn = new TextButton(">", m_btnSkin);
        m_prevBtn = new TextButton("<", m_btnSkin);

        m_nextBtn.pad(30f,50f,30f,50f);
        m_prevBtn.pad(30f,50f,30f,50f);
        m_returnBtn.getLabel().setFontScale(3);
        m_nextBtn.getLabel().setFontScale(3);
        m_prevBtn.getLabel().setFontScale(3);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        m_lvlRowTop = new HorizontalGroup();
        m_lvlRowBot = new HorizontalGroup();

        setChapter();

        table.add(m_lvlRowTop);
        table.row();
        table.add(m_lvlRowBot);
        table.row();

        m_navBtns = new HorizontalGroup();
        m_navBtns.addActor(m_prevBtn);
        m_navBtns.addActor(m_nextBtn);
        m_navBtns.space(235);

        table.add(m_navBtns);
        table.row().pad(10);
        table.add(m_returnBtn).expandX();

        m_stage.addActor(table);

        setupClickListeners();
    }

    private void setChapter()
    {
        Gdx.app.log("Welcome", "To my secret lair");
        m_lvlRowBot.clear();
        m_lvlRowTop.clear();
        for (int i = 0; i < 6; i++)
        {
            Integer level = m_chapters.get(m_currentChapter).getLevels()[i];
            TextButton temp = new TextButton(level.toString(), m_btnSkin);
            if (i < 3)
            {
                m_lvlRowTop.addActor(temp);
            }
            else
            {
                m_lvlRowBot.addActor(temp);
            }
        }
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
        m_nextBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.log("Welcome", "To my secret lair!");
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
