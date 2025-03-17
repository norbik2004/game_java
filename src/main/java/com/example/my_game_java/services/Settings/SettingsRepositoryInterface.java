package com.example.my_game_java.services.Settings;

import com.example.my_game_java.settings.Settings;

import java.io.IOException;

public interface SettingsRepositoryInterface {
    Settings loadSettings() throws IOException;

    public void saveSettings(Settings settings) throws IOException;
}
