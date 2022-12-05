package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen {

    private MyGame game;
    private Skin skin;
    private Stage stage;
    private Table table;

    private TextButton new_game;
    private TextButton load_game;
    private TextButton exit;
    private Window window;
    private TextButton w_exit;
    private Window new_game_window;
    private TextButton new_game_exit;
    private TextButton new_game_start_game;

    private ScrollPane plarer_1_selection;
    private VerticalGroup plarer_1_selection_g;
    private ScrollPane plarer_2_selection;
    private VerticalGroup plarer_2_selection_g;
    private ImageButton tank11, tank12, tank13 , tank21, tank22 ,tank23;
    private Label tank_1, tank_2;

    private Window load_game_window;
    private TextButton load_game_exit;
    private ScrollPane load_selection;
    private VerticalGroup load_selection_g;
    private TextButton[] save;
    private TextureRegionDrawable t1;
    private TextureRegionDrawable t2;
    private TextureRegionDrawable t3;
    private TextureRegionDrawable t4;
    private TextureRegionDrawable t5;
    private TextureRegionDrawable t6;
    private Image logo = new Image(new Texture(Gdx.files.internal("tankstar-usa_owler_20160227_001629_original.png")));

    public MainScreen(MyGame game){

        this.game = game;
        Pixmap pixmapOld = new Pixmap(Gdx.files.internal("kindpng_1188707.png"));
        Pixmap pixmapNew = new Pixmap(pixmapOld.getWidth()/2, pixmapOld.getHeight()/2, pixmapOld.getFormat());
        pixmapNew.drawPixmap(pixmapOld,
                0, 0, pixmapOld.getWidth(), pixmapOld.getHeight(),
                0, 0, pixmapNew.getWidth(), pixmapNew.getHeight()
        );
        Texture texture = new Texture(pixmapNew);
        pixmapOld.dispose();
        pixmapNew.dispose();
        TextureRegion tt1 = new TextureRegion(texture);
        TextureRegion tt2 = new TextureRegion(texture);
        TextureRegion tt3 = new TextureRegion(texture);
        TextureRegion tt4 = new TextureRegion(texture);
        TextureRegion tt5 = new TextureRegion(texture);
        TextureRegion tt6 = new TextureRegion(texture);


        t1 = new TextureRegionDrawable(tt1);
        t2 = new TextureRegionDrawable(tt2);
        t3 = new TextureRegionDrawable(tt3);
        t4 = new TextureRegionDrawable(tt4);
        t5 = new TextureRegionDrawable(tt5);
        t6 = new TextureRegionDrawable(tt6);

    }
    @Override
    public void show() {

//        new_game.setDebug(true);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage(new ScreenViewport());

        table = new Table();
        new_game = new TextButton("NEW GAME",skin);
        load_game = new TextButton("LOAD GAME",skin);
        exit = new TextButton("EXIT",skin);
        stage.addActor(table);
        window = new Window("Select Tank",skin);
        window.setHeight((float) (Gdx.graphics.getHeight()/(5.0/4.0)));
        window.setWidth((float) (Gdx.graphics.getWidth()/(5.0/4.0)));
        window.setPosition(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
//        window.setDebug(true);
        window.setMovable(false);

        new_game_window = new Window("Select Tank",skin);
        new_game_window.setHeight((float) (Gdx.graphics.getHeight()/(5.0/4.0)));
        new_game_window.setWidth((float) (Gdx.graphics.getWidth()/(5.0/4.0)));
        new_game_window.setPosition(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
//        new_game_window.setDebug(true);
        new_game_window.setMovable(false);
        new_game_exit = new TextButton("Exit",skin);
        new_game_start_game = new TextButton("Start Game",skin);
        tank11 = new ImageButton(t1); tank12 = new ImageButton(t2);
        tank13 = new ImageButton(t3); tank21 = new ImageButton(t4);
        tank22 = new ImageButton(t5); tank23 = new ImageButton(t6);
        plarer_1_selection_g = new VerticalGroup(); plarer_1_selection = new ScrollPane(plarer_1_selection_g,skin);
        plarer_2_selection_g = new VerticalGroup(); plarer_2_selection = new ScrollPane(plarer_2_selection_g,skin);
        tank_1 = new Label("select tank",skin); tank_2 = new Label("select tank",skin);


        load_game_window = new Window("Select Tank",skin);
        load_game_window.setHeight((float) (Gdx.graphics.getHeight()/(5.0/4.0)));
        load_game_window.setWidth((float) (Gdx.graphics.getWidth()/(5.0/4.0)));
        load_game_window.setPosition(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
//        load_game_window.setDebug(true);
        load_game_window.setMovable(false);
        load_game_exit = new TextButton("Exit",skin);
        load_selection_g = new VerticalGroup();
        load_selection = new ScrollPane(load_selection_g,skin);
        save = new TextButton[5];
        for (int i = 0; i < 5; i++) {
            save[i] = new TextButton("save"+(i+1),skin);
        }


        Gdx.input.setInputProcessor(stage);
        table.add(logo).expandX().padBottom(70);
        table.row();
        table.add(new_game).pad(10).width(200).height(40).expandX();
        table.row();
        table.add(load_game).pad(10).width(200).height(40).expandX();
        table.row();
        table.add(exit).pad(10).width(200).height(40).expandX();
        table.setFillParent(true);

        plarer_1_selection_g.addActor(tank11);plarer_1_selection_g.addActor(tank12);plarer_1_selection_g.addActor(tank13); plarer_1_selection_g.fill();
        plarer_2_selection_g.addActor(tank21);plarer_2_selection_g.addActor(tank22);plarer_2_selection_g.addActor(tank23); plarer_2_selection_g.fill();
        new_game_window.add(plarer_1_selection).expand(); new_game_window.add(plarer_2_selection).expand();
        new_game_window.row();
        new_game_window.add(tank_1);new_game_window.add(tank_2);
        new_game_window.row();
        new_game_window.add(new_game_start_game).colspan(2).width(200).height(40);
        new_game_window.row();
        new_game_window.add(new_game_exit).colspan(2).width(200).height(40);
        load_selection_g.fill();
        load_selection_g.addActor(save[1]);load_selection_g.addActor(save[2]);load_selection_g.addActor(save[3]);load_selection_g.addActor(save[4]);load_selection_g.addActor(save[0]);
        load_selection_g.getPrefWidth();
        load_game_window.add(load_selection).expand();
        load_game_window.row();
        load_game_window.add(load_game_exit).width(200).height(40);


//        table.setDebug(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(new_game.isChecked())
        {

            stage.addActor(new_game_window);
            new_game.setChecked(false);
        }
        if(load_game.isChecked())
        {

            stage.addActor(load_game_window);
            load_game.setChecked(false);
        }

        if(tank11.isChecked())
        {
//            System.out.println("yolo");//
            tank_1.setText("Abrahms");
            tank11.setChecked(false);
        }
        if(tank12.isChecked())
        {
//            System.out.println("yolo");//
            tank_1.setText("Frost");
            tank11.setChecked(false);
        }
        if(tank13.isChecked())
        {
//            System.out.println("yolo");//
            tank_1.setText("Buggy");
            tank11.setChecked(false);
        }
        if(tank21.isChecked())
        {
//            System.out.println("yolo");//
            tank_2.setText("Abrahms");
            tank11.setChecked(false);
        }
        if(tank22.isChecked())
        {
//            System.out.println("yolo");//
            tank_2.setText("Frost");
            tank11.setChecked(false);
        }
        if(tank23.isChecked())
        {
//            System.out.println("yolo");//
            tank_2.setText("Buggy");
            tank11.setChecked(false);
        }
        for (int i = 0; i < 5; i++) {
            if(save[i].isChecked())
            {
                game.setScreen(game.gscreen);
                save[i].setChecked(false);
            }
        }
        if(new_game_exit.isChecked())
        {
            new_game_window.remove();
            new_game_exit.setChecked(false);
        }
        if(new_game_start_game.isChecked())
        {
            game.setScreen(game.gscreen);
            new_game_start_game.setChecked(false);
        }
        if(load_game_exit.isChecked())
        {
            load_game_window.remove();
            load_game_exit.setChecked(false);
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
