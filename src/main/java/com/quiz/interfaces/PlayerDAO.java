package com.quiz.interfaces;

import com.quiz.entities.Player;
import com.quiz.exceptions.AuthException;

import java.util.List;

public interface PlayerDAO {
    List<Player> getPlayerData();

    void updatePlayer(Player player);

    Player login(String email, String password) throws AuthException;

    void register(Player newPlayer);
}
