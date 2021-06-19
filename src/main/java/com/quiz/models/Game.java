package com.quiz.models;

import com.quiz.entities.Player;
import com.quiz.entities.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Game klasa koje se koristi za inicijalizaciju igre.
 */
public class Game {
    /**
     * Pitanja koja koristimo u igri
     */
    private List<Question> questions = new ArrayList<>();
    /**
     * Instanca Player klase ukoliko je autentikovani korisnik pokrenuo igru
     */
    private Player player;
    /**
     * Broj osvojenih poena tokom igre
     */
    private int points = 0;

    /**
     * Podrazumevani konstruktor
     */
    public Game() {}

    /**
     * @param questions lista pitanja
     */
    public Game(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @param questions lista pitanja
     * @param player igrač
     */
    public Game(List<Question> questions, Player player) {
        this.questions = questions;
        this.player = player;
    }

    /**
     * @return instanca Player klase, igrač
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player instanca Player klase, igrač
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return lista pitanja koja se koriste u igri
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions lista pitanja koja će se koristiti u igri
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @return broj osvojenih poena
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points broj poena
     */
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Game{" +
                "questions=" + questions +
                ", player=" + player +
                ", points=" + points +
                '}';
    }
}
