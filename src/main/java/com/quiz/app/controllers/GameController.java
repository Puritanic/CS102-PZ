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
    /**
     * Maksimalan broj pitanja po igri
     */
    private final int QUESTION_NUM = 5;
    /**
     * Instanca Game klase
     */
    private Game game;
    /**
     * Instanca Player klase
     */
    private Player player;
    /**
     * Lista pitanja koja se koriste u game instanci
     */
    private List<Question> gameQuestions = new ArrayList<>();
    /**
     * Indeks trenutnog pitanja u igri
     */
    private int currentQuestionIndex = 0;

    /**
     * Podrazumevani konstruktor
     */
    public GameController() {
    }

    /**
     * @param questions lista pitanja
     */
    public GameController(List<Question> questions) {
        buildQuestionsList(questions);
        initGame(gameQuestions);
    }

    /**
     * @param questions lista pitanja
     * @param player    instanca klase Player
     */
    public GameController(List<Question> questions, Player player) {
        buildQuestionsList(questions);
        this.player = player;
        initGame(gameQuestions, player);
    }

    /**
     * Metoda koja se koristi za ulogovane korisnike
     *
     * @param questions lista pitanja koja će se koristiti u igri
     * @param player    - instanca Player klase
     */
    private void initGame(List<Question> questions, Player player) {
        game = new Game(questions, player);
    }

    /**
     * Metoda koja se koristi za neulogovane korisnike
     *
     * @param questions lista pitanja koja će se koristiti u igri
     */
    private void initGame(List<Question> questions) {
        game = new Game(questions);
    }

    /**
     * Kreira listu pitanja, tako što ispremešta redosled pitanja dobijenih iz baze podataka
     * i onda uzima prvih QUESTION_NUM broj pitanja, koja će se koristiti u igri.
     *
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
     *
     * @return null ako je indeks trenutnog pitanja veći ili jednak maksimalnom broju pitanja
     * Question u suprotnom.
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
     *
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

    /**
     * Getter metoda zadužena za čitanje trenutne instance klase Game
     *
     * @return game - instanca klase Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter metoda zadužena za setovanje instance klase Game
     *
     * @param game - instanca klase Game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter metoda zadužena za čitanje klase Player, igrača koji je trenutno u igri
     *
     * @return instanca klase Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter metoda za setovanje instance igrača, klasa Player
     *
     * @param player instanca klase Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Getter metoda za čitanje liste pitanja koja je u igri
     *
     * @return lista pitanja
     */
    public List<Question> getGameQuestions() {
        return gameQuestions;
    }

    /**
     * Setter metoda zadužena za setovanje liste pitanja koja će biti upotrebljena u igri
     *
     * @param gameQuestions lista pitanja
     */
    public void setGameQuestions(List<Question> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    /**
     * @return index trenutnog pitanja
     */
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    /**
     * @param currentQuestionIndex index trenutnog pitanja
     */
    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}
