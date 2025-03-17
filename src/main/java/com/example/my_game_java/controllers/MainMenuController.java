package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.scenes.StartGameScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Scenes.SceneRepository;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainMenuController {
    private final AudioRepository audioRepository;
    private final SceneRepository sceneRepository;

    @FXML
    private ImageView imageView;

    @FXML
    private Label logo;

    @FXML
    private Button start_game;

    @FXML
    private StackPane rootPane;

    @FXML
    private Button load_game;

    @FXML
    private Button options;

    @FXML
    private Button exit_game;

    public MainMenuController() {
        this.audioRepository = new AudioRepository();
        this.sceneRepository = new SceneRepository();
    }

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        imageView.setImage(image);
        logo.setText("MAIN MENU");
        start_game.setText("NEW GAME");
        load_game.setText("LOAD GAME");
        options.setText("SETTINGS");
        exit_game.setText("EXIT");

        List<Node> nodes = Arrays.asList(imageView, logo, start_game, load_game, options, exit_game);

        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);
        ParallelTransition outro = sceneRepository.getSceneParallelTransition(nodes, false);
        intro.play();



        start_game.setOnAction(event -> handleStartGame(outro));
        load_game.setOnAction(event -> handleLoadGame());

        //options listener
        options.setOnAction(event -> {
            try {
                handleOptions(outro);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //exit game listener
        exit_game.setOnAction(event -> handleExitGame());
    }

    private void handleStartGame(ParallelTransition outro) {
        audioRepository.playClickSound();

        outro.setOnFinished(event -> {
            StartGameScene startGameScene;
            try {
                startGameScene = new StartGameScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) options.getScene().getWindow();
            stage.setTitle("Start Game");
            stage.setScene(startGameScene.getScene());
        });

        outro.play();
    }

    private void handleLoadGame() {
        System.out.println("Load game clicked!");
        audioRepository.playClickSound();
    }

    private void handleOptions(ParallelTransition outro) throws IOException {
        audioRepository.playClickSound();

        outro.setOnFinished(event -> {
            OptionsScene optionsScene;
            try {
                optionsScene = new OptionsScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) options.getScene().getWindow();
            stage.setTitle("Settings");
            stage.setScene(optionsScene.getScene());
        });

        outro.play();
    }

    private void handleExitGame() {
        System.out.println("Exit game clicked!");
        audioRepository.playClickSound();
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
