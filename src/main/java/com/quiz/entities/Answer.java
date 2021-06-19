package com.quiz.entities;

import javax.persistence.*;

/**
 * Hibernate Answer klasa bazirana na answers tabeli u bazi podataka
 */
@Entity
@Table(name = "answers")
public class Answer {
    /**
     * ID pitanja, autogenerisana vrednost u bazi podataka
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    /**
     * Ponudjeni odgovor na pitanje, čiji je deo.
     */
    @Column(name = "answer")
    private String answer;

    /**
     * Instanca Question klase mapirana prema question_id-u. Ovo je many to one relacija
     * zato što pitanje može biti jedno ali imati više odgovora.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * Podrazumevani konstruktor
     */
    public Answer() {
    }

    /**
     * @param answer tekst odgovora
     */
    public Answer(String answer) {
        this.answer = answer;
    }

    /**
     * @param answer tekst odgovora
     * @param question instanca klase Question, sa kojom je odgovor povezan
     */
    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    /**
     * @return id odgovora
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id odgovora
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return tekst odgovora
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer tekst odgovora
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return instanca klase Question sa kojom je odgovor povezan
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question instanca klase Question sa kojom je odgovor povezan
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                '}';
    }
}
