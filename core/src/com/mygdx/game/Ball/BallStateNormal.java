package com.mygdx.game.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.App;
import com.mygdx.game.Basics.AssetLoader;
import com.mygdx.game.Basics.InputHandler;

import org.w3c.dom.ranges.Range;

import java.util.Random;


public class BallStateNormal extends BallState
{

    Sound m_sound[] = null;

    public BallStateNormal(Ball b)
    {
        super(b);
        setupSprite();
        m_sound = setupSound();
        AssetLoader.audio.musicLevelNormalStart();
        m_defaultGravityModifier = 1f;
        m_gravityModifier = 1f;
        m_onCollisionGravityModifier = 1f;
    }

    @Override
    protected boolean hasHistory()
    {
        return true;
    }

    @Override
    protected boolean hasOnCollision()
    {
        return true;
    }

    private void setupSprite()
    {
        m_sprite = new Sprite(AssetLoader.flatballgrey);
        m_sprite.setOriginCenter();
        m_sprite.setColor(0.75f,0.2f,0.15f,1);
    }

    @Override
    public void updateSprite(float x, float y)
    {
        m_sprite.setPosition(x, y);
    }

    @Override
    protected void setSpriteSize(float width , float height)
    {
        m_sprite.setSize(width, height);
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
    }

    @Override
    protected void playBounceSound(float velY)
    {
        if(m_sound != null)
        {
            Random rnd = new Random();
            m_sound[rnd.nextInt(m_sound.length)].play(1.0f * (velY/700) + 0.2f, (float)MathUtils.clamp(velY, -0.5f, 1) + 1f, 0);
        }

    }

    @Override
    public String toString()
    {
        String str = super.toString();


        return str;
    }



    @Override
    public void dispose()
    {

    }

/*    private Sound[] setupSound()
    {
        Sound[] sound = new Sound[4];
        sound[0] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce01.wav"));
        sound[1] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce02.wav"));
        sound[2] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce03.wav"));
        sound[3] = Gdx.audio.newSound(Gdx.files.internal("sound/bounce04.wav"));
        return sound;
    }*/

}
