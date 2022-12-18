package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
	public OrthographicCamera camera;
//	World world = new World(new Vector2(0, 0), true);
	Box2DDebugRenderer debugRenderer;
	public TankActive tank;


	Ground ground; //todo debug testing to be omitted after test
	Vector2 def_screen_size  = new Vector2(16*50,9*50);
	@Override
	public void create () {
		physics_world = new World(new Vector2(0, -10), true); // todo debug testing to be omited after done
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		mscreen = new MainScreen(this);
		gscreen = new GameScreen(this);
		rscreen = new ResultScreen(this);

//		this.setScreen(new MainScreen(this));
		debugRenderer = new Box2DDebugRenderer();
		MyGame.handle = this;
		ground = new Ground();
		tank = new AbramsActive();

	}

	@Override
	public void render () {

		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		physics_world.step(1 / 60f, 6, 2);
		debugRenderer.render(physics_world, camera.combined.scl(32));
//		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		ground.render();
		tank.render();
//		batch.end();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
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
