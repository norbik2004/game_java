package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Item;

public class Warrior extends Character {

    public Warrior() {
        super(55,100,75,30,0.2,
                new Item("Big Sword",12,0,0));
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
