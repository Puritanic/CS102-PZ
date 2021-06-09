package com.quiz.app.controllers;

import com.quiz.app.views.*;
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
        screenMap.put("Home", new HomeView());
        screenMap.put("Game", new GameView());
        screenMap.put("Login", new LoginView());
        screenMap.put("Register", new RegisterView());
        screenMap.put("Results", new ResultsView());
        screenControllerInstance = this;
    }

    public static ScreenController getScreenControllerInstance() {
        return screenControllerInstance;
    }

    public static void setScreenControllerInstance(ScreenController screenControllerInstance) {
        ScreenController.screenControllerInstance = screenControllerInstance;
    }

    public void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    public void removeScreen(String name) {
        screenMap.remove(name);
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
