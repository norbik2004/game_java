package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.services.Audio.AudioRepository;
import com.example.my_game_java.services.Scenes.SceneRepository;
import com.example.my_game_java.services.Settings.SettingsRepository;
import com.example.my_game_java.settings.Settings;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OptionsController {

    @FXML
    private ImageView background;
    @FXML
    private Label logo;
    @FXML
    private CheckBox musicCheckBox;
    @FXML
    private Button applyButton;
    @FXML
    private Button backButton;

    private final SettingsRepository settingsRepository;
    private final AudioRepository audioRepository;
    private final SceneRepository sceneRepository;
    private MediaPlayer mediaPlayer;

    public OptionsController() {
        this.settingsRepository = new SettingsRepository();
        this.audioRepository = new AudioRepository();
        this.sceneRepository = new SceneRepository();
    }
    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        background.setImage(image);
        logo.setText("SETTINGS");

        List<Node> nodes = Arrays.asList(background, logo, musicCheckBox, applyButton, backButton);
        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);
        ParallelTransition outro = sceneRepository.getSceneParallelTransition(nodes, false);
        intro.play();


        try {
            Settings currentSettings = settingsRepository.loadSettings();
            musicCheckBox.setSelected(currentSettings.isMusic_on());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading settings.");
        }

        applyButton.setOnAction(event -> {
            try {
                audioRepository.playEffect("/audio/button_click.mp3");
                Settings updatedSettings = new Settings();
                updatedSettings.setMusic_on(musicCheckBox.isSelected());
                updatedSettings.setScreen_height(720);
                updatedSettings.setScreen_width(1280);
                settingsRepository.saveSettings(updatedSettings);
                System.out.println("Settings saved successfully");
                if(!musicCheckBox.isSelected()) {
                    audioRepository.stopMusic();
                }else{
                    audioRepository.playMusic("/audio/lobby_music.mp3");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error saving settings.");
            }
        });

        backButton.setOnAction(event -> {
            audioRepository.playEffect("/audio/button_click.mp3");
            outro.play();

            outro.setOnFinished(e -> {
                MainMenuScene mainMenuScene;
                try {
                    mainMenuScene = new MainMenuScene();
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
                Stage stage = (Stage) background.getScene().getWindow();
                stage.setTitle("Options");
                stage.setScene(mainMenuScene.getScene());
            });
        });

    }
}
