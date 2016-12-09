package com.mygdx.game.Obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.App;
import com.mygdx.game.Ball.Ball;

/**
 * Created by Rickard on 2016-12-08.
 */

public class LevelGoal extends Obstacle
{
    Sprite m_sprite;

    public LevelGoal(float x, float y, float w, float h)
    {
        super(x,y,w,h);
        m_texture = new Texture("goal.png");
        m_sprite = new Sprite(m_texture);
        m_sprite.setAlpha(0.5f);
        float scale = App.m_worldW / m_sprite.getWidth();
        m_sprite.setSize(App.m_worldW, m_sprite.getHeight()*scale);
        m_sprite.setPosition(m_position.x,m_position.y);

    }

    @Override
    protected Vector2 getCollisionPos(Ball b)
    {
        return new Vector2(0,m_position.y+m_height);
    }

    @Override
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
        //sb.draw(m_texture,m_position.x,m_position.y,m_width,m_height);
    }

    @Override
    protected boolean isColliding(Ball b)
    {
        Rectangle rect = new Rectangle(m_position.x,m_position.y,m_width,m_height);

        return rect.overlaps(b.getRect());
    }

    @Override
    public void checkCollision(Ball b)
    {
        super.checkCollision(b);
        // Do something else
    }

    @Override
    public void update(float dt)
    {
    }
}
