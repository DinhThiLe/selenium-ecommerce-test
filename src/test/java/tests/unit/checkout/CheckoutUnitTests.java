package tests.unit.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class CheckoutUnitTests {
    @Test
    public void UNIT_18_Validate_Checkout_Data() {
        boolean result = ValidationUtils.isValidCheckoutData("Le Tester", "Ha Noi", "Hoan Kiem", "100000");
        Assert.assertTrue(result);
    }
}
