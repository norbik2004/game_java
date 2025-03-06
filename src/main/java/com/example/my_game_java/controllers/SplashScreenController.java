package com.example.my_game_java.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SplashScreenController {

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/photos/splash_screen.png"));
        imageView.setImage(image);
    }
}
