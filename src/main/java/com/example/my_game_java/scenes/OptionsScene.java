package com.example.my_game_java.scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;


import java.io.IOException;

public class OptionsScene {

    private final Scene scene;

    public OptionsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_game_java/options-scene-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }
}

