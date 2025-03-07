package com.example.my_game_java;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.scenes.SplashScreenScene;
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
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws IOException {

        OptionsScene optionsScene = new OptionsScene(primaryStage);
        MainMenuScene mainMenuScene = new MainMenuScene(primaryStage);
        SplashScreenScene splashScreenScene = new SplashScreenScene(primaryStage);

        primaryStage.setScene(splashScreenScene.getScene());


        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(false);

        this.PlayMusic();

        primaryStage.setOnCloseRequest(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });

        primaryStage.show();



    }

    private static void setOptions(Stage primaryStage){
        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }

    private void PlayMusic(){
        try{
            String musicFile = Objects.requireNonNull(getClass().getResource("/audio/lobby_music.mp3")).toExternalForm();
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.25);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch(Exception e){
            System.err.println("Error playing music" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
