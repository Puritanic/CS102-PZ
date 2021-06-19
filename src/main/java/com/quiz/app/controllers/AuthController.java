package com.quiz.app.controllers;

import com.quiz.entities.Player;
import com.quiz.exceptions.AlreadyInitializedException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Klasa zadužena za praćenje i čuvanje auth stanja korisnika tokom rada aplikacije.
 * Ova klasa funkcioniše kao jednostavna singleton klasa, kreira se jednom u toku rada aplikacije i onda se ta
 * instanca (preko polja authControllerInstance) upotrebljava tamo gde je potrebna.
 */
public class AuthController {
    /**
     * Instanca AuthController singleton klase
     */
    private static AuthController authControllerInstance = null;
    /**
     * SimpleObjectProperty zadužen za čuvanje trenutne instance Player klase
     */
    private final ObjectProperty<Player> player = new SimpleObjectProperty<>();

    /**
     * Podrazumevani konstruktor
     *
     * @throws AlreadyInitializedException - greška koja se može desiti ukoliko je klasa AuthController već inicijalizovana
     */
    public AuthController() throws AlreadyInitializedException {
        if (authControllerInstance != null) {
            throw new AlreadyInitializedException();
        }
        authControllerInstance = this;
    }

    /**
     * @param player - instanca klase Player, trenutno ulogovanog korisnika
     * @throws AlreadyInitializedException - greška koja se može desiti ukoliko je klasa AuthController već inicijalizovana
     */
    public AuthController(Player player) throws AlreadyInitializedException {
        if (authControllerInstance != null) {
            throw new AlreadyInitializedException();
        }
        this.playerProperty().set(player);
        authControllerInstance = this;
    }

    /**
     * Metoda zadužena za prosledjivanje statičke singleton instance AuthController klase
     *
     * @return AuthController - singleton instanca AuthController klase
     */
    public static AuthController getAuthControllerInstance() {
        return authControllerInstance;
    }

    public static void setAuthControllerInstance(AuthController authControllerInstance) {
        AuthController.authControllerInstance = authControllerInstance;
    }

    /**
     * @return instanca klase ObjectProperty, koja sadrži igrača
     */
    public final ObjectProperty<Player> playerProperty() {
        return this.player;
    }

    /**
     * @return trenutno ulogovani korisnik, instanca klase Player
     */
    public Player getPlayer() {
        return player.get();
    }

    /**
     * @param player instanca klase Player
     */
    public void setPlayer(Player player) {
        this.player.set(player);
    }
}
