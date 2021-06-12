package com.quiz.app.controllers;

import com.quiz.entities.Player;
import com.quiz.entities.Question;
import com.quiz.models.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasa zadužena za čuvanje stanja igre (broj poena, igrač, indeks trenutnog pitanja)
 * i kreiranja seta pitanja koja će se koristiti u igri.
 */
public class GameController {
    private final int QUESTION_NUM = 5;
    private Game game;
    private Player player;
    private List<Question> gameQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;

    public GameController() {}

    public GameController(List<Question> questions) {
        buildQuestionsList(questions);
        initGame(gameQuestions);
    }

    public GameController(List<Question> questions, Player player) {
        buildQuestionsList(questions);
        this.player = player;
        initGame(gameQuestions, player);
    }

    /**
     * Metoda koja se koristi za ulogovane korisnike
     * @param questions lista pitanja koja će se koristiti u igri
     * @param player - instanca Player klase
     */
    private void initGame(List<Question> questions, Player player) {
        game = new Game(questions, player);
    }

    /**
     * Metoda koja se koristi za neulogovane korisnike
     * @param questions lista pitanja koja će se koristiti u igri
     */
    private void initGame(List<Question> questions) {
        game = new Game(questions);
    }

    /**
     * Kreira listu pitanja, tako što ispremešta redosled pitanja dobijenih iz baze podataka
     * i onda uzima prvih QUESTION_NUM broj pitanja, koja će se koristiti u igri.
     * @param questions lista svih pitanja iz baze podataka
     */
    private void buildQuestionsList(List<Question> questions) {
        Collections.shuffle(questions);
        for (int i = 0; i < QUESTION_NUM; i++) {
            gameQuestions.add(questions.get(i));
        }
    }

    /**
     * Metoda odgovorna za prosledjivanje sledećeg pitanja u listi pitanja koja se koriste u igri
     * i za inkrementovanje vrednosti currentQuestionIndex polja.
     * @return null ako je indeks trenutnog pitanja veći ili jednak maksimalnom broju pitanja
     *         Question u suprotnom.
     */
    public Question getNextQuestion() {
        if (currentQuestionIndex >= QUESTION_NUM) {
            return null;
        }

        Question nextQuestion = gameQuestions.get(currentQuestionIndex);
        currentQuestionIndex++;
        return nextQuestion;
    }

    /**
     * Metoda zadužena za prosledjivanje trenutnog pitanja.
     * @return Question | null
     */
    public Question getCurrentQuestion() {
        try {
            return gameQuestions.get((currentQuestionIndex - 1));
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Game getGame() { return game; }

    public void setGame(Game game) { this.game = game; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Question> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(List<Question> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}
