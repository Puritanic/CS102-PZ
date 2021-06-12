package com.quiz.app.controllers;

import com.quiz.app.Game;
import com.quiz.entities.Player;
import com.quiz.entities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {
    private final int QUESTION_NUM = 5;
    private Game game;
    private Player player;
    private List<Question> gameQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int currentGamePoints = 0;


    public GameController() {
    }

    public GameController(List<Question> questions) {
        buildQuestionsList(questions);
    }

    public GameController(List<Question> questions, Player player) {
        buildQuestionsList(questions);
        this.player = player;
    }

    public void initGame(List<Question> questions, Player player) {
        game = new Game(questions, player);
    }

    public void initGame(List<Question> questions) {
        game = new Game(questions);
    }

    public void buildQuestionsList(List<Question> questions) {
        Collections.shuffle(questions);
        for (int i = 0; i < QUESTION_NUM; i++) {
            gameQuestions.add(questions.get(i));
        }
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex >= QUESTION_NUM) {
            return null;
        }

        Question nextQuestion = gameQuestions.get(currentQuestionIndex);
        currentQuestionIndex++;
        return nextQuestion;
    }

    public Question getCurrentQuestion(){
        try{
            return gameQuestions.get((currentQuestionIndex -1));
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

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

    public int getCurrentGamePoints() {
        return currentGamePoints;
    }

    public void setCurrentGamePoints(int currentGamePoints) {
        this.currentGamePoints = currentGamePoints;
    }
}
