package com.quiz.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "correct_answer_id")
    private String correctAnswerId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Question(){}

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

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(String correctAnswerId) {
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
