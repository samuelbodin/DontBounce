package com.mygdx.game.AppStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.MenuBackground;
import com.mygdx.game.levels.LevelManager;

/**
 * Created by Rickard on 2016-12-26.
 */

public class HelpState extends State
{

    private BitmapFont m_font = null;
    private Label.LabelStyle m_labelStyle = null;
    private ImageButton m_homeBtn = null;
    private Image m_puSuperSpeed = null;
    private Image m_puPassThrough = null;
    private Image m_puUltraRapid = null;
    private Image m_tiltPhone = null;
    private Skin m_skin = null;
    private Stage m_stage = null;
    private StateManager m_sm = null;
    private MenuBackground m_background = null;

    private TextureRegion m_blackBackground;

    public HelpState(StateManager sm)
    {
        super(sm);

        m_sm = sm;

        m_stage = new Stage(new StretchViewport(m_config.m_worldW, m_config.m_worldH));
        Gdx.input.setInputProcessor(m_stage);

        // Background
        TextureRegion[] bg = {  AssetLoader.cloudsbg,
                AssetLoader.clouds01,
                AssetLoader.clouds02};
        m_background = new MenuBackground(bg, m_config.m_worldW, m_config.m_worldH);
        m_blackBackground = AssetLoader.black;

        m_skin = AssetLoader.buttonSkin;

        // Heading & Font
        m_font = AssetLoader.slackeyfont;
        Label.LabelStyle m_labelStyle = new Label.LabelStyle(m_font, new Color(1,1,1,0.8f));

        // Images
        m_puSuperSpeed = new Image(AssetLoader.speedup);
        m_puPassThrough = new Image(AssetLoader.passthrough);
        m_puUltraRapid = new Image(AssetLoader.ultrarapid);
        m_tiltPhone = new Image(AssetLoader.buttonSkin.getDrawable("tiltphone"));

        Color m_pressTintColor = new Color(0.7f, 0.7f, 0.7f, 1f);
        // Buttons
        ImageButton.ImageButtonStyle homeBtnStyle = new ImageButton.ImageButtonStyle();
        homeBtnStyle.up = m_skin.getDrawable("home");
        homeBtnStyle.down = m_skin.newDrawable("home", m_pressTintColor);

        m_homeBtn = new ImageButton(homeBtnStyle);

        // Add click listeners
        setupClickListeners();

        // Table
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        Table footer = new Table();

        // Game info heading
        Label pugihlabel = new Label("Game Objectives", m_labelStyle);
        pugihlabel.setWrap(true);
        pugihlabel.setWidth(m_config.m_worldW);
        pugihlabel.setFontScale(0.4f);
        table.add(pugihlabel).colspan(2).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(10).padTop(30);

        table.row();

        // Game info
        Label pugilabel = new Label("Fall to the finish line before the time is up. Sounds easy? It is, if you Don't Bounce!", m_labelStyle);
        pugilabel.setWrap(true);
        pugilabel.setWidth(m_config.m_worldW);
        pugilabel.setFontScale(0.3f);
        table.add(pugilabel).colspan(2).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(20);

        table.row();

        // Power up info
        Label pupulabel = new Label("Power Ups", m_labelStyle);
        pupulabel.setWrap(true);
        pupulabel.setWidth(m_config.m_worldW);
        pupulabel.setFontScale(0.4f);
        table.add(pupulabel).colspan(2).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(10);

        table.row();

        // Super Speed
        table.add(m_puSuperSpeed).width(64).height(64).center().padLeft(10);
        Label pusslabel = new Label("Super Speed - You'll go really fast, be aware!", m_labelStyle);
        pusslabel.setWrap(true);
        //pusslabel.setWidth(m_config.m_worldW-100);
        pusslabel.setFontScale(0.3f);
        pusslabel.pack();
        table.add(pusslabel).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(10);

        table.row();

        // Pass Through
        table.add(m_puPassThrough).width(64).height(64).center().padLeft(10);
        Label puptlabel = new Label("Pass Through - No more bounce with this one!", m_labelStyle);
        puptlabel.setWrap(true);
        //puptlabel.setWidth(m_config.m_worldW-100);
        puptlabel.setFontScale(0.3f);
        puptlabel.pack();
        table.add(puptlabel).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(10);

        table.row();

        // Ultra Rapid
        table.add(m_puUltraRapid).width(64).height(64).center().padLeft(10);
        Label puurlabel = new Label("Ultra Rapid - Slows everything down but you!", m_labelStyle);
        puurlabel.setWrap(true);
        //puurlabel.setWidth(m_config.m_worldW-100);
        puurlabel.setFontScale(0.3f);
        table.add(puurlabel).center().left().expandX().fill().padRight(10).padLeft(10);

        table.row();


        // Controls info
        Label cilabel = new Label("Controls", m_labelStyle);
        cilabel.setWrap(true);
        cilabel.setWidth(m_config.m_worldW);
        cilabel.setFontScale(0.4f);
        table.add(cilabel).colspan(2).center().left().expandX().fill().padRight(10).padLeft(10).padBottom(10).height(100);

        table.row();

        // Tilt
        table.add(m_tiltPhone).width(64).height(64).center().padLeft(10);
        Label citplabel = new Label("Tilt the phone to avoid the platforms.", m_labelStyle);
        citplabel.setWrap(true);
        citplabel.setWidth(m_config.m_worldW-100);
        citplabel.setFontScale(0.3f);
        table.add(citplabel).center().left().expandX().fill().padRight(20).padLeft(20);

        table.row();

        table.add(footer).expandY().bottom().left();

        footer.add(m_homeBtn).width(100).height(100).padBottom(20).padLeft(40);


        m_stage.addActor(table);
    }

    private void setupClickListeners()
    {
        m_homeBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                // MenuState
                m_sm.set(new MenuState(m_sm));
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
        m_stage.getBatch().setColor(0, 0, 0, 0.5f);
        m_stage.getBatch().draw(m_blackBackground, 0, 0);
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
        m_stage.dispose();
    }

    @Override
    public void resize(int width, int height)
    {
        m_stage.getViewport().update(width, height);
    }
}
