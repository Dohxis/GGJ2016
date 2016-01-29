package com.ggj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Sprite {

    public World world;
    public Body body;
    int SPEED = 100;

    public Player(World world){
        this.world = world;
        createCollision();
    }

    private void createCollision(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100 / 2, 380 / 2);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 10);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void render(SpriteBatch batch){

    }
}
