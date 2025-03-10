package com.example.my_game_java;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.scenes.SplashScreenScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private final AudioRepository audioRepository;

    public Main() {
        this.audioRepository = new AudioRepository();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        SplashScreenScene splashScreenScene = new SplashScreenScene(primaryStage);
        primaryStage.setScene(splashScreenScene.getScene());

        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(false);

        audioRepository.playMusic("/audio/lobby_music.mp3");


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
