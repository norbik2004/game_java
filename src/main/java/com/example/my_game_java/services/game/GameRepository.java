package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.enemy.Enemy;
import com.example.my_game_java.game.character.enemy.Zombie;
import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.character.room.Room;
import com.example.my_game_java.game.services.PlayerManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.List;

public class GameRepository implements GameRepositoryInterface {
    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isProcessing = false;

    private Runnable onQueueEmpty;
    private Runnable onQueueNotEmpty;

    public void setOnQueueEmpty(Runnable callback) {
        this.onQueueEmpty = callback;
    }

    public void setOnQueueNotEmpty(Runnable callback) {
        this.onQueueNotEmpty = callback;
    }

    public synchronized void addConsoleText(String text, TextArea console) {
        boolean wasEmpty = messageQueue.isEmpty();
        messageQueue.offer(text);

        // to notify that queue is no longer empty
        if (wasEmpty && onQueueNotEmpty != null) {
            Platform.runLater(onQueueNotEmpty);
        }

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

            if (messageQueue.isEmpty() && onQueueEmpty != null) {
                Platform.runLater(onQueueEmpty);
            }
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

        for (ImageView icon : icons) {
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
            switch (item.getId()) {
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
    public void updateStats(List<Label> stats, Character player) {
        stats.get(0).setText("HEALTH: " + player.getHealth());
        stats.get(1).setText("ARMOR:  " + player.getArmour());
        stats.get(2).setText("DAMAGE: " + player.getDamage());
        stats.get(3).setText("CRITIC: " + player.getCrit_chance());
        stats.get(4).setText("AR PEN: " + player.getArmour_pen());
    }

    @Override
    public void updateHealthBar(Rectangle healthBar) {
        double currentHealth = PlayerManager.getInstance().getPlayer().getHealth();
        double newWidth = (300 * (currentHealth / 100));
        healthBar.setWidth(newWidth);
    }

    @Override
    public boolean checkIfGameOver(Character player) {
        return player.getHealth() > 0;
    }

    @Override
    public void walk(Character player) {
        int current_room = player.getCurrent_room();


        player.setCurrent_room(current_room + 1);
    }

    @Override
    public ArrayList<Room> generateRooms(Character player) {
        int rooms = player.getRooms_number();
        ArrayList<Room> rooms_to_return = new ArrayList<>();



        for (int i = 0; i < rooms; i++) {
            Room room = new Room(i,"dark room", generateEnemies(i));
            rooms_to_return.add(room);
        }

        return rooms_to_return;
    }

    /***
     * 0-10 tier 1
     * 5-10 50% chance for tier 2
     * 10-20 tier 2
     * 15-20 50% chance for tier 3
     * 20-30 tier 3
     * 25-30 50% chance for tier 4
     */
    private int generateTier(int i){
        int tier = 1;
        double chance;
        Random rand = new Random();

        if(i <= 10) {
            if(i >=5){
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 2;
                }
            }
        }else if(i <= 20) {
            tier = 2;
            if(i >= 15){
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 3;
                }
            }
        }else if(i <= 30) {
            tier = 3;
            if(i >= 25){
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 4;
                }
            }
        }else{
            tier = 4;
        }
        return tier;
    }

    /***
     * 0 - 10  rooms ONLY one enemy,
     * 10 - 20 rooms 25% chance for two enemies
     * 20 - 30 rooms 25% chance for three enemies and two enemies guaranteed
     */
    private ArrayList<Enemy> generateEnemies(int i) {
        Random rand = new Random();
        rand.nextDouble();
        double chance;
        int enemies = 1;

        if(i <= 20){
            chance = rand.nextDouble();
            if (chance < 0.25) {
                enemies = 2;
            }
        }else{
            enemies = 2;
            chance = rand.nextDouble();
            if (chance < 0.25) {
                enemies = 3;
            }
        }

        // later generate different type of enemies
        ArrayList<Enemy> list_enemies = new ArrayList<>();
        for(int y = 0; y < enemies; y++) {
            Zombie zombie = new Zombie(generateTier(i));
            list_enemies.add(zombie);
        }

        return list_enemies;
    }

}

