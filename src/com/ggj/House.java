package com.ggj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class House extends Sprite{
    public int x=0, y=0;

    public House(int x, int y){
        super(new Texture("house.png"));
        this.x = x;
        this.y = y;
        setPosition(x - 30, y - 25);
        getColor().a = 0;
    }

    public void render(SpriteBatch batch){
        draw(batch);
    }
}
