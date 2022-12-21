package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

// TODO: 08/12/22 implement bullet
public class Bullet implements Projectile , Renderable {

    private Texture image;
    private String name;
    private Body physics_instance;

    private TankActive owner;
    private HashSet<Collidable> collision_seq;

    Bullet(TankActive owner)
    {
        this.owner = owner;
        this.generateAssets();
        collision_seq = new HashSet<>();
        MyGame.handle.getGscreen().render_these.add(this);
    }
    public void generateAssets() {
        image = new Texture(Gdx.files.internal("badlogic.jpg"));
        name = "Default Bullet";
    }

    @Override
    public void spawn(Vector2 pos, int power, int angle_deg) {
        BodyDef def = new BodyDef();
        //todo redo after physics is properly implemented;
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true; def.position.set(pos); def.angle = (float) (angle_deg/360f * Math.PI);//see in box2d
        physics_instance = MyGame.handle.getGscreen().physics_world.createBody(def);
        CircleShape shape = new CircleShape();

        shape.setRadius(3/32f);
        physics_instance.createFixture(shape,1.0f).setUserData(this);
        shape.dispose();
        physics_instance.applyLinearImpulse(new Vector2(power/5f,0).rotateDeg(angle_deg).scl(physics_instance.getMass()),physics_instance.getPosition(),true);
    }


    @Override
    public void dispose() {

        MyGame.handle.getGscreen().physics_world.destroyBody(physics_instance);
        image.dispose();
        MyGame.handle.getGscreen().render_these.remove(this);
    }

    @Override
    public void render() {

        Iterator i = collision_seq.iterator();
        while (i.hasNext()){
            colide((Collidable)i.next());
        }

    }

    @Override
    public void beginCollide(Collidable collide_with) {
        collision_seq.add(collide_with);
    }

    @Override
    public void endCollide(Collidable collide_with) {

    }

    @Override
    public void colide(Collidable collide_with) {
        if(collide_with.getClass() == Ground.class){
           float[][] f = ((Ground) collide_with).getGroundCoord();
            for (int i = 0; i < f.length; i++) {
                float x = f[i][0]*Gdx.graphics.getWidth()/32f; float y = f[i][1]*Gdx.graphics.getHeight()/32f;
                if(physics_instance.getPosition().dst(x,y) < 20/32f){
                    f[i][1] = (y - (float) Math.sqrt((Math.pow(20/32f,2)) - (Math.pow(x - physics_instance.getPosition().x,2))))/(Gdx.graphics.getHeight()/32f);
                }
            }
            ((Ground) collide_with).setGroundCoord(f);

        } else if(collide_with instanceof TankActive){
            ((TankActive) collide_with).onHitFallBack((5/32f)/(new Vector2(physics_instance.getPosition()).dst(((TankActive) collide_with).getPosition().scl(1/32f))));
        }
        dispose();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TankActive getOwner() {
        return owner;
    }

    public void setOwner(TankActive owner) {
        this.owner = owner;
    }
}
