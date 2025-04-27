package com.example.my_game_java.services.game;
import com.example.my_game_java.game.character.enemy.Enemy;
import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;

import com.example.my_game_java.game.character.room.Room;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public interface GameRepositoryInterface {
    void welcomingScript(TextArea console,Character player);
    void initializeIcons(List<ImageView> icons, Character player);
    void updateStats(List<Label> stats, Character player);
    void updateHealthBar(Rectangle healthBar);
    boolean checkIfGameOver(Character player);
    void walk(Character player, TextArea console);
    ArrayList<Room> generateRooms(Character player);
    void playerAttack(List<Enemy> enemies, Character player, TextArea console);
    void enemyDropItem(TextArea console, Character player, Item item_dropped);
    void addItemToInv(Item item, Character player);
    void yesToItem();
}
