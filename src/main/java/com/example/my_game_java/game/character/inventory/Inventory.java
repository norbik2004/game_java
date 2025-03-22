package com.example.my_game_java.game.character.inventory;

import java.util.List;

public class Inventory {
    private final List<Item> items;

    public Inventory(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) { this.items.add(item); }
    public void removeItem(Item item) { this.items.remove(item); }
    public List<Item> getItems() { return items; }


    //total additions
    public int getTotalDamageBonus() { return items.stream().mapToInt(Item::getDamageBonus).sum(); }
    public int getTotalHealthBonus() { return items.stream().mapToInt(Item::getHealthBonus).sum(); }
    public int getTotalArmourBonus() { return items.stream().mapToInt(Item::getArmorBonus).sum(); }

}
