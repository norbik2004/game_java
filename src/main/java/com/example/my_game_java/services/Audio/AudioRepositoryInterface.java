package com.example.my_game_java.services.Audio;

public interface AudioRepositoryInterface {

    void playMusic(String path);

    void playEffect(String path);

    void stopMusic();

    void switchMusic(String path);


}
