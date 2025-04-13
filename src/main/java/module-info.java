module com.example.my_game_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires java.compiler;
    exports com.example.my_game_java.settings;

    opens com.example.my_game_java.controllers to javafx.fxml;
    opens com.example.my_game_java.services.game to com.fasterxml.jackson.databind;
    opens com.example.my_game_java.game.character.player to com.fasterxml.jackson.databind;
    exports com.example.my_game_java.game.character.inventory to com.fasterxml.jackson.databind;
    exports com.example.my_game_java.game.character.room to com.fasterxml.jackson.databind;
    exports com.example.my_game_java.game.character.enemy to com.fasterxml.jackson.databind;

    exports com.example.my_game_java;
}
