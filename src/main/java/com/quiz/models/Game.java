package com.quiz.models;

import com.quiz.entities.Player;
import com.quiz.entities.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Game klasa koje se koristi za inicijalizaciju igre.
 */
public class Game {
    private List<Question> questions = new ArrayList<>();
    private Player player;
    private int points = 0;

    public Game() {}

    public Game(List<Question> questions) {
        this.questions = questions;
    }

    public Game(List<Question> questions, Player player) {
        this.questions = questions;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getPoints() {
        return points;
    }

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
