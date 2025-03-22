package com.example.my_game_java.game.character.player;

public class Cleric extends Character {

    public Cleric() {
        super(30,150,50,0.3,0.01);
    }

    @Override
    public void attack() {
        System.out.println("Swings his holy water");
    }

    @Override
    public void defend() {
        System.out.println("Asks for help");
    }
}
