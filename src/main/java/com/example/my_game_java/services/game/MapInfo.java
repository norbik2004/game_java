package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.inventory.Inventory;
import com.example.my_game_java.game.character.player.Character;

public class MapInfo {
    private int[][] map;
    private Character player;
    private String class_name;

    public MapInfo(int[][] map, Character player, String class_name) {
        this.map = map;
        this.player = player;
        this.class_name = class_name;
    }


    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    /*
    MAP INFO
    0 - nothing
    1 - room
    2 - corridor
    3 - exit room
     */
}
