package com.github.hastebrot.gradle.demoSimpleApplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DemoSimpleApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String className = getClass().getSimpleName();
        Pane sceneRoot = new StackPane(new Label(className));
        Scene scene = new Scene(sceneRoot, 400, 400);
        stage.setScene(scene);
        stage.setTitle(className);
        stage.show();
    }

}
