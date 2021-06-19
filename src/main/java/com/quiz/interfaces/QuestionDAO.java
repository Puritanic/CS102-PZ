package com.quiz.interfaces;

import com.quiz.entities.Answer;
import com.quiz.entities.Question;

import java.util.List;

/**
 * Osnovni interfejs za rad sa pitanjima u bazi podataka
 */
public interface QuestionDAO {
    /**
     * @return List<Question> - Lista svih pitanja sa odgovorima
     */
    List<Question> getQuestions();

    /**
     * Metoda koja je odgovorna za proces skladištenja pitanja i povezivanja odgovarajućih polja izmedju Question i Answer tabela
     * @param q Pitanje koje želimo da sačuvamo
     * @param answers Lista odgovora za dato pitanje
     * @param answerIdx indeks tačnog odgovora u listi pitanja
     */
    void saveQuestion(Question q, List<Answer> answers, int answerIdx);
}
