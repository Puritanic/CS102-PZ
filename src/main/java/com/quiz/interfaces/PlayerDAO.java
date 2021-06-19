package com.quiz.interfaces;

import com.quiz.entities.Player;
import com.quiz.exceptions.AuthException;

import java.util.List;

/**
 * Osnovni interfejs za rad sa igračima u bazi podataka
 */
public interface PlayerDAO {
    /**
     * Pokreće poziv kad bazi podataka odakle dobija listu svih registrovanih igrača.
     * @return List<Player> - Lista igrača sa svim potrebnim poljima. Trenutno se koristi samo za ResultsView.
     */
    List<Player> getPlayerData();

    /**
     * Prosledjuje modifikovanu instancu igrača bazi podataka, gde se vrši update
     * @param player Player objekat koji menjamo
     */
    void updatePlayer(Player player);

    /**
     * @param email String - email igrača
     * @param password String - Enkriptovana šifra korisnika
     * @return Player - instanca igrača koji je uspešno registrovan u bazi podataka
     * @throws AuthException - izuzetak koji se dobija ukoliko je email već zauzet, ili kredencijali nisu dobri
     */
    Player login(String email, String password) throws AuthException;

    /**
     * @param newPlayer novi igrač koga registrujemo
     */
    void register(Player newPlayer);
}
