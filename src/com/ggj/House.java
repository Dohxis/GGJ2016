package com.ggj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class House extends Sprite{
    public int x=0, y=0;
    public boolean isFlipped=false;

    public House(int x, int y, boolean isFlipped){
        super(new Texture("house.png"));
        this.x = x;
        this.y = y;
        this.isFlipped = isFlipped;
        setPosition(x - 30, y - 25);
        getColor().a = 0;
        setRotation(360);
    }

    public void render(SpriteBatch batch){
        draw(batch);
    }
}
