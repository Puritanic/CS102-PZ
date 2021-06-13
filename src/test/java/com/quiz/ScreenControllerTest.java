package com.quiz;

import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
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

    @Test
    public void nullWhenNotInitialized(){
        Assert.assertNull(ScreenController.getScreenControllerInstance());
    }

    @Test
    public void notnullWhenNotInitialized(){
        Map<String, Pane> screenMap = new HashMap<>();
        screenMap.put(Views.HOME.name(), new BorderPane());

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 650);
        new ScreenController(scene, screenMap);

        Assert.assertNotNull(ScreenController.getScreenControllerInstance());
    }

    @Test
    public void isAbleToGoBack(){
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
