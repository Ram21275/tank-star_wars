package com.mygdx.game;


import java.io.Serializable;

//TODO: write the player class
public class PassivePlayer implements Serializable {
    private int coins;
    private String name;
    private TankPassive tank;
    private int player_no;

    public  PassivePlayer(int coins,String name,int player_no) {
        this.coins = coins;
        this.name = name;
        tank = null;
        this.player_no = player_no;
    }
    public ActivePlayer generateActivePlayer() {
        if(tank == null) {
            return null;
        }
        return  new ActivePlayer(this);
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

    public int getPlayer_no() {
        return player_no;
    }

    public void setPlayer_no(int player_no) {
        this.player_no = player_no;
    }
}
