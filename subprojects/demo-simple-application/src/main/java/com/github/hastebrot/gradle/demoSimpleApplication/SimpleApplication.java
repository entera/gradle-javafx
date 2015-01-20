package com.github.hastebrot.gradle.demoSimpleApplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane sceneRoot = new StackPane(new Label(getClass().getSimpleName()));
        Scene scene = new Scene(sceneRoot, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

}
