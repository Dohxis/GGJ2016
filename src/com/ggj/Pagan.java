package com.ggj;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Pagan {
    boolean isIdle = true;
    boolean isChristian = false;
    int direction = 2;
    double time = 0;
    World world;
    Body body;

    int adultSpeed = 25;
    int adultSize = 20;

    int childSpeed;
    int childSize;

    int paganSpeed;
    int paganSize;


    public Pagan(World world, int x, int y, boolean isAdult){
        this.world = world;
        if(isAdult){
            paganSpeed = adultSpeed;
            paganSize = adultSize;
        }
        else{
            paganSpeed = childSpeed;
            paganSize = childSize;
        }
        createCollision(x, y);
    }

    private void createCollision(int x, int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(paganSize, paganSize);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void update(SpriteBatch batch, double deltaTime, Body waterBody){
        time += deltaTime;

        Rectangle paganRekt = new Rectangle(body.getPosition().x-25, body.getPosition().y-25, paganSize+50, paganSize+50);
        Rectangle rekt = new Rectangle(waterBody.getPosition().x, waterBody.getPosition().y, 10, 10);

        if(/*!isChristian && */paganRekt.overlaps(rekt)){
            isChristian = true;
            System.out.println("blah");
            //create light
        }

        if(isChristian){
            //move light
        }

        if(time > 1){
            isIdle = !isIdle;
            direction = 0 + (int)(Math.random() * ((3 - 0) + 1));
            time = 0;
        }

        if(isIdle){
            body.setLinearVelocity(0, 0);
        }
        else{
            switch (direction){
                case 0:
                    body.setLinearVelocity(paganSpeed, 0);
                    break;
                case 1:
                    body.setLinearVelocity(0, paganSpeed);
                    break;
                case 2:
                    body.setLinearVelocity(-paganSpeed, 0);
                    break;
                case 3:
                    body.setLinearVelocity(0, -paganSpeed);
                    break;
            }
        }

    }
}
