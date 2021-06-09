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
    int id;

    @Column(name = "question")
    String question;

    @OneToMany(mappedBy = "id")
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "correct_answer_idx")
    int correctAnswerIdx;

    public Question(){}

    public Question(String question, List<Answer> answers, int correctAnswerIdx) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIdx = correctAnswerIdx;
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

    public int getCorrectAnswerIdx() {
        return correctAnswerIdx;
    }

    public void setCorrectAnswerIdx(int correctAnswerIdx) {
        this.correctAnswerIdx = correctAnswerIdx;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", correctAnswerIdx=" + correctAnswerIdx +
                '}';
    }
}
