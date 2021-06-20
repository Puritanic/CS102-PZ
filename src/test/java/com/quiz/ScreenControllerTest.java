package com.quiz;

import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
import com.quiz.exceptions.AlreadyInitializedException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ScreenControllerTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    /**
     * Provera da je ScreenController singleton instanca null pre inicijalizacije
     */
    @Test
    public void nullWhenNotInitialized() {
        Assert.assertNull(ScreenController.getScreenControllerInstance());
    }

    /**
     * Provera da ScreenController singleton instanca nije null nakon inicijalizacije
     */
    @Test(expected = AlreadyInitializedException.class)
    public void notnullWhenNotInitialized() throws AlreadyInitializedException {
        Map<String, Pane> screenMap = new HashMap<>();
        screenMap.put(Views.HOME.name(), new BorderPane());

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 650);
        new ScreenController(scene, screenMap);

        Assert.assertNotNull(ScreenController.getScreenControllerInstance());
    }

    /**
     * Provera goBack metode i da li history radi očeivano
     */
    @Test
    public void isAbleToGoBack() throws AlreadyInitializedException {
        Map<String, Pane> screenMap = new HashMap<>();
        screenMap.put(Views.HOME.name(), new BorderPane());
        screenMap.put(Views.LOGIN.name(), new StackPane());

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 650);
        new ScreenController(scene, screenMap);

        ScreenController sc = ScreenController.getScreenControllerInstance();
        sc.show(Views.HOME.name());

        sc.show(Views.LOGIN.name());

        Pane currentScreen = sc.getHistory().peek();

        Assert.assertTrue(currentScreen instanceof StackPane);

        sc.goBack();

        currentScreen = sc.getHistory().peek();

        Assert.assertTrue(currentScreen instanceof BorderPane);
    }
}
