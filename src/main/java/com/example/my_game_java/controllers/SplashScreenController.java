package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.MainMenuScene;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class SplashScreenController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label logo;

    @FXML
    private Button button_main;

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/splash_screen.png")));
        imageView.setImage(image);

        imageView.setOpacity(0);
        logo.setOpacity(0);
        button_main.setOpacity(0);

        logo.setText("DUNGEONS");
        button_main.setText("ENTER");

        //intro
        FadeTransition ft = new FadeTransition(Duration.seconds(2), imageView);
        ft.setFromValue(0);
        ft.setToValue(1);

        FadeTransition ft2 = new FadeTransition(Duration.seconds(2), logo);
        ft2.setFromValue(0);
        ft2.setToValue(1);

        FadeTransition ft3 = new FadeTransition(Duration.seconds(2), button_main);
        ft3.setFromValue(0);
        ft3.setToValue(1);

        //play intro
        SequentialTransition sequentialTransition = new SequentialTransition(ft, ft2, ft3);
        sequentialTransition.play();

        //outro
        FadeTransition outro1 = new FadeTransition(Duration.seconds(1), imageView);
        outro1.setFromValue(1);
        outro1.setToValue(0);

        FadeTransition outro2 = new FadeTransition(Duration.seconds(1), logo);
        outro2.setFromValue(1);
        outro2.setToValue(0);

        FadeTransition outro3 = new FadeTransition(Duration.seconds(1), button_main);
        outro3.setFromValue(1);
        outro3.setToValue(0);



        button_main.setOnAction(e -> {

            AudioClip sound = new AudioClip(Objects.requireNonNull(getClass()
                    .getResource("/audio/button_click.mp3")).toString());
            sound.setVolume(0.5);
            sound.play();

            try {
                this.changeToMainMenu(outro1, outro2, outro3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void changeToMainMenu(FadeTransition outro1, FadeTransition outro2, FadeTransition outro3) throws IOException {
        ParallelTransition parallelTransition = new ParallelTransition(outro1, outro2, outro3);

        parallelTransition.setOnFinished(event -> {
            try {
                MainMenuScene mainMenuScene = new MainMenuScene();
                Stage stage = (Stage) button_main.getScene().getWindow();
                stage.setScene(mainMenuScene.getScene());
                stage.setTitle("Main Menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        parallelTransition.play();
    }



}
