package com.quiz;


import com.quiz.app.controllers.AuthController;
import com.quiz.exceptions.AlreadyInitializedException;
import org.junit.Assert;
import org.junit.Test;

public class AuthControllerTest {
    @Test
    public void nullWhenNotInitialized() {
        Assert.assertNull(AuthController.getAuthControllerInstance());
    }

    @Test
    public void notNullWhenInitialized() throws AlreadyInitializedException {
        new AuthController();
        Assert.assertNotNull(AuthController.getAuthControllerInstance());
    }
}
