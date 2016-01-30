package com.ggj;

import com.badlogic.gdx.physics.box2d.*;

public class Enemy {
    World world;
    Body body;

    int x, y;
    int enemySize = 10;
    int enemySpeed = 50;

    int killRadius;

    public Enemy(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        createCollision(x, y);
    }

    public void update(float playerX, float playerY){
        goToTarget(playerX, playerY);
    }

    double distance2d(){
       return 1.0;
    }

    void follow(){

    }

    void patrol(){

    }

    void goToTarget(float targetX, float targetY){

        if(body.getPosition().x - targetX < -10){
            body.setLinearVelocity(enemySpeed, 0);
        }
        else if(body.getPosition().x - targetX > 10){
            body.setLinearVelocity(-enemySpeed, 0);
        }
        else if(body.getPosition().y - targetY < -10){
            body.setLinearVelocity(0, enemySpeed);
        }
        else if(body.getPosition().y - targetY > 10){
            body.setLinearVelocity(0, -enemySpeed);
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
