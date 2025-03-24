module com.example.my_game_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    exports com.example.my_game_java.settings;

    opens com.example.my_game_java.controllers to javafx.fxml;

    exports com.example.my_game_java;
}
