package com.example.my_game_java.game.character.inventory;

public class Item {
    private final int id;
    private final String name;
    private final String icon_path;
    private final int damage;
    private final int healthBonus;
    private final int armorBonus;

    public Item(int id, String name,String icon_path, int damage, int healthBonus, int armorBonus) {
        this.id = id;
        this.icon_path = icon_path;
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
    public String getIcon_path() { return icon_path; }
    public int getId() { return id; }



    /*
    ITEM ID
    HELMET  ID ---- 1
    ARMOR   ID ---- 2
    MAIN    ID ---- 3
    2 HAND  ID ---- 4
    BOOTS   ID ---- 5
     */
}
