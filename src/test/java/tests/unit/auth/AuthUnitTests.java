package tests.unit.auth;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class AuthUnitTests {
    @Test
    public void UNIT_01_Validate_Email() {
        boolean result = ValidationUtils.isValidEmail("test@example.com");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_02_Validate_Email_Invalid() {
        boolean result = ValidationUtils.isValidEmail("userexample.com");
        Assert.assertFalse(result);
    }
    @Test
    public void UNIT_05_Password_Strength() {
        boolean result = ValidationUtils.isStrongPassword("abc123");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_06_Password_TooShort() {
        boolean result = ValidationUtils.isStrongPassword("abc");
        Assert.assertFalse(result);
    }
    @Test
    public void UNIT_07_Validate_Password() {
        boolean result = ValidationUtils.isValidPassword("123456");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_08_Validate_Password_Invalid() {
        boolean result = ValidationUtils.isValidPassword("123");
        Assert.assertFalse(result);
    }
    @Test
    public void UNIT_09_Validate_Empty_Email() {
        boolean result = ValidationUtils.isValidEmail("");
        Assert.assertFalse(result);
    }
    @Test
    public void UNIT_17_Validate_Empty_Password() {
        boolean result = ValidationUtils.isValidPassword("");
        Assert.assertFalse(result);
    }
}
