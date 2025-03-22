package com.example.my_game_java.game.character.player;

public class Warrior extends Character {

    public Warrior() {
        super(55,100,75,30,0.2);
    }

    @Override
    public void attack() {
        System.out.println("Swings his giant sword");
    }

    @Override
    public void defend() {
        System.out.println("Uses shield");
    }
}
