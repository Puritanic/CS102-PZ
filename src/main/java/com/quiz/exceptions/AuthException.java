package com.quiz.exceptions;

/**
 * Spejilazovana klasa izuzetka za errore koji se dese tokom login ili registration procesa.
 */
public class AuthException extends Exception {
    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
