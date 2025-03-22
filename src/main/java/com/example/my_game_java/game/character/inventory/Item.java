package com.example.my_game_java.game.character.inventory;

public class Item {
    private final String name;
    private final int damage;
    private final int healthBonus;
    private final int armorBonus;

    public Item(String name, int damage, int healthBonus, int armorBonus) {
        this.name = name;
        this.damage = damage;
        this.healthBonus = healthBonus;
        this.armorBonus = armorBonus;
    }

    @Override
    public String toString() {
        return name + " (Damage +" + damage + ", Armor +" + armorBonus + ", Health Bonus +" + healthBonus + ")";
    }

    public String getName() { return name; }
    public int getDamageBonus() { return damage; }
    public int getHealthBonus() { return healthBonus; }
    public int getArmorBonus() { return armorBonus; }
}
