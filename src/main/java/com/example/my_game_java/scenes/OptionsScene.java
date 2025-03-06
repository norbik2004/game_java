package com.example.my_game_java.scenes;

import com.example.my_game_java.controllers.OptionsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsScene {
    private final Scene scene;

    public OptionsScene(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_game_java/options-scene-view.fxml"));
        primaryStage.setTitle("OptionsScene");

        Parent root = loader.load();
        scene = new Scene(root, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }
}

