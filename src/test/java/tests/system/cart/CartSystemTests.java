package tests.system.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class CartSystemTests extends BaseTest {

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
    public void SYS_09_Add_To_Cart_System() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        Assert.assertTrue(waitVisible(By.xpath("//*[text()='Added!']")).isDisplayed());
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        Assert.assertTrue(waitVisible(By.id("cart_info_table")).isDisplayed());
    }

    @Test
    public void SYS_10_Remove_From_Cart() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        waitVisible(By.xpath("//*[text()='Added!']"));
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        clickJS(By.className("cart_quantity_delete"));
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Cart is empty!')]")).isDisplayed() || waitVisible(By.id("empty_cart")).isDisplayed());
    }

    @Test
    public void SYS_11_Update_Quantity() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        WebElement viewProd = waitVisible(By.xpath("(//ul[contains(@class,'nav')]/li/a[contains(@href, '/product_details')])[1]"));
        scrollToElement(viewProd);
        clickJS(viewProd);
        WebElement qty = waitVisible(By.id("quantity"));
        qty.clear();
        qty.sendKeys("3");
        clickJS(By.xpath("//button[contains(@class, 'cart')]"));
        waitVisible(By.xpath("//*[text()='Added!']"));
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        Assert.assertTrue(waitVisible(By.xpath("//button[text()='3']")).isDisplayed());
    }
}
