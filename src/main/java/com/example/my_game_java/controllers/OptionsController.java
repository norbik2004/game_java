package com.example.my_game_java.controllers;

import com.example.my_game_java.scenes.OptionsScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OptionsController {

    @FXML
    private ImageView background;

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        background.setImage(image);
    }
}
