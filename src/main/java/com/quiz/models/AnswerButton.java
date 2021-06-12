package com.quiz.models;


import javafx.scene.control.Button;

public class AnswerButton extends Button {
    private int answerId;

    public AnswerButton() {
        super();
    }

    public AnswerButton(int answerId) {
        super();
        this.answerId = answerId;
    }

    public AnswerButton(String label, int answerId) {
        super(label);
        this.answerId = answerId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
