package com.quiz.app.controllers;

import com.quiz.DAO.QuestionDAOImpl;
import com.quiz.entities.Question;

import java.util.List;

public class QuestionController {
    private final QuestionDAOImpl questionService = new QuestionDAOImpl();

    public QuestionController(){}

    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }
}
