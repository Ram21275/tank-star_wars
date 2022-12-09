package com.mygdx.game;


//TODO: write the player class
public class PassivePlayer {
    private int coins;
    private String name;
    private TankPassive tank;

    public void generateActivePlayer() {

    }
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TankPassive getTank() {
        return tank;
    }

    public void setTank(TankPassive tank) {
        this.tank = tank;
    }
}
