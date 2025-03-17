package com.example.my_game_java.settings;

public class Settings {
    private boolean music_on;
    private int screen_height;
    private int screen_width;

    public boolean isMusic_on() {
        return music_on;
    }
    public void setMusic_on(boolean music_on) {
        this.music_on = music_on;
    }

    public int getScreen_height() {
        return screen_height;
    }
    public void setScreen_height(int screen_height) {
        this.screen_height = screen_height;
    }

    public int getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(int screen_width) {
        this.screen_width = screen_width;
    }

}
