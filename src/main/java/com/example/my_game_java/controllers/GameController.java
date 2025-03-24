package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.util.Objects;

public class GameController {
    private final AudioRepository audioRepository;

    public GameController() {
        this.audioRepository = new AudioRepository();
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() {
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();

        if (player != null) {
            System.out.println("Game started with: " + player.getClass().getSimpleName());
            addConsoleTextWithAnimation("Gra została uruchomiona, rozpoczynasz gre jako " +
                    player.getClass().getSimpleName() + ", wkraczasz do podziemii. \n");
        } else {
            System.out.println("No player selected.");
        }

        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.5);

        audioRepository.switchMusic("/audio/main_music.mp3");
    }

    // dynamic text
    public void addConsoleTextWithAnimation(String message) {
        Timeline timeline = new Timeline();
        for (int i = 0; i < message.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50 * index), event -> {
                textArea.appendText(String.valueOf(message.charAt(index)));
            }));
        }
        timeline.play();
    }

    @FXML
    public void addText() {
        addConsoleTextWithAnimation("Nowa linia tekstu dodana! \n");
    }
}
