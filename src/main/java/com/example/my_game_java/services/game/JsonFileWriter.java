package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.player.Character;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class JsonFileWriter {
    private static final int MAX_SAVES = 4;
    private static final String SAVE_DIRECTORY = "saves/";
    private static final String SAVE_PREFIX = "save_";
    private static final String SAVE_EXTENSION = ".json";

    public static void saveMapInfo(Character player) {
        File directory = new File(SAVE_DIRECTORY);
        // if doesnt exist folder
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // getter for all files
        File[] saveFiles = directory.listFiles((dir, name) -> name.startsWith(SAVE_PREFIX) && name.endsWith(SAVE_EXTENSION));

        if (saveFiles != null && saveFiles.length >= MAX_SAVES) {
            // sorting by last modified
            Arrays.sort(saveFiles, Comparator.comparingLong(File::lastModified));

            // deleting oldest
            if (saveFiles[0].delete()) {
                System.out.println("Deleted oldest save: " + saveFiles[0].getName());
            } else {
                System.out.println("Failed to delete old save: " + saveFiles[0].getName());
            }
        }

        // next slot
        int nextSlot = 1;
        while (new File(SAVE_DIRECTORY + SAVE_PREFIX + nextSlot + SAVE_EXTENSION).exists()) {
            nextSlot++;
        }

        String filePath = SAVE_DIRECTORY + SAVE_PREFIX + nextSlot + SAVE_EXTENSION;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), player);
            System.out.println("Saved game in: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
