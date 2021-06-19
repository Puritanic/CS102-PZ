package com.quiz.models;

import javafx.scene.control.Button;

/**
 * Jednostavni wrapper za Button klasu, sa dodatkom answerId polja
 */
public class AnswerButton extends Button {
    /**
     * id pitanja vezanog za instancu AnswerButton klase
     */
    private int answerId;

    /**
     * Podrazumevani konstruktor
     */
    public AnswerButton() {
        super();
    }

    /**
     * @param answerId id pitanja
     */
    public AnswerButton(int answerId) {
        super();
        this.answerId = answerId;
    }

    /**
     * @param label tekst koji prikazujemo u okviru dugmeta
     * @param answerId id pitanja
     */
    public AnswerButton(String label, int answerId) {
        super(label);
        this.answerId = answerId;
    }

    /**
     * @return id pitanja
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId id pitanja
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
