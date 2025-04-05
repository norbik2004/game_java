package com.example.my_game_java.services.game;
import com.example.my_game_java.game.character.player.Character;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.List;

public interface GameRepositoryInterface {
    void welcomingScript(TextArea console,Character player);
    void initializeIcons(List<ImageView> icons, Character player);
    int[][] generateMap();

}
