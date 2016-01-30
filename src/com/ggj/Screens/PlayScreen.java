package com.ggj.Screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggj.*;

public class PlayScreen implements Screen {

    Game game;
    OrthographicCamera camera;
    Viewport viewport;
    RayHandler rayHandler;

    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    Map map;

    Player player;
    Random enemy;
    Texture bg;

    Pagan pagan;

    public PlayScreen(Game game) {
        // 640 480
        this.game = game;
        bg = new Texture("map.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();


        enemy = new Random("", world, true, 200, 100, 30);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0f, 0f, 0f, 0.1f);
        player = new Player(world, camera, rayHandler);

        map = new Map(world, rayHandler);
        map.generate();

        pagan = new Pagan(world, 100, 50, true);
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

        game.batch.draw(bg,0, 0);
        map.renderAll(game.batch);
        pagan.update(game.batch, Gdx.graphics.getDeltaTime(), player.body);
        game.batch.end();

        box2DDebugRenderer.render(world, camera.combined);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
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
    public void resume() {
    }

    @Override
    public void dispose() {


    }

    @Override
    public void hide() {
    }
}

