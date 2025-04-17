package com.example.my_game_java.game.character.enemy;

public class Ghost extends Enemy{
    public Ghost(int tier){
        super("Ghost",75,100,25,0.3,0.2, tier,
                "/audio/ghost_attack.mp3");
    }
}
