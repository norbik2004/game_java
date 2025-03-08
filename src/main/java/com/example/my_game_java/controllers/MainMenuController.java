package com.example.my_game_java.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        imageView.setImage(image);
        logo.setText("MAIN MENU");
        start_game.setText("NEW GAME");

        imageView.setOpacity(0);

        //intro
        FadeTransition ft = new FadeTransition(Duration.seconds(1), imageView);
        ft.setFromValue(0);
        ft.setToValue(0.65);

        ParallelTransition parallelTransition = new ParallelTransition(ft);
        parallelTransition.play();
    }
}
