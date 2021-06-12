package com.quiz.app;

import com.quiz.entities.Player;
import com.quiz.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Question> questions = new ArrayList<>();
    private Player player;
    private int pointsNr = 0;

    public Game() {}

    public Game(List<Question> questions) {
        this.questions = questions;
    }

    public Game(List<Question> questions, Player player) {
        this.questions = questions;
        this.player = player;
    }

    public Game(List<Question> questions, Player player, int pointsNr) {
        this.questions = questions;
        this.player = player;
        this.pointsNr = pointsNr;
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

    public int getPointsNr() {
        return pointsNr;
    }

    public void setPointsNr(int pointsNr) {
        this.pointsNr = pointsNr;
    }

    @Override
    public String toString() {
        return "Game{" +
                "questions=" + questions +
                ", player=" + player +
                ", pointsNr=" + pointsNr +
                '}';
    }
}
