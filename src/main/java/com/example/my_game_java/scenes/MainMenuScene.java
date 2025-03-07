package com.example.my_game_java.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class MainMenuScene {

    private final Scene scene;

    public MainMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_game_java/main-menu-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }
}
