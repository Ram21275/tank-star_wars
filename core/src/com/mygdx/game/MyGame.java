package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


// TODO: Complete GameScreen First
public class MyGame extends Game implements Serializable {
	public SpriteBatch batch;
//	Texture img;
	private  MainScreen mscreen;
	private GameScreen gscreen;
	private  ResultScreen rscreen;
	public static MyGame handle;
	private PassivePlayer player;
	private int coins;
	private static HashMap<Integer,TankPassive> tanklist;
	private HashMap<Integer,Save> save_files;
	public static long serialVersionUID = 1023L;


	@Override

	public void create () {
		MyGame game = null;
		boolean ser = false;
		try {
			Json json = new Json(JsonWriter.OutputType.json);
			JsonReader json2 = new JsonReader();
			JsonValue base = json2.parse(Gdx.files.internal("s_file.json"));
			game = json.fromJson(MyGame.class,base.toJson(JsonWriter.OutputType.json));
		} catch (Exception e) {
			System.out.println("new Install");
		}
		if(game == null) {
			player = new PassivePlayer(0,"Player 1",1);
			MyGame.handle = this;
			batch = new SpriteBatch();
			mscreen = new MainScreen(this);
			this.setScreen(mscreen);
			save_files = new HashMap<>();
			for (int i = 0; i < 5; i++) {
				save_files.put(i,new Save());
			}
		} else {
			this.player = game.getPlayer();
			this.rscreen = game.getRscreen();
			this.gscreen = game.getGscreen();
			this.batch = new SpriteBatch();
			this.save_files = game.save_files;
		}

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
		PrintWriter out = null;
		try {
			Json json = new Json(JsonWriter.OutputType.json);
			out = new PrintWriter(new FileWriter("s_file.json"));
			out.write( json.toJson(this));
			;
		} catch (Exception e) {
			System.out.println("this ain't good");
		}
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

		return player;
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
