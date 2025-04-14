package com.example.my_game_java.game.services;

import com.example.my_game_java.services.game.GameState;

public class GameStateManager {
    private static GameStateManager instance;
    private GameState gameState;

    private GameStateManager() {}

    public static synchronized GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}