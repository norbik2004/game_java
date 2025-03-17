package com.example.my_game_java;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.scenes.SplashScreenScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Settings.SettingsRepository;
import com.example.my_game_java.settings.Settings;
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
    private final SettingsRepository settingsRepository;

    public Main() {
        this.audioRepository = new AudioRepository();
        this.settingsRepository = new SettingsRepository();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        SplashScreenScene splashScreenScene = new SplashScreenScene();
        primaryStage.setScene(splashScreenScene.getScene());
        primaryStage.setTitle("Splash Screen");

        Settings settings = settingsRepository.loadSettings();

        boolean isMusicOn = settings.isMusic_on();

        if (isMusicOn) {
            audioRepository.playMusic("/audio/lobby_music.mp3");
        }

        primaryStage.setWidth(settings.getScreen_width());
        primaryStage.setHeight(settings.getScreen_height());
        primaryStage.setResizable(false);


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
