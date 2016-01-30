package com.ggj;

import com.badlogic.gdx.physics.box2d.*;

public class Enemy {
    World world;
    Body body;

    int x, y;
    int enemySizze = 20;
    int enemySpeed = 10;

    int killRadius;

    public Enemy(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        createCollision(x, y);
    }

    public void update(float playerX, float playerY){

    }

    double distance2d(){
       return 1.0;
    }

    void follow(){

    }

    void patrol(){

    }

    void goToTarget(int targetX, int tergetY){
        if(x - targetX < 0){
            body.setLinearVelocity(10, 0);
        }
        if(x - targetX > 0){
            body.setLinearVelocity(-10, 0);
        }
        if(y - targetX < 0){
            body.setLinearVelocity(0, 10);
        }
        if(y - targetX > 0){
            body.setLinearVelocity(0, -10);
        }
    }

    private void createCollision(int x, int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemySizze, enemySizze);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
