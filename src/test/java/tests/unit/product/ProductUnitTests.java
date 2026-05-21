package tests.unit.product;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ValidationUtils;

public class ProductUnitTests {
    @Test
    public void UNIT_10_Validate_Search_Keyword() {
        boolean result = ValidationUtils.isValidSearchKeyword("Blue Top");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_11_Calculate_Discount() {
        double result = ValidationUtils.calculateDiscount(1000, 10);
        Assert.assertEquals(result, 900.0);
    }
    @Test
    public void UNIT_13_Check_Product_Name() {
        boolean result = ValidationUtils.isValidProductName("Blue Top");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_14_Check_Product_Price() {
        double result = ValidationUtils.getProductPrice(500);
        Assert.assertEquals(result, 500.0);
    }
    @Test
    public void UNIT_20_Validate_Product_Image() {
        boolean result = ValidationUtils.isValidProductImage("product1.png");
        Assert.assertTrue(result);
    }
    @Test
    public void UNIT_21_Validate_Popup_Message() {
        boolean result = ValidationUtils.isValidPopupMessage("Added!", "Added!");
        Assert.assertTrue(result);
    }
}
