package com.example.my_game_java.services.Audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class AudioRepository implements AudioRepositoryInterface {
    private static MediaPlayer mediaPlayer;
    private final String click_path = "/audio/button_click.mp3";

    @Override
    public void playMusic(String path) {
        try {
            String musicFile = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            fadeIn(mediaPlayer);
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
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

    @Override
    public void switchMusic(String path) {
        if (mediaPlayer != null) {
            fadeOut(mediaPlayer, () -> {
                mediaPlayer.stop();
                mediaPlayer = null;
                playMusic(path);
            });
        } else {
            playMusic(path);
        }
    }

    private void fadeOut(MediaPlayer player, Runnable onComplete) {
        final double fadeDuration = 2000; // 2 seconds
        final double fadeStep = 0.05;

        new Thread(() -> {
            try {
                double volume = player.getVolume();
                while (volume > 0) {
                    volume -= fadeStep;
                    if (volume < 0) volume = 0;
                    double finalVolume = volume;
                    javafx.application.Platform.runLater(() -> player.setVolume(finalVolume));
                    Thread.sleep((long) (fadeDuration * fadeStep));
                }
                javafx.application.Platform.runLater(onComplete);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fadeIn(MediaPlayer player) {
        final double fadeDuration = 2000; // 2 seconds
        final double fadeStep = 0.05;

        new Thread(() -> {
            try {
                double volume = 0;
                while (volume < 0.25) {
                    volume += fadeStep;
                    if (volume > 0.25) volume = 0.25;
                    double finalVolume = volume;
                    javafx.application.Platform.runLater(() -> player.setVolume(finalVolume));
                    Thread.sleep((long) (fadeDuration * fadeStep));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
