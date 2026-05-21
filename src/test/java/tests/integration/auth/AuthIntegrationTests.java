package tests.integration.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.Config;

public class AuthIntegrationTests extends BaseTest {

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
    public void INT_01_Register_New_Account() {
        String randomEmail = "tester" + System.currentTimeMillis() + "@gmail.com";
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        
        new pages.RegisterPage(driver).registerAccount(randomEmail);
        
        Assert.assertTrue(waitVisible(By.xpath("//b[contains(text(),'Account Created!')]")).isDisplayed());
    }

    @Test
    public void INT_02_Register_Logout() {
        String randomEmail = "tester" + System.currentTimeMillis() + "@gmail.com";
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        
        new pages.RegisterPage(driver).registerAccount(randomEmail);
        waitVisible(By.xpath("//b[contains(text(),'Account Created!')]"));
        
        clickJS(By.xpath("//a[@data-qa='continue-button']"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/");
        
        clickJS(By.xpath("//a[contains(@href, '/logout')]"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void INT_03_Register_Delete() {
        String randomEmail = "tester" + System.currentTimeMillis() + "@gmail.com";
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        
        new pages.RegisterPage(driver).registerAccount(randomEmail);
        waitVisible(By.xpath("//b[contains(text(),'Account Created!')]"));
        
        clickJS(By.xpath("//a[@data-qa='continue-button']"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/");
        
        clickJS(By.xpath("//a[contains(@href, '/delete_account')]"));
        Assert.assertTrue(waitVisible(By.xpath("//b[contains(text(),'Account Deleted!')]")).isDisplayed());
    }

    @Test
    public void INT_04_Register_Missing() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("testergmail.com"); 
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        String validationMessage = emailField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage != null && !validationMessage.isEmpty());
    }

    @Test
    public void INT_05_Login_Logout() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        clickJS(By.xpath("//a[contains(@href, '/logout')]"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void INT_06_Login_Search() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        waitVisible(By.id("search_product")).sendKeys("Men Tshirt");
        clickJS(By.id("submit_search"));
        
        WebElement productEl = waitVisible(By.xpath("//p[contains(text(),'Men Tshirt')]"));
        Assert.assertTrue(productEl.isDisplayed());
    }

    @Test
    public void INT_07_Login_AddCart() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        
        Assert.assertTrue(waitVisible(By.xpath("//*[text()='Added!']")).isDisplayed());
    }

    @Test
    public void INT_08_Login_Retry() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpass");
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        waitVisible(By.xpath("//*[contains(text(), 'incorrect email or password')]"));
        
        WebElement emailInput = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailInput.clear();
        emailInput.sendKeys(Config.EMAIL);
        WebElement passInput = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        passInput.clear();
        passInput.sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        Assert.assertTrue(waitVisible(By.xpath("//a[contains(text(), 'Logged in as')]")).isDisplayed());
    }

    @Test
    public void INT_09_Login_Product() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        Assert.assertTrue(waitVisible(By.xpath("//h2[contains(text(),'All Products')]")).isDisplayed());
    }

    @Test
    public void INT_10_Login_Contact() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        clickJS(By.xpath("//a[contains(@href, '/contact_us')]"));
        
        waitVisible(By.name("name")).sendKeys("Le Tester");
        driver.findElement(By.name("email")).sendKeys(Config.EMAIL);
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.id("message")).sendKeys("Test Message content");
        
        WebElement submitBtn = driver.findElement(By.name("submit"));
        scrollToElement(submitBtn);
        clickJS(submitBtn);
        
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Success! Your details have been submitted successfully.')]")).isDisplayed());
    }
}
