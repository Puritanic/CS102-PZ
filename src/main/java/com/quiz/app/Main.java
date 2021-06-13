package com.quiz.app;

import com.quiz.app.controllers.AuthController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
import com.quiz.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Glavna klasa aplikacije, odgovorna za pokretanje aplikacije,
 * inicijalizaciju Auth kontrolera, inicijalizaciju i čišćenje Hibernate factory-ja.
 *
 * @author dtasevski
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CS102 - Java quiz");
        HibernateUtil.createSessionFactory();
        new AuthController();

        primaryStage.setOnCloseRequest(e -> {
            HibernateUtil.closeSessionFactory();
        });

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 900, 650);
        ScreenController screenController = new ScreenController(scene);
        screenController.show(Views.HOME.name());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
