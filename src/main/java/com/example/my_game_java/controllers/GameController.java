package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.game.GameRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;

import java.util.List;
import java.util.Objects;

public class GameController {
    private final AudioRepository audioRepository;
    private final GameRepository gameRepository;

    public GameController() {
        this.audioRepository = new AudioRepository();
        this.gameRepository = new GameRepository();
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView helmet_icon;

    @FXML
    private ImageView main_hand_icon;

    @FXML
    private ImageView second_hand_icon;

    @FXML
    private ImageView boots_icon;

    @FXML
    private ImageView armor_icon;

    @FXML
    private Button addTextButton;

    @FXML
    public void initialize() {
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();

        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.4);

        audioRepository.switchMusic("/audio/main_music.mp3");

        //script
        if (player != null) {
            gameRepository.addConsoleText(
                    "Welcome, " + player.getClass().getSimpleName() + ".\n" +
                            "You wake up on the cold, damp floor of an ancient dungeon...\n" +
                            "The air is thick with the scent of mold and decay.\n" +
                            "A faint flickering torch casts eerie shadows on the walls, \n" +
                            "revealing cryptic symbols carved into the stone.\n", textArea
            );

            gameRepository.addConsoleText(
                    "In the distance, you hear the slow, rhythmic dripping of water.\n" +
                            "But beneath that sound, something else lurks... \n" +
                            "A whisper? A growl? You're not alone.\n", textArea
            );

            gameRepository.addConsoleText(
                    "Your heart pounds as you struggle to recall how you got here.\n" +
                            "Your only choice is to move forward, deeper into the unknown.\n" +
                            "Find a way out before the darkness consumes you.\n", textArea
            );

        } else {
            System.out.println("No player selected.");
        }

        gameRepository.addConsoleText(
                "Be careful... This place is ancient and full of secrets.\n" +
                        "Not all of them will be friendly.\n", textArea
        );
        //ends here



        //Initialize start Equipment
        if (player != null) {
            List<Item> player_items = player.getInventory().getItems();
            for (Item item : player_items) {
                if(item.getId() == 3) {
                    main_hand_icon.setImage(new Image(getClass().getResourceAsStream(item.getIcon_path())));
                }

            }
        }


    }

    @FXML
    public void addText() {
        gameRepository.addConsoleText("this button just adds text \n", textArea);
    }
}
