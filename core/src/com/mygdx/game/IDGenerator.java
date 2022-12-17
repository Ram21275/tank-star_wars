package com.mygdx.game;

public class IDGenerator {
    //generate a new integer
    private static int currentUID = 0;
    public static int getAvailID() {
        currentUID++;
        return currentUID;

    }
}
