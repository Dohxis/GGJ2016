package com.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy {
    World world;
    Body body;

    int x, y;
    int enemySize = 10;
    int enemySpeed = 50;

    int killRadius;

    boolean hasMoved = false;
    double timeSinceMoved = 0;
    float lastX, lastY;

    boolean change = false;

    public Enemy(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        createCollision(x, y);
    }

    public void update(float playerX, float playerY){
        moved();
        follow(playerX, playerY);
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

    double distance2d(){
       return 1.0;
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
        shape.setAsBox(enemySize, enemySize);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
