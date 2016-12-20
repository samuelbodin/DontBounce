package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.mygdx.game.Basics.MenuBackground;

public class MenuState extends State
{

    private BitmapFont m_font;
    private ImageButton m_playBtn, m_levelBtn, m_soundBtn;
    //private Label m_heading;
    private Image m_logo;
    private Skin m_skin;
    private Stage m_stage;
    private StateManager m_sm;
    private TextureAtlas m_icons;
    private MenuBackground m_background;

    public MenuState(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        // Background
        Texture[] bg = {new Texture(Gdx.files.internal("cloudsbg.png")),
                        new Texture(Gdx.files.internal("clouds01.png")),
                        new Texture(Gdx.files.internal("clouds02.png"))};
        m_background = new MenuBackground(bg, m_config.m_worldW, m_config.m_worldH);

        m_icons = new TextureAtlas("buttons/buttons.atlas");
        m_skin = m_assets.buttonSkin;


        // Heading & Font
        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));

        Label.LabelStyle ls = new Label.LabelStyle(m_font, new Color(1,0.65f,0,0.8f));

        //m_heading = new Label("Don't Bounce", ls);
        //m_heading.setFontScale(0.6f);
        m_logo = new Image(m_skin.getDrawable("logo"));


        // Buttons
        ImageButton.ImageButtonStyle playBtnStyle = new ImageButton.ImageButtonStyle();
        playBtnStyle.up = m_skin.getDrawable("play");
        playBtnStyle.down = m_skin.getDrawable("play");

        ImageButton.ImageButtonStyle levelBtnStyle = new ImageButton.ImageButtonStyle();
        levelBtnStyle.up = m_skin.getDrawable("levelselect");
        levelBtnStyle.down = m_skin.getDrawable("levelselect");

        ImageButton.ImageButtonStyle soundBtnStyle = new ImageButton.ImageButtonStyle();
        soundBtnStyle.up = m_skin.getDrawable("audioon");
        soundBtnStyle.checked = m_skin.getDrawable("audiooff");

        m_playBtn = new ImageButton(playBtnStyle);
        m_levelBtn = new ImageButton(levelBtnStyle);
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
               //m_sm.set(new TestState(m_sm));
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
    }



}
