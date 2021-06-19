package com.quiz.app.controllers;

import com.quiz.DAO.PlayerDAOImpl;
import com.quiz.entities.Player;
import com.quiz.enums.Views;
import com.quiz.exceptions.AuthException;
import com.quiz.util.PasswordUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Klasa zadužena za obradu zahteva ka PlayerDAOImpl objektu
 */
public class PlayerController {
    /**
     * Player servis za komunikaciju sa bazom podataka
     */
    private final PlayerDAOImpl playerService = new PlayerDAOImpl();

    /**
     * Podrazumevani konstruktor
     */
    public PlayerController() {
    }

    /**
     * Metoda zadužena za pokretanje login Player procesa
     *
     * @param email    - email igrača
     * @param password - šifra igrača
     * @throws AuthException - Greška koja se izbacuje ukoliko se desi neka greška tokom login procesa
     */
    public void loginPlayer(String email, String password) throws AuthException {
        Player player = playerService.login(email, password);
        AuthController.getAuthControllerInstance().setPlayer(player);
        ScreenController.getScreenControllerInstance().show(Views.HOME.name());
    }

    /**
     * Metoda zadužena za pokretanje procesa registracije za igrača
     *
     * @param email    - email igrača
     * @param username - korisničko ime igrača
     * @param password - plain tekst šifra igrača
     * @param isAdmin  - boolean flag koji indikuje admin status igrača
     */
    public void registerPlayer(String email, String username, String password, boolean isAdmin) {
        String encryptedPass = PasswordUtils.encryptPassword(password);

        Player newPlayer = new Player(username, email, encryptedPass, isAdmin);

        playerService.register(newPlayer);

        AuthController.getAuthControllerInstance().setPlayer(newPlayer);
        ScreenController.getScreenControllerInstance().show(Views.HOME.name());
    }

    /**
     * Metoda zadužena za ažuriranje informacija o igraču u bazi podataka
     *
     * @param player instanca klase Player
     */
    public void updatePlayer(Player player) {
        playerService.updatePlayer(player);
    }

    /**
     * Metoda zadužena za čitanje podataka svih igrača iz baze podataka
     *
     * @return lista igrača
     */
    public ObservableList<Player> getPlayerData() {
        ObservableList<Player> players = FXCollections.observableList(playerService.getPlayerData());
        return players;
    }
}
