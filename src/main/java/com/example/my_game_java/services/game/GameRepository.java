package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
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

    @Override
    public void welcomingScript(TextArea console, Character player) {
        addConsoleText(
                "Welcome, " + player.getClass().getSimpleName() + ".\n" +
                        "You wake up on the cold, damp floor of an ancient dungeon...\n" +
                        "The air is thick with the scent of mold and decay.\n" +
                        "A faint flickering torch casts eerie shadows on the walls, \n" +
                        "revealing cryptic symbols carved into the stone.\n", console
        );

        addConsoleText(
                "In the distance, you hear the slow, rhythmic dripping of water.\n" +
                        "But beneath that sound, something else lurks... \n" +
                        "A whisper? A growl? You're not alone.\n", console
        );

        addConsoleText(
                "Your heart pounds as you struggle to recall how you got here.\n" +
                        "Your only choice is to move forward, deeper into the unknown.\n" +
                        "Find a way out before the darkness consumes you.\n", console
        );

        addConsoleText(
                "Be careful... This place is ancient and full of secrets.\n" +
                        "Not all of them will be friendly.\n", console
        );
    }
}
