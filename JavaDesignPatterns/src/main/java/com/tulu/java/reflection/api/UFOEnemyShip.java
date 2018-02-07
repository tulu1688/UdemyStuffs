package com.tulu.java.reflection.api;

public class UFOEnemyShip extends EnemyShip {

    private String idCode = "100";

    EnemyShipFactory shipFactory;

    public UFOEnemyShip(EnemyShipFactory shipFactory){

        this.shipFactory = shipFactory;
    }

    public UFOEnemyShip(int number, String randString){
        System.out.println("You sent: " + number + " - " + randString);
    }

    private String getPrivate(){
        return "How did you get this?";
    }

    private String getOtherPrivate(int i, String str){
        return "How did you get here " + i + " - " + str + "?";
    }


}
