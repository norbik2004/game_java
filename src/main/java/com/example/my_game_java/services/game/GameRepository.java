package com.example.my_game_java.services.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

public class GameRepository implements GameRepositoryInterface {
    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isProcessing = false;

    public synchronized void addConsoleText(String text, TextArea console) {
        messageQueue.offer(text);
        processNext(console);
    }

    private synchronized void processNext(TextArea console) {
        if (isProcessing || messageQueue.isEmpty()) {
            return;
        }

        isProcessing = true;
        String text = messageQueue.poll();

        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50 * index), event -> {
                console.appendText(String.valueOf(text.charAt(index)));
            }));
        }

        timeline.setOnFinished(event -> {
            isProcessing = false;
            processNext(console);
        });

        Platform.runLater(timeline::play);
    }
}
