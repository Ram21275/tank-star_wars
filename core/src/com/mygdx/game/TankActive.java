package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

//todo: make this class, delete unnecessary setters getters;
public abstract class TankActive {
    private Vector2 position;
    private int slope_angle;
    private HashMap<Integer,Bullet> ammunition;
    private static int max_angle;
    private static int min_angle;
    private static int min_power;
    private static int max_power;
    private int power;
    private int angle;
    private int fuel;
    private int max_speed;
    private TankBase base;
    private TankNozzle nozzle;
    private Ground ground;
    private ActivePlayer owner;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getSlope_angle() {
        return slope_angle;
    }

    public void setSlope_angle(int slope_angle) {
        this.slope_angle = slope_angle;
    }

    public HashMap<Integer, Bullet> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(HashMap<Integer, Bullet> ammunition) {
        this.ammunition = ammunition;
    }

    public static int getMax_angle() {
        return max_angle;
    }

    public static void setMax_angle(int max_angle) {
        TankActive.max_angle = max_angle;
    }

    public static int getMin_angle() {
        return min_angle;
    }

    public static void setMin_angle(int min_angle) {
        TankActive.min_angle = min_angle;
    }

    public static int getMin_power() {
        return min_power;
    }

    public static void setMin_power(int min_power) {
        TankActive.min_power = min_power;
    }

    public static int getMax_power() {
        return max_power;
    }

    public static void setMax_power(int max_power) {
        TankActive.max_power = max_power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }

    public TankBase getBase() {
        return base;
    }

    public void setBase(TankBase base) {
        this.base = base;
    }

    public TankNozzle getNozzle() {
        return nozzle;
    }

    public void setNozzle(TankNozzle nozzle) {
        this.nozzle = nozzle;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public ActivePlayer getOwner() {
        return owner;
    }

    public void setOwner(ActivePlayer owner) {
        this.owner = owner;
    }




}
