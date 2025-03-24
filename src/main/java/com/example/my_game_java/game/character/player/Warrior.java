package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Item;

public class Warrior extends Character {

    public Warrior() {
        super(30,100,75,0.3,0.2,
                new Item(3,"Big Sword",
                        "/photos/icons/warrior_icon.jpg",12,0,0));
    }

    @Override
    public void attack() {
        System.out.println("Swings his giant sword");
    }

    @Override
    public void defend() {
        System.out.println("Uses shield");
    }
}
