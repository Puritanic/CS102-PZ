package com.quiz.app.controllers;

import com.quiz.entities.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AuthController {
    private static AuthController authControllerInstance = null;
    private final ObjectProperty<Player> player = new SimpleObjectProperty<>();
    public final ObjectProperty<Player> playerProperty() {
        return this.player;
    }

    public AuthController() {
        authControllerInstance = this;
    }

    public AuthController(Player player) {
        this.playerProperty().set(player);
        authControllerInstance = this;
    }
    public static AuthController getAuthControllerInstance() {
        return authControllerInstance;
    }

    public static void setAuthControllerInstance(AuthController authControllerInstance) {
        AuthController.authControllerInstance = authControllerInstance;
    }

    public Player getPlayer() {
        return player.get();
    }

    public void setPlayer(Player player) {
        this.player.set(player);
    }
}
