package com.example.my_game_java.controllers;

import com.example.my_game_java.services.Scenes.SceneRepository;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StartGameController {

    @FXML
    private ImageView background;

    private final SceneRepository sceneRepository;
    public StartGameController() {
        this.sceneRepository = new SceneRepository();
    }

    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/photos/main_menu.jpg")));
        background.setImage(image);

        List<Node> nodes = Arrays.asList(background);
        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);
        ParallelTransition outro = sceneRepository.getSceneParallelTransition(nodes, false);
        intro.play();
    }
}
