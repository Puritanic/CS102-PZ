package com.quiz;


import com.quiz.app.controllers.AuthController;
import org.junit.Assert;
import org.junit.Test;

public class AuthControllerTest {
    @Test
    public void nullWhenNotInitialized(){
        Assert.assertNull(AuthController.getAuthControllerInstance());
    }

    @Test
    public void notNullWhenInitialized(){
        new AuthController();
        Assert.assertNotNull(AuthController.getAuthControllerInstance());
    }
}
