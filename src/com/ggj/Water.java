package com.ggj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;

public class Water {

    float DISAPEAR = .5f;
    int SPEED = 1000;
    float div = 1;
    World world;
    Body body;
    Vector3 startPosition;

    public Water(final World world, Vector3 pos, Vector2 go){
        this.world = world;
        this.startPosition = pos;
        createCollision();
        body.applyLinearImpulse(go, body.getWorldCenter(), true);
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                world.destroyBody(body);
            }
        }, 1f);


    }

    private void createCollision() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startPosition.x, startPosition.y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5, 5);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

}
