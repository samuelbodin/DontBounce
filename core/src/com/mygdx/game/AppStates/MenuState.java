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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.App;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.MenuBackground;
import com.mygdx.game.levels.LevelManager;

public class MenuState extends State
{

    private BitmapFont m_font = null;
    private ImageButton m_playBtn = null;
    private ImageButton m_levelBtn = null;
    private ImageButton m_helpBtn = null;
    private ImageButton m_soundBtn = null;
    private Image m_logo = null;
    private Skin m_skin = null;
    private Stage m_stage = null;
    private StateManager m_sm = null;
    private MenuBackground m_background = null;
    private LevelManager m_lm = null;

    public MenuState(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        m_lm = m_config.getLevelManager();

        // Background
        TextureRegion[] bg = {  AssetLoader.cloudsbg,
                                AssetLoader.clouds01,
                                AssetLoader.clouds02};
        m_background = new MenuBackground(bg, m_config.m_worldW, m_config.m_worldH);

        m_skin = AssetLoader.buttonSkin;

        // Heading & Font
        m_font = AssetLoader.slackeyfont;

        Label.LabelStyle ls = new Label.LabelStyle(m_font, new Color(1,0.65f,0,0.8f));

        m_logo = new Image(m_skin.getDrawable("logo"));
        Color m_pressTintColor = new Color(0.7f, 0.7f, 0.7f, 1f);

        // Buttons
        ImageButton.ImageButtonStyle playBtnStyle = new ImageButton.ImageButtonStyle();
        playBtnStyle.up = m_skin.getDrawable("play");
        playBtnStyle.down = m_skin.newDrawable("play", m_pressTintColor);

        ImageButton.ImageButtonStyle levelBtnStyle = new ImageButton.ImageButtonStyle();
        levelBtnStyle.up = m_skin.getDrawable("levelselect");
        levelBtnStyle.down = m_skin.newDrawable("levelselect", m_pressTintColor);

        ImageButton.ImageButtonStyle helpBtnStyle = new ImageButton.ImageButtonStyle();
        helpBtnStyle.up = m_skin.getDrawable("help");
        helpBtnStyle.down = m_skin.newDrawable("help", m_pressTintColor);

        ImageButton.ImageButtonStyle soundBtnStyle = new ImageButton.ImageButtonStyle();
        soundBtnStyle.up = m_skin.getDrawable("audioon");
        soundBtnStyle.checked = m_skin.getDrawable("audiooff");

        m_playBtn = new ImageButton(playBtnStyle);
        m_levelBtn = new ImageButton(levelBtnStyle);
        m_helpBtn = new ImageButton(helpBtnStyle);
        m_soundBtn = new ImageButton(soundBtnStyle);

        // Add click listeners
        setupClickListeners();

        // Table
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        Table controls = new Table();
        Table footer = new Table();

        //table.add(m_heading).expand(0, 2).bottom();
        table.add(m_logo).width(m_config.m_worldW*0.8f).expand(0, 2).bottom();
        table.row();
        table.add(controls).expandY();
        table.row();
        table.add(footer).expandY().bottom().right();

        controls.add(m_playBtn).width(200).height(200).expandX().padRight(50);
        controls.add(m_levelBtn).width(200).height(200).expandX();

        footer.add(m_helpBtn).width(100).height(100).padBottom(20).padRight(40);
        footer.add(m_soundBtn).width(100).height(100).padBottom(20);


        m_stage.addActor(table);

    }

    private void setupClickListeners()
    {
        m_playBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // PlayState
               m_sm.set(new PlayState(m_sm, m_lm.getLastUnlockedLevel()));
            }
        });


        m_levelBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new LevelSelectState(m_sm));
            }
        });

        m_helpBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                m_sm.set(new HelpState(m_sm));
            }
        });
    }

    @Override
    public void update(float dt)
    {
        m_background.update(dt);
    }


    public void resize(int width, int height)
    {
        m_stage.getViewport().update(width, height);
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
        m_stage.dispose();
    }



}
