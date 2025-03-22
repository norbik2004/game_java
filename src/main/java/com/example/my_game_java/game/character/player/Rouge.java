package com.example.my_game_java.game.character.player;

public class Rouge extends Character {

    public Rouge() {
        super(30,100,45,0.5,0.5);
    }


    @Override
    public void attack() {
        System.out.println("Rouge gets his knife and attacks");
    }

    @Override
    public void defend() {
        System.out.println("Rouge tries to hide");
    }
}
