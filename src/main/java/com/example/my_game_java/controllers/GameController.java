package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.game.GameRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.Arrays;
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
            gameRepository.welcomingScript(textArea, player);
        } else {
            System.out.println("No player selected.");
        }


        //ends here

        List<Node> icons = Arrays.asList(helmet_icon, main_hand_icon, boots_icon, armor_icon, second_hand_icon);

        //Initialize start Equipment
        if (player != null) {
            List<Item> player_items = player.getInventory().getItems();
            for (Item item : player_items) {
                if(item.getId() == 3) {
                    Tooltip tooltip_main_weapon = new Tooltip();
                    tooltip_main_weapon.setText(item.getName() + " dmg: " + item.getDamageBonus());
                    main_hand_icon.setImage(new Image(getClass().getResourceAsStream(item.getIcon_path())));
                    Tooltip.install(main_hand_icon, tooltip_main_weapon);
                }

            }
        }


    }

    @FXML
    public void addText() {
        gameRepository.addConsoleText("this button just adds text \n", textArea);
    }
}
