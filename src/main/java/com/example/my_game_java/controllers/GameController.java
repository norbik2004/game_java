package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.GameStateManager;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.game.GameRepository;
import com.example.my_game_java.services.game.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label health_bar;

    @FXML
    private Label armor_bar;

    @FXML
    private Label dmg_bar;

    @FXML
    private Label crit_bar;

    @FXML
    private Label armor_pen;

    @FXML
    public void initialize() {
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();
        GameState gameState = GameStateManager.getInstance().getGameState();

        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.4);
        addTextButton.setText("ACTION");
        attack.setText("ATTACK");
        walk.setText("WALK");
        block.setText("BLOCK");

        audioRepository.switchMusic("/audio/main_music.mp3");
        List<ImageView> icons = Arrays.asList(helmet_icon, main_hand_icon, boots_icon, armor_icon, second_hand_icon);
        List<Label> stats = Arrays.asList(health_bar, armor_bar, dmg_bar, crit_bar, armor_pen);

        //script
        if (player != null) {
            gameRepository.welcomingScript(textArea, player);
            gameRepository.initializeIcons(icons, player);
            update(stats, player);
            System.out.println(gameState.getPlayer().getArmour());

        } else {
            System.out.println("No player selected.");
        }

        //queues

        gameRepository.setOnQueueNotEmpty(() -> {
            attack.setDisable(true);
            walk.setDisable(true);
            block.setDisable(true);
            addTextButton.setDisable(true);
        });

        gameRepository.setOnQueueEmpty(() -> {
            attack.setDisable(false);
            walk.setDisable(false);
            block.setDisable(false);
            addTextButton.setDisable(false);
        });



    }

    @FXML
    private void onWalk() {
        gameRepository.addConsoleText("WALKING.....\n", textArea);
        gameRepository.walk(PlayerManager.getInstance().getPlayer());
        nextTurn();
    }

    @FXML
    private void onBlock() {
        gameRepository.addConsoleText("You raise your guard...\n", textArea);
        nextTurn();
    }

    @FXML
    private void onAttack() {
        gameRepository.addConsoleText("You swing your weapon...\n", textArea);
        nextTurn();
    }

    private void nextTurn() {
        Character player = PlayerManager.getInstance().getPlayer();
        if (player == null) return;

        List<Label> stats = Arrays.asList(health_bar, armor_bar, dmg_bar, crit_bar, armor_pen);
        update(stats, player);

        if (!gameRepository.checkIfGameOver(player)) {
            gameRepository.addConsoleText("You died. Game over.\n", textArea);
            disableActions();
        }
    }

    private void disableActions() {
        attack.setDisable(true);
        walk.setDisable(true);
        block.setDisable(true);
    }

    public void update(List<Label> stats, Character player) {
        gameRepository.updateStats(stats, player);
        gameRepository.updateHealthBar(healthBar);
    }

}
