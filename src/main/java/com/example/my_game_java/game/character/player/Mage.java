package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Item;

public class Mage extends Character {
    public Mage() {
        super(45,100,50,0.2,0.1,
                new Item("Magic Wand",12,0,0));
    }

    @Override
    public void attack() {
        System.out.println("Mage casts a powerful spell!");
    }

    @Override
    public void defend() {
        System.out.println("Mage casts a magic shield");
    }
}
