package tests.unit.register;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class RegisterUnitTests {
    @Test
    public void UNIT_16_Validate_Register_Data() {
        boolean result = ValidationUtils.isValidRegisterData("Le Tester", "dinhthile1711@gmail.com", "241003Le.");
        Assert.assertTrue(result);
    }
}
