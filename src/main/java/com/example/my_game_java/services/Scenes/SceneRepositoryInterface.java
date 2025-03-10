package com.example.my_game_java.services.Scenes;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public interface SceneRepositoryInterface {

    ParallelTransition getSceneParallelTransition(List<Node> nodes, boolean isIntro);

    SequentialTransition getSceneSequentialTransition(List<Node> nodes, boolean isIntro);

    FadeTransition getFadeTransitionIntro(Node node);

    FadeTransition getFadeTransitionOutro(Node node);

}
