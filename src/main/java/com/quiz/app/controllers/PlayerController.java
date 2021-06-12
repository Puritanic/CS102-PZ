package com.quiz.app.controllers;

import com.quiz.DAO.PlayerDAOImpl;
import com.quiz.entities.Player;
import com.quiz.enums.Views;
import com.quiz.exceptions.AuthException;
import com.quiz.util.PasswordUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerController {
    private final PlayerDAOImpl playerService = new PlayerDAOImpl();

    public PlayerController(){}

    public void loginPlayer(String email, String password) throws AuthException {
        Player player = playerService.login(email, password);
        AuthController.getAuthControllerInstance().setPlayer(player);
        ScreenController.getScreenControllerInstance().show(Views.HOME.name());
    }

    public void registerPlayer(String email, String username, String password, boolean isAdmin){
        String encryptedPass = PasswordUtils.encryptPassword(password);

        Player newPlayer = new Player(username, email, encryptedPass, isAdmin);

        playerService.register(newPlayer);

        AuthController.getAuthControllerInstance().setPlayer(newPlayer);
        ScreenController.getScreenControllerInstance().show(Views.HOME.name());
    }

    public void updatePlayer(Player player) {
        System.out.println(player);

        playerService.updatePlayer(player);
    }

    public ObservableList<Player> getPlayerData(){
        ObservableList<Player> players = FXCollections.observableList(playerService.getPlayerData());
        return players;
    }
}
