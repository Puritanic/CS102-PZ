package com.quiz.app.controllers;

import com.quiz.DAO.QuestionDAOImpl;
import com.quiz.entities.Answer;
import com.quiz.entities.Question;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa zadužena za obradu zahteva ka QuestionDAOImpl objektu
 */
public class QuestionController {
    /**
     * Question servis za komunikaciju sa bazom podataka
     */
    private final QuestionDAOImpl questionService = new QuestionDAOImpl();

    /**
     * Podrazumevani konstruktor
     */
    public QuestionController() {
    }

    /**
     * Metoda zadužena za pokretanje zahteva ka bazi podataka, kojim se učitavaju sva dostupna pitanja
     *
     * @return List<Question> - lista pitanja
     */
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    /**
     * @param question  pitanje koje želimo da uskladištimo u bazu podataka
     * @param answer1   prvi odgovor
     * @param answer2   drugi odgovor
     * @param answer3   treći odgovor
     * @param answerIdx indeks tačnog odgovora
     */
    public void saveQuestion(String question, String answer1, String answer2, String answer3, int answerIdx) {
        Question q = new Question(question);
        // Koristimo LinkedList zato što nam je bitan redosled pitanja
        List<Answer> answers = new LinkedList<>();
        answers.add(new Answer(answer1));
        answers.add(new Answer(answer2));
        answers.add(new Answer(answer3));
        questionService.saveQuestion(q, answers, answerIdx);
    }
}
