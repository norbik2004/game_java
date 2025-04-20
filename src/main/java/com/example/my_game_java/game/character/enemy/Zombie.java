package com.example.my_game_java.game.character.enemy;

public class Zombie extends Enemy{
    public Zombie(int tier){
        super("Zombie",65,100,50,0.1,0.33, tier,
                "/audio/zombie_attack.mp3");
    }

}
