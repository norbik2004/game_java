package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.character.player.Cleric;
import com.example.my_game_java.game.character.player.Mage;
import com.example.my_game_java.game.character.player.Rouge;
import com.example.my_game_java.game.character.player.Warrior;
import com.example.my_game_java.scenes.GameScene;
import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Scenes.SceneRepository;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StartGameController {

    @FXML
    private ImageView background;
    @FXML
    private Label logo;
    @FXML
    private Button button_warrior;
    @FXML
    private Button button_mage;
    @FXML
    private Button button_rouge;
    @FXML
    private Button button_cleric;
    @FXML
    private Label mage_label;
    @FXML
    private Label rouge_label;
    @FXML
    private Label cleric_label;
    @FXML
    private Label warrior_label;
    @FXML
    private Label mage_label2;
    @FXML
    private Label rouge_label2;
    @FXML
    private Label cleric_label2;
    @FXML
    private Label warrior_label2;
    @FXML
    private ImageView mage_icon;
    @FXML
    private ImageView rouge_icon;
    @FXML
    private ImageView cleric_icon;
    @FXML
    private ImageView warrior_icon;

    private final SceneRepository sceneRepository;
    private final AudioRepository audioRepository;
    private ParallelTransition outro;
    private Character player;
    public StartGameController() {
        this.sceneRepository = new SceneRepository();
        this.audioRepository = new AudioRepository();
    }

    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        //icons
        Image warrior_icon_img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/icons/warrior_icon.jpg")));
        Image mage_icon_img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/icons/mage_icon.jpg")));
        Image rouge_icon_img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/icons/rouge_icon.jpg")));
        Image priest_icon_img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/icons/priest_icon.jpg")));
        warrior_icon.setImage(warrior_icon_img);
        mage_icon.setImage(mage_icon_img);
        rouge_icon.setImage(rouge_icon_img);
        cleric_icon.setImage(priest_icon_img);

        background.setImage(image);
        logo.setText("SELECT CLASS");
        button_warrior.setText("SELECT");
        button_cleric.setText("SELECT");
        button_mage.setText("SELECT");
        button_rouge.setText("SELECT");
        mage_label.setText("MAGE");
        rouge_label.setText("ROUGE");
        cleric_label.setText("CLERIC");
        warrior_label.setText("WARRIOR");

        List<Node> nodes = Arrays.asList(background, logo, button_warrior, button_cleric, button_mage, button_rouge,
                mage_label, cleric_label, warrior_label, rouge_label, cleric_label2, warrior_label2,
                mage_label2, rouge_label2, warrior_icon, mage_icon, rouge_icon, cleric_icon);
        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);

        outro = sceneRepository.getSceneParallelTransition(nodes, false);
        intro.play();

    }

    //class selection
    @FXML
    private void selectWarrior() {
        audioRepository.playClickSound();
        player = new Warrior();
        startGame(player);
    }

    @FXML
    private void selectMage() {
        audioRepository.playClickSound();
        player = new Mage();
        startGame(player);
    }

    @FXML
    private void selectRouge() {
        audioRepository.playClickSound();
        player = new Rouge();
        startGame(player);
    }

    @FXML
    private void selectCleric() {
        audioRepository.playClickSound();
        player = new Cleric();
        startGame(player);
    }

    private void startGame(Character player) {
        System.out.println("Selected player: " + player.getClass().getSimpleName());

        outro.setOnFinished(event ->{
            Scene gameScene;
            try {
                gameScene = new GameScene().getScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = (Stage) background.getScene().getWindow();
            stage.setTitle("Game");
            stage.setScene(gameScene);
        });
        outro.play();
    }
}
