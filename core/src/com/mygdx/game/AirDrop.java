package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

//TODO: IMPLEMENT THIS
public class AirDrop implements Collidable, Serializable {
    private Vector2 position;
    private Bullet bullet;
    private int ID;


    AirDrop(Vector2 pos,int ID) {
            position = new Vector2(pos);
            bullet = new Bullet(null);
            this.ID = ID;
    }
    public void spawn(Vector2 pos)
    {

    }

    public void pickkup(TankActive tank){

    }

    @Override
    public void beginCollide(Collidable collide_with) {

    }

    @Override
    public void colide(Collidable collide_with) {

    }

    @Override
    public void endCollide(Collidable collide_with) {

    }

    @Override
    public void dispose() {

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public int getID() {
        return ID;
    }
}
