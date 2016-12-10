package com.mygdx.game.Obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.App;
import com.mygdx.game.AppStates.LevelFinishedState;
import com.mygdx.game.AppStates.StateManager;
import com.mygdx.game.AppStates.UserTestMenu;
import com.mygdx.game.Ball.Ball;
import com.mygdx.game.Basics.Circle;

/**
 * Created by Rickard on 2016-12-08.
 */

public class LevelGoal extends Obstacle
{
    Sprite m_sprite;
    StateManager m_sm = null;

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
    public void render(SpriteBatch sb)
    {
        m_sprite.draw(sb);
        //sb.draw(m_texture,m_position.x,m_position.y,m_width,m_height);
    }

    @Override
    protected Vector2 getCollisionPosition(Circle c)
    {
        return null;
    }


    @Override
    public void checkCollision(Ball b)
    {
        Array<Circle> arr = b.getCircles();

        int index = getPossibleCollisionIndex(arr);

        if(index != -1)
        {
            Vector2 collisionPosition = getCollisionPosition(arr.get(index));

            Gdx.app.log("JS","- Hit - id: " + arr.get(index).m_id + " side: " + arr.get(index).m_side + " @ index: " + index);

            m_sm.set(new UserTestMenu(m_sm));
        }
    }

    @Override
    public void update(float dt)
    {
    }

    @Override
    public void dispose()
    {
        m_texture.dispose();
    }

    public void setStateManager(StateManager sm)
    {
        m_sm = sm;
    }

}
