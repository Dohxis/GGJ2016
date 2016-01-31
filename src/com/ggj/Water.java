package com.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;

public class Water extends Sprite {

    float DISAPEAR = .5f;
    int SPEED = 1000;
    float div = 1;
    World world;
    public Body body;
    Vector3 startPosition;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("Water.wav"));

    public Water(final World world, Vector3 pos){
        super(new Texture("shot.png"));
        this.world = world;
        this.startPosition = pos;
        createCollision();
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

    public void gogo(Vector3 pos, Vector2 go){
        sound.play(1.0f);
        body.setTransform(pos.x, pos.y, 0);
        body.applyLinearImpulse(go, body.getWorldCenter(), true);
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                body.setTransform(-2000f, -2000f, 0);
                body.setLinearVelocity(new Vector2(0, 0));
            }
        }, 1f);
    }

    public void render(SpriteBatch batch){
        setPosition((body.getPosition().x - 8), (body.getPosition().y - 8));
        draw(batch);
    }

}
