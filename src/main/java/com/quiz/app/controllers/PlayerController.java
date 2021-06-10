package com.quiz.app.controllers;

import com.quiz.DAO.PlayerDAOImpl;
import com.quiz.entities.Player;
import com.quiz.exceptions.AuthException;
import com.quiz.util.PasswordUtils;

public class PlayerController {
    private final PlayerDAOImpl playerService = new PlayerDAOImpl();

    public PlayerController(){}

    public void loginPlayer(String email, String password) throws AuthException {
        playerService.login(email, password);
    }

    public void registerPlayer(String email, String username, String password, boolean isAdmin){
        String encryptedPass = PasswordUtils.encryptPassword(password);

        Player newPlayer = new Player(username, email, encryptedPass, isAdmin);

        playerService.register(newPlayer);
    }
}
