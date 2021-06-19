package com.quiz.models;

import javafx.scene.control.TextField;

/**
 * Klasa koja nam omogućava da imamo samo integer input polja za unos,
 * sa donjom i gornjom granicom integera koji može biti unešen.
 */
public class NumericInput extends TextField {
    private int minValue = 0;
    private int maxValue = 1000;

    /**
     * Podrazumevani konstruktor
     */
    public NumericInput(){}

    /**
     * @param text tekst inputa
     * @param minValue - minimalna vrednost inputa
     * @param maxValue - maksimalna vrednost inputa
     */
    public NumericInput(String text, int minValue, int maxValue) {
        super(text);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Prepisana metoda za replaceText, ovde radimo validaciju pre nego što upišemo tekst u input
     * @param start str start index
     * @param end str end index
     * @param text str
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * Prepisana metoda za replaceSelection, gde prvo radimo validaciju pre nego što dozvolimo izmenu teksta
     * @param text str
     */
    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    /**
     * Metoda koja je odgovorna za validaciju unetog teksta. Dozvoljeni su samo celi brojevi, i to u dozvoljenom min-max opsegu.
     * @param text uneti tekst
     * @return true u slučaju da je uneti tekst prošao validaciju, false u suprotnom
     */
    private boolean validate(String text) {
         if(text.matches("[0-9]*")){
             int intValue;

             try {
                 String currText = this.getText();
                 if (currText.length() == 0){
                     intValue = Integer.parseInt(text);
                 } else {
                     intValue = Integer.parseInt(currText + text);
                 }
             } catch (NumberFormatException ex){
                 ex.printStackTrace();
                 return false;
             }

             return intValue >= minValue && intValue <= maxValue;
         }
         return false;
    }

    /**
     * @return minimalna vrednost input
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * @param minValue minimalna vrednost inputa
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * @return maksimalna vrednost inputa
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue maksimalna vrednost inputa
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}