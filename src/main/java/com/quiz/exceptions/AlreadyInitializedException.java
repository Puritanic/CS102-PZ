package com.quiz.exceptions;

/**
 * Specijalizovana klasa izuzetka za ponovnu inicijalizaciju singleton klasa
 */
public class AlreadyInitializedException  extends Exception {
    public AlreadyInitializedException() {
        super("Class entity already initialized");
    }

    public AlreadyInitializedException(String message) {
        super(message);
    }

    public AlreadyInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyInitializedException(Throwable cause) {
        super(cause);
    }
}
