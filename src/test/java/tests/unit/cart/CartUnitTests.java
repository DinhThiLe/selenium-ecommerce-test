package tests.unit.cart;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class CartUnitTests {
    @Test
    public void UNIT_03_Calculate_Total_Cart() {
        int[] prices = {100, 200};
        int[] qty = {2, 1};
        int total = ValidationUtils.calculateTotal(prices, qty);
        Assert.assertEquals(total, 400);
    }
    @Test
    public void UNIT_04_Empty_Cart_Total() {
        int[] prices = {};
        int[] qty = {};
        int total = ValidationUtils.calculateTotal(prices, qty);
        Assert.assertEquals(total, 0);
    }
    @Test
    public void UNIT_12_Calculate_Cart_Quantity() {
        int[] qty = {1, 2, 3};
        int result = ValidationUtils.calculateCartQuantity(qty);
        Assert.assertEquals(result, 6);
    }
    @Test
    public void UNIT_19_Validate_Cart_Not_Empty() {
        boolean result = ValidationUtils.isCartNotEmpty(5);
        Assert.assertTrue(result);
    }
}
