package com.quiz.app.controllers;

import com.quiz.DAO.QuestionDAOImpl;
import com.quiz.entities.Question;

import java.util.List;

/**
 * Klasa zadužena za obradu zahteva ka QuestionDAO objektu
 */
public class QuestionController {
    private final QuestionDAOImpl questionService = new QuestionDAOImpl();

    public QuestionController(){}

    /**
     * Metoda zadužena za pokretanje zahteva ka bazi podataka, kojim se učitavaju sva dostupna pitanja
     * @return List<Question> - lista pitanja
     */
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }
}
