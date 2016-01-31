package com.ggj;


import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Pagan extends Sprite {

    boolean isIdle = true;
    boolean isChristian = false;
    int direction = 2;
    double time = 0;
    World world;
    Body body;
    RayHandler rayHandler;
    PointLight light;

    int adultSpeed = 30;
    Vector2 adultSize = new Vector2(9, 21);

    int childSpeed = 60;
    Vector2 childSize = new Vector2(9, 21);

    int paganSpeed;
    Vector2 paganSize;


    public Pagan(World world, int x, int y, RayHandler rayHandler, boolean isAdult){
        super(new Texture("Pagonis2.png"));
        setScale(.7f, .7f);
        this.world = world;
        this.rayHandler = rayHandler;
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
        shape.setAsBox(paganSize.x, paganSize.y);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void update(SpriteBatch batch, double deltaTime, Body waterBody){
        draw(batch);
        setPosition(body.getPosition().x - 20, body.getPosition().y - 33);

        time += deltaTime;

        Rectangle paganRekt = new Rectangle(body.getPosition().x-25, body.getPosition().y-25, paganSize.x+50, paganSize.y+50);
        Rectangle rekt = new Rectangle(waterBody.getPosition().x, waterBody.getPosition().y, 5, 5);

        if(!isChristian && paganRekt.overlaps(rekt)){
            isChristian = true;
            light = new PointLight(rayHandler, 100, new Color(1, 1, 0, .5f), 150, body.getPosition().x, body.getPosition().y);
            System.out.println("wow");
        }

        if(isChristian){
            light.setPosition(body.getPosition().x, body.getPosition().y);
        }

        if(time > 2){
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
