package com.example.my_game_java.game.character.enemy;

public abstract class Enemy {
    protected String name;
    protected int damage;
    protected int health;
    protected int armour;
    protected double armour_pen;
    protected double crit_chance;
    protected int tier;
    protected String attack_sound_path;

    public Enemy(String baseName, int damage, int health, int armour, double armour_pen,
                 double crit_chance, int tier, String attack_sound_path) {
        this.tier = tier;
        this.name = generateTierName(baseName);

        double multiplier = getTierMultiplier(tier);

        this.damage = (int) (damage * multiplier);
        this.health = (int) (health * multiplier);
        this.armour = (int) (armour * multiplier);
        this.armour_pen = armour_pen * multiplier;
        this.crit_chance = crit_chance * multiplier;
        this.attack_sound_path = attack_sound_path;
    }

    private double getTierMultiplier(int tier) {
        return switch (tier) {
            case 1 -> 0.8;
            case 2 -> 1.1;
            case 3 -> 1.5;
            case 4 -> 1.9;
            default -> 1.0;
        };
    }

    private String generateTierName(String baseName) {
        return switch (tier){
            case 1 -> "Handicapped "+baseName;
            case 2 -> "Weak "+baseName;
            case 3 -> "Strong "+baseName;
            case 4 -> "Elite "+baseName;
            default -> "error "+baseName;
        };
    }


    //getters
    public int getDamage() {return damage;}
    public int getHealth() {return health;}
    public int getArmour() {return armour;}
    public double getArmour_pen() {return armour_pen;}
    public double getCrit_chance() {return crit_chance;}
    public int getTier() {return tier;}
    public String getName() {return name;}
    public String getAttack_sound_path() {return attack_sound_path;}

    //setters
    public void setHealth(int health) {this.health = health;}

    @Override
    public String toString() {
        return name + " [Tier: " + tier +
                ", Health: " + health +
                ", Damage: " + damage +
                ", Armour: " + armour + "]";
    }

}
