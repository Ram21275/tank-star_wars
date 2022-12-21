package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

// TODO: 08/12/22 implement tank nozzle, uml remove colidable;
public class TankNozzle implements Serializable {
    private TankActive tank;
    private Sprite nozzle;
    private Vector2 nozzle_position;
    private Animation<TextureRegion> fire_anim;
    private Animation<TextureRegion> death_anim;

    public void generate(Vector2 position){

    }
    public TankActive getTank() {
        return tank;
    }

    public void setTank(TankActive tank) {
        this.tank = tank;
    }

    public Sprite getNozzle() {
        return nozzle;
    }

    public void setNozzle(Sprite nozzle) {
        this.nozzle = nozzle;
    }

    public Vector2 getNozzle_position() {
        return nozzle_position;
    }

    public void setNozzle_position(Vector2 nozzle_position) {
        this.nozzle_position = nozzle_position;
    }

    public Animation<TextureRegion> getFire_anim() {
        return fire_anim;
    }

    public void setFire_anim(Animation<TextureRegion> fire_anim) {
        this.fire_anim = fire_anim;
    }

    public Animation<TextureRegion> getDeath_anim() {
        return death_anim;
    }

    public void setDeath_anim(Animation<TextureRegion> death_anim) {
        this.death_anim = death_anim;
    }
}