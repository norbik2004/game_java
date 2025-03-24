package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Item;

public class Cleric extends Character {

    public Cleric() {
        super(30,150,50,0.3,0.01,
                new Item(3,"Holy Water",
                        "/photos/icons/priest_icon.jpg",12,0,0));
    }

    @Override
    public void attack() {
        System.out.println("Swings his holy water");
    }

    @Override
    public void defend() {
        System.out.println("Asks for help");
    }

    public void heal(){
        System.out.println("Heals his holy water");
    }
}
