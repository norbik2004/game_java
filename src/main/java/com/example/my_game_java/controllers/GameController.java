package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.character.room.Room;
import com.example.my_game_java.game.services.GameStateManager;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.scenes.GameOverScene;
import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Scenes.SceneRepository;
import com.example.my_game_java.services.game.GameRepository;
import com.example.my_game_java.services.game.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;

import javax.tools.Tool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameController {
    private final AudioRepository audioRepository;
    private final GameRepository gameRepository;
    private final SceneRepository sceneRepository;
    private boolean isInCombat = false;
    private ParallelTransition outro;

    public GameController() {
        this.audioRepository = new AudioRepository();
        this.gameRepository = new GameRepository();
        this.sceneRepository = new SceneRepository();
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
    private Button yes;
    @FXML
    private Button no;

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

        //queues
        gameRepository.setOnQueueNotEmpty(() -> {
            attack.setDisable(true);
            walk.setDisable(true);
            block.setDisable(true);
            yes.setDisable(true);
            no.setDisable(true);
        });
        gameRepository.setOnQueueEmpty(() -> {
            attack.setDisable(false);
            walk.setDisable(false);
            block.setDisable(false);
            yes.setDisable(false);
            no.setDisable(false);
        });

        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.4);
        yes.setText("YES");
        no.setText("NO");
        attack.setText("ATTACK");
        walk.setText("WALK");
        block.setText("BLOCK");

        audioRepository.switchMusic("/audio/main_music.mp3");
        List<ImageView> icons = Arrays.asList(helmet_icon, main_hand_icon, boots_icon, armor_icon, second_hand_icon);
        List<Label> stats = Arrays.asList(health_bar, armor_bar, dmg_bar, crit_bar, armor_pen);
        List<Node> nodes = Arrays.asList(imageView,textArea,helmet_icon,main_hand_icon,second_hand_icon,boots_icon,armor_icon,
                yes,no,attack,walk,block,health_bar,healthBar,armor_bar,dmg_bar,crit_bar,armor_pen);




        //script
        if (player != null) {
            gameRepository.welcomingScript(textArea, player);
            gameRepository.initializeIcons(icons, player);

            update(stats, player);
        } else {
            System.out.println("No player selected.");
        }

        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);
        outro = sceneRepository.getSceneParallelTransition(nodes, false);
        intro.play();
    }

    @FXML
    private void onYes(){

    }

    @FXML
    private void onNo(){

    }

    @FXML
    private void onWalk() {
        gameRepository.addConsoleText("Walking... \n", textArea);
        gameRepository.walk(PlayerManager.getInstance().getPlayer(), textArea);
        nextTurn();
    }

    @FXML
    private void onBlock() {
        gameRepository.addConsoleText("You raise your guard...\n", textArea);
        nextTurn();
    }

    @FXML
    private void onAttack() {
        Character player = PlayerManager.getInstance().getPlayer();
        GameState gamestate = GameStateManager.getInstance().getGameState();
        Room currentRoom = gamestate.getRooms().get(player.getCurrent_room());

        if (!currentRoom.getEnemies().isEmpty()) {
            gameRepository.playerAttack(currentRoom.getEnemies(), player, textArea);
        } else {
            gameRepository.addConsoleText("There are no enemies to attack.\n", textArea);
        }

        nextTurn();
    }

    private void nextTurn() {
        Character player = PlayerManager.getInstance().getPlayer();
        GameState gamestate = GameStateManager.getInstance().getGameState();
        Room current_room = gamestate.getRooms().get(player.getCurrent_room());

        if (current_room.isCleared()) {
            System.out.println("Cleared");
            if (isInCombat) {
                audioRepository.switchMusic("/audio/main_music.mp3");
                isInCombat = false;
            }
        } else {
            if (!isInCombat) {
                audioRepository.switchMusic("/audio/fight_music.mp3");
                System.out.println("Player must fight in room " + player.getCurrent_room());
                isInCombat = true;
            }
        }


        List<Label> stats = Arrays.asList(health_bar, armor_bar, dmg_bar, crit_bar, armor_pen);
        update(stats, player);

        if (!gameRepository.checkIfGameOver(player)) {
            gameRepository.addConsoleText("You died. Game over.\n", textArea);
            audioRepository.switchMusic("/audio/game_over.mp3");
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                gameOver(outro);
            }, 6, TimeUnit.SECONDS);
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

    public void gameOver(ParallelTransition outro) {
        outro.setOnFinished(event ->{
            //change
            Scene gameOverScene;
            try {
                gameOverScene = new GameOverScene().getScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = (Stage) imageView.getScene().getWindow();
            stage.setTitle("Game Over");
            stage.setScene(gameOverScene);
        });
        outro.play();
    }



}
