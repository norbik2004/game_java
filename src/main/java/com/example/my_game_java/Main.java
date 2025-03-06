package com.example.my_game_java;

import com.example.my_game_java.scenes.MainMenuScene;
import com.example.my_game_java.scenes.OptionsScene;
import com.example.my_game_java.scenes.SplashScreenScene;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        OptionsScene optionsScene = new OptionsScene(primaryStage);
        MainMenuScene mainMenuScene = new MainMenuScene(primaryStage);
        SplashScreenScene splashScreenScene = new SplashScreenScene(primaryStage);

        primaryStage.setScene(splashScreenScene.getScene());

        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(false);

        primaryStage.show();



    }

    private static void setOptions(Stage primaryStage){
        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
