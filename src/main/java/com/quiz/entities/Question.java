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
    /**
     * ID pitanja, autogenerisana vrednost u bazi podataka
     */
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

    /**
     * Podrazumevani konstruktor
     */
    public Question() {
    }

    /**
     * @param question tekst pitanja
     */
    public Question(String question) {
        this.question = question;
    }

    /**
     * @param question tekst pitanja
     * @param answers lista odgovora
     */
    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    /**
     * @return id pitanja
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id pitanja
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return tekst pitanja
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question tekst pitanja
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return lista odgovora
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * @param answers lista odgovora
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * @return id tačnog odgovora
     */
    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    /**
     * @param correctAnswerId id tačnog odgovora
     */
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
