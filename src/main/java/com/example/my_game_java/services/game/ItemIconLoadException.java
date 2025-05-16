package com.example.my_game_java.services.game;

public class ItemIconLoadException extends RuntimeException {
    public ItemIconLoadException(String itemName, String iconPath) {
        super("Failed to load icon for item: " + itemName + " (path: " + iconPath + ")");
    }

    public ItemIconLoadException(String itemName, Throwable cause) {
        super("Failed to load icon for item: " + itemName, cause);
    }
}

