package com.quiz.app.controllers;

import com.quiz.app.views.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {
    private static ScreenController screenControllerInstance = null;
    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private Pane currentScreen;
    private Pane previousScreen;

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

    public Pane getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Pane currentScreen) {
        this.currentScreen = currentScreen;
    }

    public Pane getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(Pane previousScreen) {
        this.previousScreen = previousScreen;
    }

    public void goBack(){
        main.setRoot(previousScreen);
        currentScreen = previousScreen;
        previousScreen = null;
        main.getRoot().getStylesheets().add("styles.css");
    }

    public void activate(String name) {
        previousScreen = currentScreen;
        currentScreen = screenMap.get(name);
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }
}
