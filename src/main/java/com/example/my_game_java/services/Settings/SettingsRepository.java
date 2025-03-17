package com.example.my_game_java.services.Settings;

import com.example.my_game_java.settings.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SettingsRepository implements SettingsRepositoryInterface {
    private static final String SETTINGS_FILE_PATH = "src/main/resources/settings/settings.json";

    @Override
    public Settings loadSettings() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(SETTINGS_FILE_PATH);

        if (file.exists()) {
            System.out.println("FILE EXISTS");
            return objectMapper.readValue(file, Settings.class);
        } else {
            System.out.println("FILE DOES NOT EXIST");
            Settings defaultSettings = new Settings();
            defaultSettings.setMusic_on(true);
            defaultSettings.setScreen_width(1280);
            defaultSettings.setScreen_height(720);
            return defaultSettings;
        }
    }

    @Override
    public void saveSettings(Settings settings) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(SETTINGS_FILE_PATH), settings);
    }
}
