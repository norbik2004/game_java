package com.example.my_game_java.services.Scenes;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class SceneRepository implements SceneRepositoryInterface {

    @Override
    public ParallelTransition getSceneParallelTransition(List<Node> nodes, boolean isIntro) {
        ParallelTransition parallelTransition = new ParallelTransition();

        for (Node node : nodes) {
            FadeTransition fade = isIntro ? getFadeTransitionIntro(node) : getFadeTransitionOutro(node);
            parallelTransition.getChildren().add(fade);
        }

        return parallelTransition;
    }

    @Override
    public SequentialTransition getSceneSequentialTransition(List<Node> nodes, boolean isIntro) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (Node node : nodes) {
            FadeTransition fade = isIntro ? getFadeTransitionIntro(node) : getFadeTransitionOutro(node);
            sequentialTransition.getChildren().add(fade);
        }

        return sequentialTransition;
    }

    @Override
    public FadeTransition getFadeTransitionIntro(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        return fadeTransition;
    }

    @Override
    public FadeTransition getFadeTransitionOutro(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        return fadeTransition;
    }

}
