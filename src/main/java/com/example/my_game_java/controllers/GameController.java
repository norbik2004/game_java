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
import javafx.scene.shape.Rectangle;
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
    private Button attack;

    @FXML
    private Button walk;

    @FXML
    private Button block;

    @FXML
    private Rectangle healthBar;

    @FXML
    public void initialize() {
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();

        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.4);
        addTextButton.setText("DODAJ");
        attack.setText("ATTACK");
        walk.setText("WALK");
        block.setText("BLOCK");

        audioRepository.switchMusic("/audio/main_music.mp3");
        List<ImageView> icons = Arrays.asList(helmet_icon, main_hand_icon, boots_icon, armor_icon, second_hand_icon);

        //script
        if (player != null) {
            gameRepository.welcomingScript(textArea, player);
            gameRepository.initializeIcons(icons, player);
        } else {
            System.out.println("No player selected.");
        }


        updateHealthBar();

    }

    @FXML
    public void addText() {
        gameRepository.addConsoleText("this button just adds text \n", textArea);
    }

    private void updateHealthBar() {
        double currentHealth = PlayerManager.getInstance().getPlayer().getHealth();
        double newWidth = (300 * (currentHealth / 100));
        healthBar.setWidth(newWidth);
    }

    @FXML
    private void update() {
        Character player = PlayerManager.getInstance().getPlayer();
        player.setHealth(player.getHealth() - 5);
        updateHealthBar();
    }
}
