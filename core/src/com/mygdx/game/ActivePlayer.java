package com.mygdx.game;

import java.io.Serializable;

//TODO: MAKE THIS CLASS
public class ActivePlayer {

    private GameScreen gamescreen;
    private int health;
    private TankActive tank;
    private String name;
    private PassivePlayer passivePlayer;

    ActivePlayer(PassivePlayer player_data)
    {
        // TODO: 08/12/22 implement after PassivePlayer
        gamescreen = MyGame.handle.getGscreen();
        health = 100;
        tank = player_data.getTank().getTankActive();
        tank.setOwner(this);
        name = player_data.getName();
        passivePlayer = player_data;
    }
    public void moveTank(float delta) {
        //todo implement move tank after tank active;
        tank.moveTank(delta);
    }
    public void setTankAngle(int angle){
        tank.setAngle(angle);
        //todo  implement after tank active;
    }

    public void setTankPower(int power) {
        tank.setPower(power);
        ///todo implement after tank active;
    }

    public void loseGame(){
         this.gamescreen.endGame(this);
    }
    public GameScreen getGamescreen() {
        return gamescreen;
    }

    public void setGamescreen(GameScreen gamescreen) {
        this.gamescreen = gamescreen;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        System.out.println(health);
    }

    public TankActive getTank() {
        return tank;
    }

    public void setTank(TankActive tank) {
        this.tank = tank;
    }

    public String getName() {
        return name;
    }

    public PassivePlayer getPassivePlayer() {
        return passivePlayer;
    }

    public void setPassivePlayer(PassivePlayer passivePlayer) {
        this.passivePlayer = passivePlayer;
    }

    public void setName(String name) {
        this.name = name;
    }
}
