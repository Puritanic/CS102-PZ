package com.quiz.models;

import javafx.scene.control.TextField;

/**
 * Klasa koja nam omogućava da imamo samo integer input polja za unos,
 * sa donjom i gornjom granicom integera koji može biti unešen.
 */
public class NumericInput extends TextField {
    private int minValue = 0;
    private int maxValue = 1000;

    public NumericInput(){}

    public NumericInput(String text, int minValue, int maxValue) {
        super(text);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

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

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}