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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name="correct_answer_id")
//    private Answer correctAnswer;

    public Question(){}

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

//    public Question(String question, List<Answer> answers, Answer correctAnswer) {
//        this.question = question;
//        this.answers = answers;
//        this.correctAnswer = correctAnswer;
//    }

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

//    public Answer getCorrectAnswer() {
//        return correctAnswer;
//    }
//
//    public void setCorrectAnswer(Answer correctAnswer) {
//        this.correctAnswer = correctAnswer;
//    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
