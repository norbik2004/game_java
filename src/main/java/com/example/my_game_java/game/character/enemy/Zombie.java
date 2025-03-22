package com.example.my_game_java.game.character.enemy;

public class Zombie extends Enemy{

    public Zombie(int tier){
        super("Zombie",12,30,30,0.1,0.01, tier);
    }

    @Override
    public void attack() {
        System.out.println(name + "attacked");
    }

    @Override
    public void defend() {
        System.out.println(name + "tries to defend");
    }
}
