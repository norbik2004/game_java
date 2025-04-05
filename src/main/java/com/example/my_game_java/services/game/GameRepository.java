package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.services.PlayerManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;

public class GameRepository implements GameRepositoryInterface {
    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isProcessing = false;

    public synchronized void addConsoleText(String text, TextArea console) {
        messageQueue.offer(text);
        processNext(console);
    }

    private synchronized void processNext(TextArea console) {
        if (isProcessing || messageQueue.isEmpty()) {
            return;
        }

        isProcessing = true;
        String text = messageQueue.poll();

        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50 * index), event -> {
                console.appendText(String.valueOf(text.charAt(index)));
            }));
        }

        timeline.setOnFinished(event -> {
            isProcessing = false;
            processNext(console);
        });

        Platform.runLater(timeline::play);
    }

    @Override
    public void welcomingScript(TextArea console, Character player) {
        addConsoleText(
                "Welcome, " + player.getClass().getSimpleName() + ".\n" +
                        "You wake up on the cold, damp floor of an ancient dungeon...\n" +
                        "The air is thick with the scent of mold and decay.\n" +
                        "A faint flickering torch casts eerie shadows on the walls, \n" +
                        "revealing cryptic symbols carved into the stone.\n", console
        );

        addConsoleText(
                "In the distance, you hear the slow, rhythmic dripping of water.\n" +
                        "But beneath that sound, something else lurks... \n" +
                        "A whisper? A growl? You're not alone.\n", console
        );

        addConsoleText(
                "Your heart pounds as you struggle to recall how you got here.\n" +
                        "Your only choice is to move forward, deeper into the unknown.\n" +
                        "Find a way out before the darkness consumes you.\n", console
        );

        addConsoleText(
                "Be careful... This place is ancient and full of secrets.\n" +
                        "Not all of them will be friendly.\n", console
        );
    }

    @Override
    public void initializeIcons(List<ImageView> icons, Character player) {
        ImageView helmet_icon = icons.get(0);
        ImageView main_hand_icon = icons.get(1);
        ImageView boots_icon = icons.get(2);
        ImageView armor_icon = icons.get(3);
        ImageView second_hand_icon = icons.get(4);
        List<Item> player_items = player.getInventory().getItems();

        for(ImageView icon : icons) {
            icon.setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/photos/icons/empty_icon.png"))));
            icon.setOpacity(0.85);
        }

        /*
        ITEM ID
        HELMET  ID ---- 1
        ARMOR   ID ---- 2
        MAIN    ID ---- 3
        2 HAND  ID ---- 4
        BOOTS   ID ---- 5
        */

        for (Item item : player_items) {
            switch(item.getId()){
                case 1:
                    Tooltip tooltip_helmet = new Tooltip();
                    tooltip_helmet.setText(item.getName() + " armor: " + item.getDamageBonus());
                    helmet_icon.setImage(new Image(Objects.requireNonNull(getClass().
                            getResourceAsStream(item.getIcon_path()))));
                    Tooltip.install(helmet_icon, tooltip_helmet);
                    break;
                case 2:
                    Tooltip tooltip_armor = new Tooltip();
                    tooltip_armor.setText(item.getName() + " dmg: " + item.getDamageBonus());
                    armor_icon.setImage(new Image(Objects.requireNonNull(getClass().
                            getResourceAsStream(item.getIcon_path()))));
                    Tooltip.install(main_hand_icon, tooltip_armor);
                    break;
                case 3:
                    Tooltip tooltip_main_weapon = new Tooltip();
                    tooltip_main_weapon.setText(item.getName() + " dmg: " + item.getDamageBonus());
                    main_hand_icon.setImage(new Image(Objects.requireNonNull(getClass().
                            getResourceAsStream(item.getIcon_path()))));
                    Tooltip.install(main_hand_icon, tooltip_main_weapon);
                    break;
                case 4:
                    Tooltip tooltip_second_hand = new Tooltip();
                    tooltip_second_hand.setText(item.getName() + " dmg: " + item.getDamageBonus());
                    second_hand_icon.setImage(new Image(Objects.requireNonNull(getClass().
                            getResourceAsStream(item.getIcon_path()))));
                    Tooltip.install(second_hand_icon, tooltip_second_hand);
                    break;
                case 5:
                    Tooltip tooltip_boots = new Tooltip();
                    tooltip_boots.setText(item.getName() + " dmg: " + item.getDamageBonus());
                    boots_icon.setImage(new Image(Objects.requireNonNull(getClass().
                            getResourceAsStream(item.getIcon_path()))));
                    Tooltip.install(boots_icon, tooltip_boots);
                    break;
            }
        }
    }

    @Override
    public int[][] generateMap() {
        int[][] map = new int[15][5];
        Random rand = new Random();
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int zeroOrOne = rand.nextInt(2);
                map[i][j] = zeroOrOne;
            }
        }



        map = makeCorridors(map);

        Arrays.fill(map[14], 0);
        Arrays.fill(map[0], 0);
        for (int i = 0; i < map[0].length; i++) {
            if(map[13][i] == 1 || map[13][i] == 2){
                map[14][i] = 1;
                break;
            }
        }

        for (int i = 0; i < map[0].length; i++) {
            if(map[1][i] == 1 || map[1][i] == 2){
                map[0][i] = 3;
                break;
            }
        }

        //debugging
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        return map;
    }

    public int[][] makeCorridors(int[][] map){
        int rows = map.length -1;
        int cols = map[0].length;
        int flag;
        Random rand = new Random();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(map[i][j] == 1){
                    flag = 0;
                    for(int dx = -1; dx <= 1; dx++){
                        for(int dy = -1; dy <= 1; dy++){
                            int ni = i + dx;
                            int nj = j + dy;

                            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                                if(flag == 0 && map[ni][nj] == 0){
                                    if (rand.nextInt(100) > 71) {
                                        map[ni][nj] = 2;
                                        flag = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return map;
    }
}

