package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


// TODO: Complete GameScreen First
public class MyGame extends Game {
	public SpriteBatch batch;
//	Texture img;
	private  MainScreen mscreen;
	private GameScreen gscreen;
	private  ResultScreen rscreen;
	public World physics_world;
	public static MyGame handle;

	Vector2 def_screen_size  = new Vector2(16*50,9*50);
	@Override
	public void create () {

		batch = new SpriteBatch();
		mscreen = new MainScreen(this);
		gscreen = new GameScreen(this);
		rscreen = new ResultScreen(this);
		this.setScreen(new MainScreen(this));
		MyGame.handle = this;
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}

	public MainScreen getMscreen() {
		return mscreen;
	}

	public void setMscreen(MainScreen mscreen) {
		this.mscreen = mscreen;
	}

	public GameScreen getGscreen() {
		return gscreen;
	}

	public void setGscreen(GameScreen gscreen) {
		this.gscreen = gscreen;
	}

	public ResultScreen getRscreen() {
		return rscreen;
	}

	public void setRscreen(ResultScreen rscreen) {
		this.rscreen = rscreen;
	}
}
