package com.ggj;

import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Moneikis on 2016-01-29.
 */
public class Random extends Sprite{

    double time = 0;
     World world;
    Body body;
    boolean a;
    float speedx;
    float speedy;
    float x, y;
    float x1, y1;
    float ymain;
    float xmain;
    float radius;


    public Random(String img, World world, boolean a, float x, float y, float radius){
        //super(new Texture(img));
        this.a = a;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.world = world;
        createPlayerCollision();
    }

    private void createPlayerCollision(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20, 20);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        x1 = x + radius;
        y1 = y + radius;
    }
    public void controls() {

        if (a) {
            if (x >= (x1 + radius)) {
                body.applyLinearImpulse(new Vector2(radius * (-1), speedy), body.getWorldCenter(), true);
                x = body.getPosition().x;
            } else {
                if (x <= (x1 - radius))
                    body.applyLinearImpulse(new Vector2(radius, speedy), body.getWorldCenter(), true);
                x = body.getPosition().x;


            }
        }
        else {
            if (y >= (y1 + radius)) {
                body.applyLinearImpulse(new Vector2(speedy, radius * (-1)), body.getWorldCenter(), true);
                y = body.getPosition().y;
            } else {
                if (y <= (y1 - radius))
                    body.applyLinearImpulse(new Vector2(speedy, radius), body.getWorldCenter(), true);
                y = body.getPosition().y;
            }
        }
    }

    public void renderRandom(SpriteBatch batch){
        controls();
        //draw(batch);
    }
}

/*
    private void createPlayerCollision(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(120, 120);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20, 20);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        x1 = body.getPosition().x;
        y1 = body.getPosition().y;
        y1 += radius;
    }
    public void controls() {

        if (x >= (x1 + radius)) {
            body.applyLinearImpulse(new Vector2(radius * (-1), speedy), body.getWorldCenter(), true);
            x = body.getPosition().x;
        }
        else { if (x <= (x1 - radius))
            body.applyLinearImpulse(new Vector2(radius, speedy), body.getWorldCenter(), true);
            x = body.getPosition().x;


            }
        }
 */