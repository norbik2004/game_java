package com.example.my_game_java.game.character.player;

import com.example.my_game_java.game.character.inventory.Inventory;
import com.example.my_game_java.game.character.inventory.Item;

import java.util.ArrayList;
import java.util.Random;

public abstract class Character {
    protected int damage;
    protected int health;
    protected int armour;
    protected double armour_pen;
    protected double crit_chance;
    protected Inventory inventory;
    protected int rooms_number;
    protected int current_room;

    Random random = new Random();

    public Character(int damage, int health, int armour, double armour_pen, double crit_chance, Item start_item) {
        this.damage = damage;
        this.health = health;
        this.armour = armour;
        this.armour_pen = armour_pen;
        this.crit_chance = crit_chance;
        this.inventory = new Inventory(new ArrayList<>());
        this.inventory.addItem(start_item);
        this.current_room = 0;
        this.rooms_number = random.nextInt(15) + 15;
    }

    public abstract void attack();

    public abstract void defend();

    //getters
    public int getDamage() {return damage;}
    public int getHealth() {return health;}
    public int getArmour() {return armour;}
    public double getArmour_pen() {return armour_pen;}
    public double getCrit_chance() {return crit_chance;}
    public Inventory getInventory() { return inventory; }
    public int getRooms_number() {return rooms_number;}
    public int getCurrent_room() {return current_room;}

    //setters
    public void setDamage(int damage) {this.damage = damage;}
    public void setHealth(int health) {this.health = health;}
    public void setArmour(int armour) {this.armour = armour;}
    public void setArmour_pen(double armour_pen) {this.armour_pen = armour_pen;}
    public void setCrit_chance(double crit_chance) {this.crit_chance = crit_chance;}
    public void setRooms_number(int rooms_number) {this.rooms_number = rooms_number;}
    public void setCurrent_room(int current_room) {this.current_room = current_room;}

    //inventory
    public void addItem(Item item) { inventory.addItem(item); }
    public void removeItem(Item item) { inventory.removeItem(item); }
}
