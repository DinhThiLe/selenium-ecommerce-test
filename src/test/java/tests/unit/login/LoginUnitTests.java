package tests.unit.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class LoginUnitTests {
    @Test
    public void UNIT_15_Validate_Login_Data() {
        boolean result = ValidationUtils.isValidLoginData("dinhthile1711@gmail.com", "241003Le.");
        Assert.assertTrue(result);
    }
}
