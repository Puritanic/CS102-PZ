package com.quiz.interfaces;

import com.quiz.entities.Question;

import java.util.List;

public interface QuestionDAO {
    /**
     * @return List<Question> - Lista svih pitanja sa odgovorima
     */
    List<Question> getQuestions();
}
