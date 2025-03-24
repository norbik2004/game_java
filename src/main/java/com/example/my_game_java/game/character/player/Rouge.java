package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Item;

public class Rouge extends Character {

    public Rouge() {
        super(30,100,35,0.5,0.5,
                new Item(3,"Kitchen Knife",
                        "/photos/icons/rouge_icon.jpg",12,0,0));
    }


    @Override
    public void attack() {
        System.out.println("Rouge gets his knife and attacks");
    }

    @Override
    public void defend() {
        System.out.println("Rouge tries to hide");
    }
}
