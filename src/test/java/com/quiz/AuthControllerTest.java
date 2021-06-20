package com.quiz;


import com.quiz.app.controllers.AuthController;
import com.quiz.exceptions.AlreadyInitializedException;
import org.junit.Assert;
import org.junit.Test;

public class AuthControllerTest {
    /**
     * Provera da je AuthController singleton instanca null pre inicijalizacije
     */
    @Test
    public void nullWhenNotInitialized() {
        Assert.assertNull(AuthController.getAuthControllerInstance());
    }

    /**
     * Provera da AuthController singleton instanca nije null nakon inicijalizacije
     */
    @Test
    public void notNullWhenInitialized() throws AlreadyInitializedException {
        new AuthController();
        Assert.assertNotNull(AuthController.getAuthControllerInstance());
    }
}
