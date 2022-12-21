package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;


// TODO: Complete GameScreen First
public class MyGame extends Game {
	public SpriteBatch batch;
//	Texture img;
	private  MainScreen mscreen;
	private GameScreen gscreen;
	private  ResultScreen rscreen;
	public static MyGame handle;
	private PassivePlayer player;
	private int coins;
	private static HashMap<Integer,TankPassive> tanklist;
	private static HashMap<Integer,Save> save_files;
	public long serialVersionUID;

//	World world = new World(new Vector2(0, 0), true);

	Ground ground; //todo debug testing to be omitted after test
	Vector2 def_screen_size  = new Vector2(16*50,9*50);

	@Override
	public void create () {
		MyGame.handle = this;
		batch = new SpriteBatch();
		mscreen = new MainScreen(this);
//		gscreen = new GameScreen(this);
		rscreen = new ResultScreen(this);

		this.setScreen(mscreen);



	}

	@Override
	public void render () {


//		ScreenUtils.clear(0, 0, 0.2f, 1);


//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		ground.render();
//		tank.render();
//		tank2.render();

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
	public void exit(){

	}
	public PassivePlayer getPlayer(){

		return null;
	}
	public MainScreen getMenuScreen(){

		return null;
	}
	public GameScreen getGameScreen(){

		return null;
	}


	public int getCoins(){
		return 0;
	}
	public void setCoins(){

	}
	public void setSave(int id,Save savefile){

	}
	public void loadPlayerData(){

	}
	public int generateSaveData(){

		return 0;
	}
	public void loadGameData(){

	}
	public void setResultScreen(ResultScreen rs){

	}
	public void setGameScreen(GameScreen gs){

	}
	public void setMenuScreen(MainScreen ms){

	}
	public void getPlayer(PassivePlayer pp){

	}
	public void setPlayer(PassivePlayer pl){

	}

	public static HashMap<Integer, TankPassive> getTankList() {
		return null;
	}

	public static HashMap<Integer, Integer> getSaveFiles() {
		return null;
	}
}
