package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;


public class PauseState extends State
{
    private StateManager m_sm;
    private Stage m_stage;
    private TextureAtlas m_icons;
    private Skin m_skin;
    private BitmapFont m_font;
    private TextButton m_return;
    private TextButton.TextButtonStyle m_returnSkin;
    private Table m_layoutTable;
    private Label m_header;
    private Label.LabelStyle m_headerSkin;

    public PauseState(StateManager sm)
    {
        super(sm);
        m_sm = sm;

        m_font = new BitmapFont(Gdx.files.internal("slackeyfont/slackey100.fnt"));

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));

        //Move this to AssetLoader in the future
        m_icons = new TextureAtlas("icons/icons.pack");
        m_skin = new Skin(m_icons);

        m_layoutTable = new Table();
        m_layoutTable.setFillParent(true);

        m_returnSkin = new TextButton.TextButtonStyle();
        m_headerSkin = new Label.LabelStyle(m_font, Color.WHITE);

        m_returnSkin.up = m_skin.getDrawable("btn");
        m_returnSkin.down = m_skin.getDrawable("btn");
        m_returnSkin.font = m_font;

        m_header = new Label("PAUSED", m_headerSkin);
        m_return = new TextButton("Return to game", m_returnSkin);

        m_header.setFontScale(0.7f);
        m_return.getLabel().setFontScale(0.5f);

        m_layoutTable.add(m_header);
        m_layoutTable.row();
        m_layoutTable.add(m_return).width(600f).height(100f);

        m_stage.addActor(m_layoutTable);

        Gdx.input.setInputProcessor(m_stage);
        setupClickListeners();
    }

    private void setupClickListeners()
    {
        m_return.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.log("PW", "return click");
                m_sm.pop();
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
        sr.setProjectionMatrix(m_cam.combined);
        m_stage.draw();
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void dispose()
    {
        m_icons.dispose();
        m_skin.dispose();
        m_font.dispose();
        m_stage.dispose();
        Gdx.app.log("RL", "PausState disposar");
    }

    @Override
    public void resize(int width, int height)
    {
        
    }
}
