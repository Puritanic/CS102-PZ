package com.quiz;

import com.quiz.util.PasswordUtils;
import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilsTest {
    @Test
    public void encryptsPassedValue() {
        String pass = "test password";
        String encryptedPass = PasswordUtils.encryptPassword(pass);
        Assert.assertNotEquals(pass, encryptedPass);
    }

    @Test
    public void canDecryptPassedValue() {
        String pass = "test password";
        String encryptedPass = PasswordUtils.encryptPassword(pass);
        Assert.assertTrue(PasswordUtils.checkPassword(pass, encryptedPass));
    }
}
