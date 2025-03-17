package com.example.my_game_java.services.Audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class AudioRepository implements AudioRepositoryInterface{
    private static MediaPlayer mediaPlayer;
    private final String click_path = "/audio/button_click.mp3";

    @Override
    public void playMusic(String path) {
        try{
            String musicFile = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.25);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch(Exception e){
            System.err.println("Error playing music" + e.getMessage());
        }
    }

    @Override
    public void playClickSound() {
        AudioClip sound = new AudioClip(Objects.requireNonNull(getClass()
                .getResource(click_path)).toString());
        sound.setVolume(0.5);
        sound.play();
    }

    @Override
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }


}
