package com.quiz.app.controllers;

import com.quiz.app.views.*;
import com.quiz.enums.Views;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Stack;

public class ScreenController {
    private static ScreenController screenControllerInstance = null;
    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private final Stack<Pane> history = new Stack<>();

    public ScreenController(Scene main) {
        this.main = main;
        screenMap.put(Views.HOME.name(), new HomeView());
        screenMap.put(Views.GAME.name(), new GameView());
        screenMap.put(Views.LOGIN.name(), new LoginView());
        screenMap.put(Views.REGISTER.name(), new RegisterView());
        screenMap.put(Views.RESULTS.name(), new ResultsView());
        screenControllerInstance = this;
    }

    public static ScreenController getScreenControllerInstance() {
        return screenControllerInstance;
    }

    public static void setScreenControllerInstance(ScreenController screenControllerInstance) {
        ScreenController.screenControllerInstance = screenControllerInstance;
    }

    public void goBack(){
        history.pop();
        Pane currentScreen = history.peek();
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }

    public void activate(String name) {
        Pane currentScreen = screenMap.get(name);
        history.push(currentScreen);
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }
}
