package com.example.my_game_java.game.character.player;

abstract class Character {
    protected int damage;
    protected int health;
    protected int armour;
    protected double armour_pen;
    protected double crit_chance;

    public Character(int damage, int health, int armour, double armour_pen, double crit_chance) {
        this.damage = damage;
        this.health = health;
        this.armour = armour;
        this.armour_pen = armour_pen;
        this.crit_chance = crit_chance;
    }

    public abstract void attack();

    public abstract void defend();

    //getters
    public int getDamage() {return damage;}
    public int getHealth() {return health;}
    public int getArmour() {return armour;}
    public double getArmour_pen() {return armour_pen;}
    public double getCrit_chance() {return crit_chance;}

    //setters
    public void setDamage(int damage) {this.damage = damage;}
    public void setHealth(int health) {this.health = health;}
    public void setArmour(int armour) {this.armour = armour;}
    public void setArmour_pen(double armour_pen) {this.armour_pen = armour_pen;}
    public void setCrit_chance(double crit_chance) {this.crit_chance = crit_chance;}
}
