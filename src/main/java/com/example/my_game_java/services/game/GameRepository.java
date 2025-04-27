package com.example.my_game_java.services.game;

import com.example.my_game_java.game.character.enemy.*;
import com.example.my_game_java.game.character.inventory.Item;
import com.example.my_game_java.game.character.inventory.ItemStore;
import com.example.my_game_java.game.character.player.Character;
import com.example.my_game_java.game.character.room.Room;
import com.example.my_game_java.game.services.GameStateManager;
import com.example.my_game_java.game.services.PlayerManager;
import com.example.my_game_java.services.Audio.AudioRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameRepository implements GameRepositoryInterface {
    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isProcessing = false;

    private Runnable onQueueEmpty;
    private Runnable onQueueNotEmpty;

    private final AudioRepository audioRepository = new AudioRepository();

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
                    Tooltip.install(armor_icon, tooltip_armor);
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
    public void walk(Character player, TextArea console) {
        int currentRoomNumber = player.getCurrent_room();
        GameStateManager gameState = GameStateManager.getInstance();
        Room currentRoom = gameState.getGameState().getRooms().get(currentRoomNumber);

        if (!currentRoom.isCleared()) {
            addConsoleText("You cannot leave yet! Enemies are still in the room.\n", console);
            return;
        }

        player.setCurrent_room(currentRoomNumber + 1);
        Room newRoom = gameState.getGameState().getRooms().get(player.getCurrent_room());
        ArrayList<Enemy> enemies = (ArrayList<Enemy>) newRoom.getEnemies();

        addConsoleText("You have walked into room with "
                + enemies.size()
                + " enemies: \n", console);

        for (Enemy enemy : enemies) {
            addConsoleText(enemy.toString() + "\n", console);
        }
    }

    @Override
    public ArrayList<Room> generateRooms(Character player) {
        int rooms = player.getRooms_number();
        ArrayList<Room> rooms_to_return = new ArrayList<>();

        for (int i = 0; i < rooms; i++) {
            Room room = new Room(i, "dark room", generateEnemies(i));
            rooms_to_return.add(room);
        }

        return rooms_to_return;
    }

    public void playerAttack(List<Enemy> enemies, Character player, TextArea console) {
        Random random = new Random();
        if (enemies.isEmpty()) {
            addConsoleText("No enemies in the room.\n", console);
            return;
        }

        Enemy target = enemies.get(0);
        int damageDealt = (int) (player.getDamage() - (target.getArmour() -
                (target.getArmour() * player.getArmour_pen())));

        if (damageDealt < 0) damageDealt = 0;
        double chance = random.nextDouble();

        if (player.getCrit_chance() > chance) {
            damageDealt *= 2;
            addConsoleText("Critical hit! \n", console);
        }

        target.setHealth(target.getHealth() - damageDealt);

        addConsoleText("You hit " + target.getName() + " for " + damageDealt + " damage!\n", console);

        if (target.getHealth() < 0) {
            target.setHealth(0);
        }

        addConsoleText("Enemy has now: " + target.getHealth() + "hp \n", console);

        if (target.getHealth() <= 0) {
            addConsoleText(target.getName() + " has been defeated!\n", console);
            enemies.remove(target);
        } else {
            int enemyDamage = (int) (target.getDamage() - (player.getArmour() -
                    (player.getArmour() * target.getArmour_pen())));

            if (enemyDamage < 0) enemyDamage = 0;
            double chance2 = random.nextDouble();

            if (target.getCrit_chance() > chance2) {
                enemyDamage *= 2;
                addConsoleText("Critical hit from the enemy! \n", console);
            }

            audioRepository.playEffect(target.getAttack_sound_path());

            player.setHealth(player.getHealth() - enemyDamage);


            addConsoleText(target.getName() + " retaliates for " + enemyDamage + " damage!\n", console);

            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                audioRepository.playEffect("/audio/player_hurt.mp3");
            }, 1, TimeUnit.SECONDS);

            if (player.getHealth() < 0) {
                player.setHealth(0);
            }
        }

        // Check if room is cleared
        Room room = GameStateManager.getInstance().getGameState()
                .getRooms().get(player.getCurrent_room());

        if (room.isCleared()) {
            addConsoleText("Room cleared!\n", console);
            enemyDropItem(console, player);
        }
    }

    @Override
    public void enemyDropItem(TextArea console, Character player) {
        addConsoleText("ITEM DROPPED", console);
    }

    private Item getItem(Character player) {
        ItemStore itemStore = ItemStore.getInstance();

        List<Item> armor_items = itemStore.getArmorItems();
        List<Item> weapons = new ArrayList<>();

        weapons = switch (player.getClass().getSimpleName()) {
            case "Cleric" -> itemStore.getClericItems();
            case "Mage" -> itemStore.getMageItems();
            case "Rogue" -> itemStore.getRogueItems();
            case "Warrior" -> itemStore.getWarriorItems();
            default -> weapons;
        };

        List<Item> combined_items = new ArrayList<>();
        combined_items.addAll(armor_items);
        combined_items.addAll(weapons);

        Collections.shuffle(combined_items);
        Random random = new Random();

        return combined_items.get(random.nextInt(combined_items.size()));
    }

    /***
     * 0-10 tier 1
     * 5-10 50% chance for tier 2
     * 10-20 tier 2
     * 15-20 50% chance for tier 3
     * 20-30 tier 3
     * 25-30 50% chance for tier 4
     */
    private int generateTier(int i) {
        int tier = 1;
        double chance;
        Random rand = new Random();

        if (i <= 10) {
            if (i >= 5) {
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 2;
                }
            }
        } else if (i <= 20) {
            tier = 2;
            if (i >= 15) {
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 3;
                }
            }
        } else if (i <= 30) {
            tier = 3;
            if (i >= 25) {
                chance = rand.nextDouble();
                if (chance > 0.5) {
                    tier = 4;
                }
            }
        } else {
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

        if (i <= 20) {
            chance = rand.nextDouble();
            if (chance < 0.25) {
                enemies = 2;
            }
        } else {
            enemies = 2;
            chance = rand.nextDouble();
            if (chance < 0.25) {
                enemies = 3;
            }
        }

        if (i == 0) enemies = 0;

        // later generate different type of enemies
        ArrayList<Enemy> list_enemies = new ArrayList<>();
        for (int y = 0; y < enemies; y++) {
            switch (rand.nextInt(4)) {
                case 0:
                    Zombie zombie = new Zombie(generateTier(i));
                    list_enemies.add(zombie);
                    break;
                case 1:
                    Wizard wizard = new Wizard(generateTier(i));
                    list_enemies.add(wizard);
                    break;
                case 2:
                    Skeleton skeleton = new Skeleton(generateTier(i));
                    list_enemies.add(skeleton);
                    break;
                case 3:
                    Ghost ghost = new Ghost(generateTier(i));
                    list_enemies.add(ghost);
                    break;
                default:
                    break;
            }

        }

        return list_enemies;
    }

}

