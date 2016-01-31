package com.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ggj.Screens.MenuScreen;
import com.ggj.Screens.PlayScreen;
import com.ggj.Screens.WinLose;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));

		music = Gdx.audio.newMusic(Gdx.files.internal("musi.mp3"));

		music.setVolume(0.05f);
		music.setLooping(true);
		music.play();
	}

	@Override
	public void render () {
		super.render();
	}
}
