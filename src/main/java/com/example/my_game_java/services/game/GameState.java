package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.character.room.Room;

import java.util.List;

public class GameState {
    private Character player;
    private List<Room> rooms;

    public GameState() {
    }

    public GameState(Character player, List<Room> rooms) {
        this.player = player;
        this.rooms = rooms;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}

