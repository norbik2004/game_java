package com.example.my_game_java.controllers;

import com.example.my_game_java.services.Scenes.SceneRepository;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import java.util.Arrays;
import java.util.List;

public class GameOverController {
    private final SceneRepository sceneRepository;
    public Label label;

    public GameOverController() {
        this.sceneRepository = new SceneRepository();
    }

    @FXML
    public void initialize() {
        List<Node> nodes = Arrays.asList(label);

        ParallelTransition intro = sceneRepository.getSceneParallelTransition(nodes, true);
        intro.play();

    }
}
