package com.example.my_game_java.scenes;

import com.example.my_game_java.controllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuScene {
    private final Scene scene;

    public MainMenuScene(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_game_java/main-menu-view.fxml"));
        primaryStage.setTitle("MainMenuScene");
        primaryStage.setFullScreenExitHint("");

        Parent root = loader.load();
        scene = new Scene(root, 1280, 720);
        

    }

    public Scene getScene() {
        return scene;
    }
}
