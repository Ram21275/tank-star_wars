package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

// TODO: 08/12/22 implement tankbase, uml remove colidable;
public class TankBase implements Serializable {
    private TankActive tank;
    private Sprite base;
    private int base_angle;
    private Vector2 base_position;
    private Animation<TextureRegion> move_anim;
    private Animation<TextureRegion> death_anim;
    private Animation<TextureRegion> idle_anim;

    // TODO: 09/12/22  check the get field method

    public TankActive getTank() {
        return tank;
    }

    public void setTank(TankActive tank) {
        this.tank = tank;
    }

    public Sprite getBase() {
        return base;
    }

    public void setBase(Sprite base) {
        this.base = base;
    }

    public int getBase_angle() {
        return base_angle;
    }

    public void setBase_angle(int base_angle) {
        this.base_angle = base_angle;
    }

    public Vector2 getBase_position() {
        return base_position;
    }

    public void setBase_position(Vector2 base_position) {
        this.base_position = base_position;
    }

    public Animation<TextureRegion> getMove_anim() {
        return move_anim;
    }

    public void setMove_anim(Animation<TextureRegion> move_anim) {
        this.move_anim = move_anim;
    }

    public Animation<TextureRegion> getDeath_anim() {
        return death_anim;
    }

    public void setDeath_anim(Animation<TextureRegion> death_anim) {
        this.death_anim = death_anim;
    }

    public Animation<TextureRegion> getIdle_anim() {
        return idle_anim;
    }

    public void setIdle_anim(Animation<TextureRegion> idle_anim) {
        this.idle_anim = idle_anim;
    }
}
