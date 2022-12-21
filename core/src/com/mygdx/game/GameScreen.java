package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import sun.java2d.opengl.GLXSurfaceData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GameScreen implements Screen {
    private MyGame game;
    public World physics_world;
    Box2DDebugRenderer debugRenderer;
    public OrthographicCamera camera;

    public void generateActivePlayer(PassivePlayer[] passivePlayer) {
        for (int i = 0; i < 2; i++) {
            player[i] = passivePlayer[i].generateActivePlayer();
        }
    }

    private ActivePlayer[] player;
    private int playerturn;
    private ProgressBar health_p1;
    private ProgressBar health_p2;
    private TextButton power_left;
    private TextButton power_right;
    private TextButton angle_left;
    private TextButton angle_right;
    private TextButton pause;
    private TextButton save;
    private TextButton resume;
    private TextButton exit;
    private int power = 50;  // should be toggled after each player turn
    private int angle = 60;  // should be toggled after each player turn
    private Label power_box;
    private Label angle_box;
    private Ground ground;
    private HashMap<Integer,AirDrop> airdroplist;

    private Stage stage;
    private Skin skin;
    private Table table;
    private int h1 = 40;
    private int h2 = 70;
    private TextButton bullet;
    private TextButton fire;
    private Table downlabel;
    private Table uplabel;
    private Window pause_menu;
    private TextButton exit_prompt;
    private TextButton exit_main;
    private Label Power_box;
    private Music music;
    public long serialVersionUID;


    public  LinkedList<Renderable> render_these;
    private  LinkedList<Renderable> render_it;
    public TankActive tank;
    private BuratinoActive tank2;

    private Integer hash = 0;
    private int turns_ellapsed = 0;
    GameScreen(MyGame game)
    {
        //initialize variables here: non-reutilized;
        render_these = new LinkedList<>();
        physics_world = new World(new Vector2(0, -10), true); // todo debug testing to be omited after done
        debugRenderer = new Box2DDebugRenderer();
        physics_world.setContactListener(new ContactHandler());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        airdroplist = new HashMap<>();
        this.game = game;

    }

    GameScreen(MyGame game,PassivePlayer[] passivePlayer)
    {
        //initialize variables here: non-reutilized;
        render_these = new LinkedList<>();
        physics_world = new World(new Vector2(0, -10), true); // todo debug testing to be omited after done
        debugRenderer = new Box2DDebugRenderer();
        physics_world.setContactListener(new ContactHandler());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        airdroplist = new HashMap<>();
        this.player = new ActivePlayer[2];

        playerturn = 0;
        this.game = game;


    }
    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        ground = new Ground();
//        tank = new AbramsActive(); // todo remove after debug
//        tank2 = new BuratinoActive(); // todo remove after debug
        stage = new Stage(new ScreenViewport());

        table = new Table();
        stage.addActor(table);
        health_p1 = new ProgressBar(0,100,1,false,skin);
        health_p2 = new ProgressBar(0,100,1,false,skin);
        pause = new TextButton("pause",skin);
        save = new TextButton("Save Game",skin);
        resume = new TextButton("Resume",skin);
        power_left = new TextButton("<",skin);
        angle_left = new TextButton("<",skin);
        power_right  = new TextButton(">",skin);
        angle_right = new TextButton(">",skin);
        bullet = new TextButton("Big One",skin);
        exit = new TextButton("Exit",skin);

        power_box = new Label("" + power,skin);
        angle_box = new Label("" + angle,skin);
        fire = new TextButton("FIRE",skin);
        downlabel = new Table();
        uplabel = new Table();
        pause_menu = new Window("pause",skin);
        pause_menu.setHeight((float) (Gdx.graphics.getHeight()/(5.0/4.0)));
        pause_menu.setWidth((float) (Gdx.graphics.getWidth()/(5.0/4.0)));
        pause_menu.setPosition(Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/10f);
//        pause_menu.setDebug(true);
        pause_menu.setMovable(false);
        pause_menu.add(save).width(200).height(60).pad(20);
        pause_menu.row();
        pause_menu.add(resume).width(200).height(60).pad(20);
        pause_menu.row();
        pause_menu.add(exit).width(200).height(60).pad(20);

        health_p2.setRotation(health_p2.getRotation() + 180);
        uplabel.add(health_p1).align(Align.left).expandX().padRight(200).width(170).height(30);
        uplabel.add(pause).align(Align.center).expandX().height(40);
        uplabel.add(health_p2).align(Align.right).expandX().padLeft(200).width(170).height(30);

        health_p1.setValue(h1/100f);health_p2.setValue(h2/100f);
        downlabel.setColor(1,1,0,1);
        downlabel.add(bullet).align(Align.left).space(0,0,0,0).height(30).width((200));
        downlabel.add(fire).align(Align.center).space(0,150,0,130).height(50).width((100));
        downlabel.add(power_left).align(Align.right).space(0,40,0,0).height(30).width((30));
        downlabel.add(power_box).align(Align.right).space(0,5,0,5).height(30).width((30));
        downlabel.add(power_right).align(Align.right).space(0,0,0,0).height(30).width((30));
        downlabel.add(angle_left).align(Align.right).space(0,20,0,0).height(30).width((30));
        downlabel.add(angle_box).align(Align.right).space(0,5,0,5).height(30).width((30));
        downlabel.add(angle_right).align(Align.right).space(0,0,0,0).height(30).width((30));
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
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        physics_world.step(1 / 60f, 6, 2);
//		camera.position.x = tank.getPosition().x*32f;camera.position.y = tank.getPosition().y*32f;
        camera.update();

        //Game should be rendered here.


        //UI drawn here
        /*
        * while fire animation buttons should become irresponsive
        * draging on screen and scrolling should help in navigation
        *
        *  */



        debugRenderer.render(physics_world, camera.combined.scl(32));
        render_it = (LinkedList<Renderable>) render_these.clone();
        Iterator<Renderable> i = render_it.iterator();
        while (i.hasNext()){
            i.next().render();
        }
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
            game.setScreen(game.getMscreen());
            exit.setChecked(false);
            this.dispose();
        }

        if(fire.isChecked())
        {
//            game.setScreen(game.getRscreen());
            player[playerturn].getTank().setPower(power);
            player[playerturn].getTank().setAngle(angle);
            player[playerturn].getTank().fireBullet(0);
            player[playerturn].getTank().setInput_enable(false);
            playerturn = (playerturn + 1)%2;
            player[playerturn].getTank().setInput_enable(true);
            fire.setChecked(false);
        }
        if(power_left.isChecked())
        {
            power--;
            power_box.setText(power);
            power_left.setChecked(false);

        }
        if(angle_left.isChecked())
        {
            angle--;
            angle_box.setText(angle);
            angle_left.setChecked(false);

        }
        if(power_right.isChecked())
        {
            power++;
            power_box.setText(power);
            power_right.setChecked(false);

        }
        if(angle_right.isChecked())
        {
            angle++;
            angle_box.setText(angle);
            angle_right.setChecked(false);

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

    public ActivePlayer getPlayer1() { return player[0]; }
    public ActivePlayer getPlayer2() { return player[1]; }
    public ActivePlayer getActivePlayer() { return player[playerturn]; }

    public int getPower() {
        return power;
    }

    public int getAngle() {
        return angle;
    }

    public Ground getGround() {
        return ground;
    }

    public HashMap<Integer, AirDrop> getAirdroplist() {
        return airdroplist;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public Integer addAirDrop()
    {
        //TODO: inplement addAirDrop
        return 0;
    }
    public void removeAirDrop(int ID) {
        this.airdroplist.remove(ID);
    }
    public void endGame(ActivePlayer loser) {
        // todo implement after result screen;
        ResultScreen r = new ResultScreen(game);
        r.setPlayerPassive(player);
        if(loser == player[0]) {
            r.setResult(1);
        } else {
            r.setResult(0);
        }
        game.setRscreen(r);
        game.setScreen(game.getRscreen());
    }



}
