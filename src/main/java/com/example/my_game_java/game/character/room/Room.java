package com.example.my_game_java.game.character.room;

import com.example.my_game_java.game.character.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int id;
    private final String description;

    private final List<Enemy> enemies;

    public Room(int id, String description, ArrayList<Enemy> enemies) {
        this.id = id;
        this.description = description;
        this.enemies = enemies;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }


    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }


    public boolean isCleared() {
        return enemies.isEmpty();
    }

    @Override
    public String toString() {
        return "Room " + id + " - " + description +
                " | Enemies: " + enemies.size();
    }
}

