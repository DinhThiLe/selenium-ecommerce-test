package tests.integration.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class CheckoutIntegrationTests extends BaseTest {

    private void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void clickJS(By by) {
        clickJS(waitVisible(by));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", element);
    }

    @Test
    public void INT_20_Checkout_NoLogin() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        waitVisible(By.xpath("//*[text()='Added!']"));
        
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        clickJS(By.linkText("Proceed To Checkout"));
        
        Assert.assertTrue(waitVisible(By.xpath("//u[contains(text(),'Register / Login')]")).isDisplayed());
    }

    @Test
    public void INT_23_Login_Checkout_Payment() {
        // 1. Đăng nhập thành công
        login();
        removeAds();

        // Vào Products
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        removeAds();

        // 2. Nhấn Add to cart
        WebElement firstProductAdd = waitClickable(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        waitVisible(By.xpath("//*[text()='Added!']"));

        // 3. View cart
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));

        // 4. Click Proceed To Checkout
        clickJS(By.linkText("Proceed To Checkout"));
        removeAds();

        // 5. Nhấn Place Order
        WebElement placeOrder = waitClickable(By.linkText("Place Order"));
        scrollToElement(placeOrder);
        clickJS(placeOrder);

        // 6. Nhập thông tin thanh toán
        waitVisible(By.name("name_on_card")).sendKeys("Le Tester");
        driver.findElement(By.name("card_number")).sendKeys("4242424242424242");
        driver.findElement(By.name("cvc")).sendKeys("123");
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        driver.findElement(By.name("expiry_year")).sendKeys("2028");

        // 7. Nhấn Pay and Confirm Order
        WebElement submitBtn = waitClickable(By.id("submit"));
        scrollToElement(submitBtn);
        clickJS(submitBtn);

        // Kiểm tra Order Placed!
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Order Placed')]")).isDisplayed());
    }
}
