package com.ggj;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.ggj.Screens.WinLose;

public class Enemy extends Sprite {
    World world;
    Body body;

    int x, y;
    int enemySize = 10;
    int enemySpeed = 50;

    int killRadiusX = 20;
    int killRadiusY = 50;

    boolean hasMoved = false;
    double timeSinceMoved = 0;
    float lastX, lastY;

    boolean change = false;

    Game game;

    public Enemy(World world, int x, int y, Game game){
        super(new Texture("Pagonis2.png"));
        setScale(.8f, .8f);
        this.world = world;
        this.x = x;
        this.y = y;
        createCollision(x, y);

        this.game = game;
    }

    public void update(SpriteBatch batch, float playerX, float playerY){
        moved();
        draw(batch);
        setPosition(body.getPosition().x - 20, body.getPosition().y - 33);
        follow(playerX, playerY);
        if(Math.abs(playerX - body.getPosition().x) <= killRadiusX && Math.abs(playerY - body.getPosition().y) <= killRadiusY){
            game.setScreen(new WinLose(game, false));
        }
    }

    void moved(){
        if(lastX != body.getPosition().x || lastY != body.getPosition().y){
            lastX = body.getPosition().x;
            lastY = body.getPosition().y;
            hasMoved = true;
            timeSinceMoved = 0;
        }
        else timeSinceMoved += Gdx.graphics.getDeltaTime();

        if(timeSinceMoved > 0.5){
            timeSinceMoved = 0;
            change = !change;
            hasMoved = false;
            // changedir
        }
    }

    double distance2d(float aX, float aY, float bX, float bY){
       return Math.sqrt((aX-bX)*(aX-bX) + (aY-bY)*(aY-bY));
    }

    void follow(float playerX, float playerY){
        goToTarget(playerX, playerY);
    }

    void patrol(){

    }

    void goToTarget(float targetX, float targetY){

        if(!change){
            if(body.getPosition().y - targetY < -1){
                body.setLinearVelocity(0, enemySpeed);
            }
            else if(body.getPosition().y - targetY > 1){
                body.setLinearVelocity(0, -enemySpeed);
            }
            else if(body.getPosition().x - targetX < -1){
                body.setLinearVelocity(enemySpeed, 0);
            }
            else if(body.getPosition().x - targetX > 1){
                body.setLinearVelocity(-enemySpeed, 0);
            }
        }
        else{
            if(body.getPosition().x - targetX < -1){
                body.setLinearVelocity(enemySpeed, 0);
            }
            else if(body.getPosition().x - targetX > 1){
                body.setLinearVelocity(-enemySpeed, 0);
            }
            else if(body.getPosition().y - targetY < -1){
                body.setLinearVelocity(0, enemySpeed);
            }
            else if(body.getPosition().y - targetY > 1){
                body.setLinearVelocity(0, -enemySpeed);
            }
        }
    }

    private void createCollision(int x, int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 23);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
