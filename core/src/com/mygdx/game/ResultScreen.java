package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ResultScreen implements Screen,Renderable {
    private MyGame game;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Table table2;
    private Table table3;
    private int coins_earned  = 400;
    private Label coins;
    private TextButton replay;
    private TextButton exit_main;
    private Image player_tank;
    private Label result;
    private Label player;
    private PassivePlayer[] players;
    private PassivePlayer winner;
    ResultScreen(MyGame game)
    {
        this.game = game;
        players = new PassivePlayer[2];
    }
    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table2 = new Table();
        table3 = new Table();

        coins = new Label("Coin: "+ coins_earned,skin);
        replay = new TextButton("Replay",skin);
        exit_main = new TextButton("Return to Menu",skin);
        player_tank = new Image(new Texture(Gdx.files.internal("kindpng_1188707.png")));
        result = new Label("VICTORY",skin);
        player = new Label(winner.getName(),skin);


        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        coins.setAlignment(1);
        result.setFontScale(2.6f);
        player.setFontScale(1.5f);
        table.setFillParent(true);
//        table2.setFillParent(true);
//        table3.setFillParent(true);
//        table3.setDebug(true);
//        table2.setDebug(true);
//        table.setDebug(true);
        table.add(result).colspan(2).expandX();
        table.row();
        table.add(table2).expand().space(0).fill();
        table.add(table3).expand().space(0).fill();

        table2.add(player_tank).expandX().pad(10);
        table2.row();
        table2.add(player).expandX();
        table3.add(coins).fill().pad(20);
        table3.row();
        table3.add(replay).minWidth(250).minHeight(60).pad(20).bottom();
        table3.row();
        table3.add(exit_main).width(250).height(60).pad(20).top();

//        coins.setColor(1,0,0,0.3f);
//        table.add(result).expandX();
//        table.add(coins).expandX();
//        table.row();
//        table.add(player_tank).expand();
//        table.add(replay).expandX().minWidth(200).prefWidth(300);
//        table.row();
//        table.add(player).expandX();
//        table.add(exit_main).expandX().minWidth(200).prefWidth(300);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

        if(replay.isChecked())
        {
            //add some other stuff after mygame is finished
            GameScreen g = new GameScreen(game,players);
            game.setGscreen(g);

            game.setScreen(game.getGscreen());
            g.generateActivePlayer(players);
            replay.setChecked(false);
        }

        if(exit_main.isChecked())
        {
            game.setScreen(game.getMscreen());
            replay.setChecked(false);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(){

    }

    public void replay(){

    }

    public void exit(){

    }
    public void setPlayerPassive(ActivePlayer[] activePlayer){
        for (int i = 0; i < players.length; i++) {
            players[i] = activePlayer[i].getPassivePlayer();
        }
    }

    public void setResult(int loser_index){
        winner = players[(loser_index)%2];
    }
    public void setCoinsEarned(){

    }
    public void addCoinsToAccount(){

    }
    public void generateAssets(){

    }
    public void linkUIElements(){

    }
    public void removeSaveState(){

    }
}
