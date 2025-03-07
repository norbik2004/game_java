package com.example.my_game_java.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public class SplashScreenController {

    @FXML
    private ImageView imageView;

    @FXML
    private Pane rootPane;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Label logo;

    @FXML
    private Label label_main;

    @FXML
    private Button button_main;

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/splash_screen.png")));
        imageView.setImage(image);

        imageView.setOpacity(0);
        logo.setOpacity(0);

        logo.setText("DUNGEONS");
        button_main.setText("ENTER");

        //fade for image
        FadeTransition ft = new FadeTransition(Duration.seconds(2), imageView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setDelay(Duration.seconds(0.5));

        //fade for text
        FadeTransition ft2 = new FadeTransition(Duration.seconds(2), logo);
        ft2.setFromValue(0);
        ft2.setToValue(1);
        ft2.setDelay(Duration.seconds(1.5));

        ft.play();
        ft2.play();
    }

}
