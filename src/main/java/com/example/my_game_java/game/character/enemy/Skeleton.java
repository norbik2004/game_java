package com.example.my_game_java.game.character.enemy;

public class Skeleton extends Enemy{
    public Skeleton(int tier){
        super("Skeleton",40,100,50,0.1,0.1, tier,
                "/audio/skeleton_attack.mp3");
    }
}
