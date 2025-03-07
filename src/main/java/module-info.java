module com.example.my_game_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.media;

    opens com.example.my_game_java.controllers to javafx.fxml;

    exports com.example.my_game_java;
}
