package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameController {
    private final AudioRepository audioRepository;

    public GameController() {
        this.audioRepository = new AudioRepository();
    }

    @FXML
    private ImageView imageView;


    @FXML
    public void initialize(){
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();

        if (player != null) {
            System.out.println("Game started with: " + player.getClass().getSimpleName());
        } else {
            System.out.println("No player selected.");
        }

        Image image = new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("/photos/game-main-theme.png")));
        imageView.setImage(image);
        imageView.setOpacity(0.5);



        audioRepository.switchMusic("/audio/main_music.mp3");
    }


}
