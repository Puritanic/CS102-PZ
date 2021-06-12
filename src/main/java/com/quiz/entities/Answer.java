package com.quiz.entities;

import javax.persistence.*;

/**
 * Hibernate Answer klasa bazirana na answers tabeli u bazi podataka
 */
@Entity
@Table(name = "answers")
public class Answer {
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
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    public Answer(){}

    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

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
