package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
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

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        imageView.setImage(image);
        logo.setText("MAIN MENU");
        start_game.setText("NEW GAME");
        load_game.setText("LOAD GAME");
        options.setText("OPTIONS");
        exit_game.setText("EXIT");

        imageView.setOpacity(0);

        //intro
        FadeTransition ft = new FadeTransition(Duration.seconds(1), imageView);
        ft.setFromValue(0);
        ft.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(ft);
        parallelTransition.play();

        //outro
        List<Node> elements = Arrays.asList(imageView, logo, start_game, load_game, options, exit_game);
        List<FadeTransition> transitions = new ArrayList<>();

        for (Node element : elements) {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), element);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            transitions.add(fadeTransition);
        }

        ParallelTransition outro = new ParallelTransition(transitions.toArray(new FadeTransition[0]));


        start_game.setOnAction(event -> handleStartGame());
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

    private void handleStartGame() {
        System.out.println("Start game clicked!");
        playClick();
    }

    private void handleLoadGame() {
        System.out.println("Load game clicked!");
        playClick();
    }

    private void handleOptions(ParallelTransition outro) throws IOException {
        System.out.println("Options clicked!");
        playClick();
        outro.setOnFinished(event -> {
            OptionsScene optionsScene;
            try {
                optionsScene = new OptionsScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) options.getScene().getWindow();
            stage.setTitle("Options");
            stage.setScene(optionsScene.getScene());
        });

        outro.play();
    }

    private void handleExitGame() {
        System.out.println("Exit game clicked!");
        playClick();
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void playClick(){
        AudioClip sound = new AudioClip(Objects.requireNonNull(getClass()
                .getResource("/audio/button_click.mp3")).toString());
        sound.setVolume(0.5);
        sound.play();
    }
}
