package tests.integration.product;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class ProductIntegrationTests extends BaseTest {

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
    public void INT_11_Search_And_Add_To_Cart() {
        login();
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        productPage.enterSearch("Blue Top");
        productPage.clickSearch();
        
        productPage.addFirstProductToCart();
        
        Assert.assertTrue(waitVisible(By.id("cartModal")).isDisplayed() || waitVisible(By.xpath("//*[text()='Added!']")).isDisplayed());
    }

    @Test
    public void INT_12_Search_ViewProduct() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        productPage.clickViewProduct();
        
        Assert.assertTrue(waitVisible(By.xpath("//div[@class='product-information']")).isDisplayed());
    }

    @Test
    public void INT_13_Search_Special() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        productPage.enterSearch("@#$");
        productPage.clickSearch();
        
        Assert.assertTrue(driver.findElements(By.className("product-image-wrapper")).isEmpty() || 
            waitVisible(By.xpath("//div[@class='features_items']")).getText().contains("SEARCHED PRODUCTS"));
    }

    @Test
    public void INT_14_Search_Invalid() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        productPage.enterSearch("abcxyzmn");
        productPage.clickSearch();
        
        Assert.assertTrue(driver.findElements(By.className("product-image-wrapper")).isEmpty() || 
            !driver.findElement(By.xpath("//div[@class='features_items']")).getText().contains("Add to cart"));
    }

    @Test
    public void INT_15_Search_AddMulti() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        productPage.enterSearch("Sleeveless Dress");
        productPage.clickSearch();
        
        productPage.addFirstProductToCart();
        
        waitVisible(By.xpath("//*[text()='Added!']"));
        clickJS(By.xpath("//button[text()='Continue Shopping']"));
        
        productPage.addFirstProductToCart();
        waitVisible(By.xpath("//*[text()='Added!']"));
        
        productPage.clickViewCart();
        Assert.assertTrue(waitVisible(By.id("cart_info_table")).isDisplayed());
    }

    @Test
    public void INT_16_Search_Category_Cart() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        clickJS(By.xpath("//a[@href='#Women']"));
        clickJS(By.xpath("//*[@id='Women']//a[contains(text(),'Dress')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/category_products/1");
        
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        Assert.assertTrue(waitVisible(By.xpath("//*[text()='Added!']")).isDisplayed());
    }

    @Test
    public void INT_24_Write_Review_Product() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement viewProd = waitVisible(By.xpath("(//ul[contains(@class,'nav')]/li/a[contains(@href, '/product_details')])[1]"));
        scrollToElement(viewProd);
        clickJS(viewProd);
        
        waitVisible(By.id("name")).sendKeys("Le Tester");
        driver.findElement(By.id("email")).sendKeys("tester@gmail.com");
        driver.findElement(By.id("review")).sendKeys("Good product!");
        
        WebElement submitBtn = driver.findElement(By.id("button-review"));
        scrollToElement(submitBtn);
        clickJS(submitBtn);
        
        Assert.assertTrue(waitVisible(By.xpath("//span[contains(text(),'Thank you for your review')]")).isDisplayed());
    }
}
