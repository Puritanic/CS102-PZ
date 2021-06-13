package com.quiz.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate Question klasa bazirana na questions tabeli u bazi podataka
 */
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    /**
     * Tekst pitanja.
     */
    @Column(name = "question")
    private String question;

    /**
     * Id tačnog odgovora u listi odgovora koja se dobija sa pitanjem.
     */
    @Column(name = "correct_answer_id")
    private int correctAnswerId;

    /**
     * Lista odgovora, mapirana prema question polju u Answer klasi.
     * One to many relacija zbog toga što jedno pitanje može imati više odgovora.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", correctAnswerId='" + correctAnswerId + '\'' +
                ", answers=" + answers +
                '}';
    }
}
