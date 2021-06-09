package com.quiz.app;

import com.quiz.app.views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws URISyntaxException, IOException {
        primaryStage.setTitle("Java kviz");

        StackPane root = new StackPane();
        root.getStylesheets().add("styles.css");

        HomeView homeView = new HomeView();
        GameView gameView = new GameView();
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        ResultsView resultsView = new ResultsView();

        root.getChildren().add(homeView);

        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
