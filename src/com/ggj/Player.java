package com.ggj;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Player extends Sprite {

    public World world;
    public Body body;
    OrthographicCamera camera;
    Vector3 mousePos;
    int SPEED = 100;
    int canShoot = 3;
    int shooted = 0;
    float speed_water = 260f;
    float div = speed_water / 35;
    public Water water;
    boolean canAttack;
    float timerAttack = 0f;
    Vector2 go = new Vector2(speed_water, 0);
    RayHandler rayHandler;
    PointLight light;

    public Player(World world, OrthographicCamera camera, RayHandler rayHandler){
        this.rayHandler = rayHandler;
        this.world = world;
        this.camera = camera;
        mousePos = new Vector3(0, 0, 0);
        water = new Water(world, new Vector3(-2000, -2000, 0));
        light = new PointLight(rayHandler, 200, Color.YELLOW, 35, -1000, -10000);
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
        light.setPosition(body.getPosition().x, body.getPosition().y);
        controls();
        shoot();
        timerAttack += Gdx.graphics.getDeltaTime();
        if(timerAttack > 1f && !Gdx.input.isTouched()){
            canAttack = true;
            timerAttack = 0f;
        }
    }

    public void controls(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            body.applyLinearImpulse(new Vector2(0, SPEED), body.getWorldCenter(), true);
            go.set(0, speed_water);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            body.applyLinearImpulse(new Vector2(0, -SPEED), body.getWorldCenter(), true);
            go.set(0, -speed_water);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            body.applyLinearImpulse(new Vector2(-SPEED, 0), body.getWorldCenter(), true);
            go.set(-speed_water, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            body.applyLinearImpulse(new Vector2(SPEED, 0), body.getWorldCenter(), true);
            go.set(speed_water, 0);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)){
            body.setLinearVelocity(new Vector2(0, 0));
        }
    }

    public void shoot(){
        if(Gdx.input.isTouched() && canAttack && shooted < canShoot){
            mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);
            canAttack = false;



            water.gogo(new Vector3(body.getPosition().x +
                    go.x/div, body.getPosition().y + go.y/div, 0), go);
            shooted++;
        }
    }
}
