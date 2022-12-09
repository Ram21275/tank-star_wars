package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

// TODO: 08/12/22 implement bullet
public class Bullet implements Projectile , Renderable {

    private Texture image;
    private String name;
    private Body physics_instance;
    private TankActive owner;

    Bullet(TankActive owner)
    {
        this.owner = owner;
        this.generateAssets();
    }
    public void generateAssets() {
        image = new Texture(Gdx.files.internal("badlogic.jpg"));
        name = "Default Bullet";
    }

    @Override
    public void spawn(Vector2 pos, int power, int angle) {
        BodyDef def = new BodyDef();
        //todo redo after physics is properly implemented;
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = false; def.position.set(pos); def.angle = (float) (angle/360f * Math.PI);//see in box2d
        physics_instance = MyGame.handle.physics_world.createBody(def);
        CircleShape shape = new CircleShape();
        shape.dispose();
        shape.setRadius(0.125f);
    }

    @Override
    public void collide(Collidable collide_with) {
        if(collide_with == owner) {
            return;
        }
        if(collide_with instanceof TankActive) {
            // TODO: 09/12/22  write after tank active after hit is created;
            return;
        } else if (collide_with instanceof Ground) {
            // TODO: 09/12/22  write after ground erasure is created

        } else {
            collide_with.dispose(); // might need rewrite
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {

    }
}
