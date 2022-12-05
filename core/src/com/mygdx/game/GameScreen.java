package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private MyGame game;
    private Stage stage;
    private Skin skin;
    private Table table;
    private int h1 = 40;
    private int h2 = 70;
    private ProgressBar health1;
    private ProgressBar health2;
    private TextButton pause;
    private TextButton save;
    private TextButton resume;
    private TextButton left1;
    private TextButton right1;
    private TextButton left2;
    private TextButton right2;
    private TextButton bullet;
    private TextButton exit;
    private Label Power;
    private Label Angle;
    private int pow = 50;
    private int ang = 60;
    private TextButton fire;
    private Table downlabel;
    private Table uplabel;

    private Window pause_menu;

    GameScreen(MyGame game)
    {
        this.game = game;


    }
    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage(new ScreenViewport());

        table = new Table();
        stage.addActor(table);
        health1 = new ProgressBar(0,100,1,false,skin);
        health2 = new ProgressBar(0,100,1,false,skin);
        pause = new TextButton("pause",skin);
        save = new TextButton("Save Game",skin);
        resume = new TextButton("Resume",skin);
        left1  = new TextButton("<",skin);
        left2  = new TextButton("<",skin);
        right1  = new TextButton(">",skin);
        right2  = new TextButton(">",skin);
        bullet = new TextButton("Big One",skin);
        exit = new TextButton("Exit",skin);

        Power = new Label("" + pow,skin);
        Angle = new Label("" + ang,skin);
        fire = new TextButton("FIRE",skin);
        downlabel = new Table();
        uplabel = new Table();
        pause_menu = new Window("pause",skin);
        pause_menu.setHeight((float) (Gdx.graphics.getHeight()/(5.0/4.0)));
        pause_menu.setWidth((float) (Gdx.graphics.getWidth()/(5.0/4.0)));
        pause_menu.setPosition(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
//        pause_menu.setDebug(true);
        pause_menu.setMovable(false);
        pause_menu.add(save).width(200).height(60).pad(20);
        pause_menu.row();
        pause_menu.add(resume).width(200).height(60).pad(20);
        pause_menu.row();
        pause_menu.add(exit).width(200).height(60).pad(20);

        health2.setRotation(health2.getRotation() + 180);
        uplabel.add(health1).align(Align.left).expandX().padRight(200).width(170).height(30);
        uplabel.add(pause).align(Align.center).expandX().height(40);
        uplabel.add(health2).align(Align.right).expandX().padLeft(200).width(170).height(30);


        downlabel.setColor(1,1,0,1);
        downlabel.add(bullet).align(Align.left).space(0,0,0,0).height(30).width((200));
        downlabel.add(fire).align(Align.center).space(0,150,0,130).height(50).width((100));
        downlabel.add(left1).align(Align.right).space(0,40,0,0).height(30).width((30));
        downlabel.add(Power).align(Align.right).space(0,5,0,5).height(30).width((30));
        downlabel.add(right1).align(Align.right).space(0,0,0,0).height(30).width((30));
        downlabel.add(left2).align(Align.right).space(0,20,0,0).height(30).width((30));
        downlabel.add(Angle).align(Align.right).space(0,5,0,5).height(30).width((30));
        downlabel.add(right2).align(Align.right).space(0,0,0,0).height(30).width((30));
        table.setFillParent(true);
//        downlabel.setDebug(true);
//        uplabel.setDebug(true);
//        table.setDebug(true);


        table.add(uplabel).expand().align(Align.top);
        table.row();
        table.add((downlabel)).expand().align(Align.bottom);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

        if(pause.isChecked())
        {
            stage.addActor(pause_menu);
            pause.setChecked(false);
        }

        if(resume.isChecked())
        {
            pause_menu.remove();
            resume.setChecked(false);
        }

        if(exit.isChecked())
        {
            pause_menu.remove();
            game.setScreen(game.mscreen);
            exit.setChecked(false);
        }

        if(fire.isChecked())
        {
            game.setScreen(game.rscreen);
            fire.setChecked(false);
        }
        if(left1.isChecked())
        {
            pow--;
            Power.setText(pow);
            left1.setChecked(false);

        }
        if(left2.isChecked())
        {
            ang--;
            Angle.setText(ang);
            left2.setChecked(false);

        }
        if(right1.isChecked())
        {
            pow++;
            Power.setText(pow);
            right1.setChecked(false);

        }
        if(right2.isChecked())
        {
            ang++;
            Angle.setText(ang);
            right2.setChecked(false);

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

    }

    @Override
    public void dispose() {

    }
}
