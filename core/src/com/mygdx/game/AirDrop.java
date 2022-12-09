package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

//TODO: IMPLEMENT THIS
public class AirDrop implements Collidable {
    private Vector2 position;
    private Bullet bullet;

    public void spawn(Vector2 pos)
    {

    }

    public void pickkup(TankActive tank){

    }

    @Override
    public void collide(Collidable collide_with) {

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
}
