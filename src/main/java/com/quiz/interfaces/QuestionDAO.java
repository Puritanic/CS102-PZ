package com.quiz.interfaces;

import com.quiz.entities.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getQuestions();
}
