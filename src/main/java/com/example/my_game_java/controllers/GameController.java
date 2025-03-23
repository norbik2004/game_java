package com.example.my_game_java.controllers;

import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import javafx.fxml.FXML;

public class GameController {
    private final AudioRepository audioRepository;

    public GameController() {
        this.audioRepository = new AudioRepository();
    }
    @FXML
    public void initialize(){
        System.out.println("Initializing Game");
        Character player = PlayerManager.getInstance().getPlayer();

        if (player != null) {
            System.out.println("Game started with: " + player.getClass().getSimpleName());
        } else {
            System.out.println("No player selected.");
        }

        audioRepository.stopMusic();
        audioRepository.playMusic("/audio/main_music.mp3");
        //audioRepository.switchMusic("/audio/main_music.mp3");
    }


}
