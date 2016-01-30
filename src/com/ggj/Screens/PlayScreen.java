package com.ggj.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggj.Game;
import com.ggj.Map;
import com.ggj.Player;
import com.ggj.Random;

public class PlayScreen implements Screen {

    Game game;
    OrthographicCamera camera;
    Viewport viewport;

    World world;
    Box2DDebugRenderer box2DDebugRenderer;

    Player player;
    Random enemy;

    public PlayScreen(Game game){
        // 640 480
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        player = new Player(world, camera);
        enemy = new Random("", world, true, 200, 100, 30);

        Map map = new Map(world);
        map.generate();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        world.step(1 / 60f, 6, 2);
        camera.update();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        player.render(game.batch);
        enemy.renderRandom(game.batch);
        game.batch.end();

        box2DDebugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    }

    @Override
    public void dispose() {


    }
}
    public void resume() {

    }

    @Override
    public void hide() {

