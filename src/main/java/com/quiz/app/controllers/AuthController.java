package com.quiz.app.controllers;

import com.quiz.entities.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Klasa zadužena za praćenje i čuvanje auth stanja korisnika tokom rada aplikacije.
 * Ova klasa funkcioniše kao jednostavna singleton klasa, kreira se jednom u toku rada aplikacije i onda se ta
 * instanca (preko polja authControllerInstance) upotrebljava tamo gde je potrebna.
 */
public class AuthController {
    private static AuthController authControllerInstance = null;
    private final ObjectProperty<Player> player = new SimpleObjectProperty<>();

    public AuthController() {
        authControllerInstance = this;
    }

    public AuthController(Player player) {
        this.playerProperty().set(player);
        authControllerInstance = this;
    }

    /**
     * Metoda zadužena za prosledjivanje statičke singleton instance AuthController klase
     * @return AuthController - singleton instanca AuthController klase
     */
    public static AuthController getAuthControllerInstance() {
        return authControllerInstance;
    }

    public static void setAuthControllerInstance(AuthController authControllerInstance) {
        AuthController.authControllerInstance = authControllerInstance;
    }

    public final ObjectProperty<Player> playerProperty() {
        return this.player;
    }

    public Player getPlayer() {
        return player.get();
    }

    public void setPlayer(Player player) {
        this.player.set(player);
    }
}
