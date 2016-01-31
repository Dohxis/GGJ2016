package com.ggj.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.ggj.Game;

public class MenuScreen implements Screen {

    Texture playBtn;
    OrthographicCamera camera;
    Texture Quit;
    Texture logo;

    public Game game;

    public MenuScreen(Game game) {
        this.game = game;
        playBtn = new Texture("play.png");
        Quit = new Texture("end.png");
        logo = new Texture("title.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(tmp);
        camera.update();

        Rectangle bounds = new Rectangle((Gdx.graphics.getWidth() - 300)/2, (Gdx.graphics.getHeight() - 175) / 2, 300, 125);
        Rectangle bounds1 = new Rectangle((Gdx.graphics.getWidth() - 300)/2, (Gdx.graphics.getHeight() - 450) / 2, 300, 125);

        if(bounds.contains(tmp.x, tmp.y) && Gdx.input.isTouched()){
            game.setScreen(new PlayScreen(game, 0));
        }

        if(bounds1.contains(tmp.x, tmp.y) && Gdx.input.isTouched()){
            Gdx.app.exit();
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(playBtn, (Gdx.graphics.getWidth() - 300)/2, (Gdx.graphics.getHeight() - 175) / 2, 300, 125 );
        game.batch.draw(Quit, (Gdx.graphics.getWidth() - 300)/2, (Gdx.graphics.getHeight()- 450) / 2, 300, 125 );
        game.batch.draw(logo, (Gdx.graphics.getWidth() - 300)/2, (Gdx.graphics.getHeight() + 150) / 2, 300, 125 );
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
