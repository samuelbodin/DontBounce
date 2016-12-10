package com.mygdx.game.Basics;

import com.badlogic.gdx.math.Vector2;

public class Circle
{
    public float m_x = 0f;
    public float m_y = 0f;
    public float m_radius = 0f;
    public int m_id = 0;
    public int m_side = -1;

    public Circle(Vector2 position, float radius, int id)
    {
        m_x = position.x;
        m_y = position.y;
        m_radius = radius;
        m_id = id;
    }
}
