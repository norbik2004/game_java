package com.example.my_game_java.game.character.inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {
    private static final ItemStore instance = new ItemStore();

    private final List<Item> cleric_items;
    private final List<Item> mage_items;
    private final List<Item> rouge_items;
    private final List<Item> warrior_items;
    private final List<Item> armor_items;

    private ItemStore() {
        cleric_items = new ArrayList<>(List.of(
                new Item(3,"Four Elements", "/photos/items/weapons/cleric/4_elements.jpg",100,0,0),
                new Item(3,"Blue Water", "/photos/items/weapons/cleric/blue_water.jpg",50,0,0),
                new Item(3,"Lighting Ring", "/photos/items/weapons/cleric/lighting_ring.jpg",65,0,0),
                new Item(3,"Ruby Script", "/photos/items/weapons/cleric/ruby_spells.jpg",90,0,0),
                new Item(3,"Sapphire Raw Gem", "/photos/items/weapons/cleric/sapphire_gem.jpg",80,0,0)
        ));

        mage_items = new ArrayList<>(List.of(
                new Item(3,"Magic Book", "/photos/items/weapons/mage/magic_book.jpg",30,0,0),
                new Item(3,"Magic Rune", "/photos/items/weapons/mage/magic_rune.jpg",25,0,0),
                new Item(3,"Poison Book", "/photos/items/weapons/mage/poison_book.jpg",45,0,0),
                new Item(3,"Ruby Rune","/photos/items/weapons/mage/ruby_rune.jpg",75,0,0),
                new Item(3,"Wand","/photos/items/weapons/mage/wand.jpg",100,0,0)
        ));

        rouge_items = new ArrayList<>(List.of(
                new Item(3,"Ultimate Knife", "/photos/items/weapons/rouge/ultimate_knife.jpg",100,0,0),
                new Item(3,"Short Knife", "/photos/items/weapons/rouge/short_knife.jpg",50,0,0),
                new Item(3,"Ruby Knife", "/photos/items/weapons/rouge/ruby_knife.jpg",65,0,0),
                new Item(3,"Red Knifes", "/photos/items/weapons/rouge/red_knifes.jpg",90,0,0),
                new Item(3,"Ghost Knife", "/photos/items/weapons/rouge/ghost_knife.jpg",80,0,0)
        ));

        warrior_items = new ArrayList<>(List.of(
                new Item(3,"Warrior Wand", "/icons",30,0,0)
        ));

        armor_items = new ArrayList<>(List.of(
                new Item(3,"Armor Wand", "/icons",0,15,30)
        ));
    }

    public static ItemStore getInstance() {
        return instance;
    }

    public List<Item> getClericItems() {
        return cleric_items;
    }

    public List<Item> getMageItems() {
        return mage_items;
    }

    public List<Item> getRogueItems() {
        return rouge_items;
    }

    public List<Item> getWarriorItems() {
        return warrior_items;
    }

    public List<Item> getArmorItems() {
        return armor_items;
    }
}
