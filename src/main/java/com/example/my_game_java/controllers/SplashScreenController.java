package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Scenes.SceneRepository;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SplashScreenController {
    private final AudioRepository audioRepository;
    private final SceneRepository sceneRepository;
    private final String button_click = "/audio/button_click.mp3";

    @FXML
    private ImageView imageView;

    @FXML
    private Label logo;

    @FXML
    private Button button_main;

    public SplashScreenController() {
        this.audioRepository = new AudioRepository();
        this.sceneRepository = new SceneRepository();
    }

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
        List<Node> nodes = Arrays.asList(imageView, logo, button_main);
        SequentialTransition intro = sceneRepository.getSceneSequentialTransition(nodes, true);
        intro.play();

        //outro
        ParallelTransition outro = sceneRepository.getSceneParallelTransition(nodes, false);



        button_main.setOnAction(e -> {
            audioRepository.playClickSound(button_click);

            outro.setOnFinished(event ->{
                button_main.setVisible(false);

                //change
                Scene mainMenuScene;
                try {
                    mainMenuScene = new MainMenuScene().getScene();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Stage stage = (Stage) button_main.getScene().getWindow();
                stage.setTitle("Main Menu");
                stage.setScene(mainMenuScene);
            });
            outro.play();
        });
    }


}
