package com.example.my_game_java.game.services;
import com.example.my_game_java.game.character.player.Character;

public class PlayerManager {
    private static PlayerManager instance;
    private Character player;

    private PlayerManager() {}

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }
}
