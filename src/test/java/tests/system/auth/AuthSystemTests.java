package tests.system.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.Config;

public class AuthSystemTests extends BaseTest {

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
    public void SYS_01_Register_New_Account() {
        String randomEmail = "tester" + System.currentTimeMillis() + "@gmail.com";
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        
        new pages.RegisterPage(driver).registerAccount(randomEmail);
        
        Assert.assertTrue(waitVisible(By.xpath("//b[contains(text(),'Account Created')]")).isDisplayed());
    }

    @Test
    public void SYS_02_Register_Existing_Email() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Le Tester");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(Config.EMAIL);
        clickJS(By.xpath("//button[@data-qa='signup-button']"));
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(), 'Email Address already exist!')]")).isDisplayed());
    }

    @Test
    public void SYS_03_Login_System() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        Assert.assertTrue(waitVisible(By.xpath("//a[contains(text(), 'Logged in as')]")).isDisplayed());
    }

    @Test
    public void SYS_04_Login_Invalid_Password() {
      
        driver.get("https://automationexercise.com/");

        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        
        // 2. Nhập email đúng, password sai 
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpassword123");
        
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        
        WebElement errorMessage = waitVisible(By.xpath("//*[contains(text(), 'Your email or password is incorrect!')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void SYS_05_Login_Empty_Email() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Config.PASSWORD);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        String validationMsg = emailField.getAttribute("validationMessage");
        Assert.assertTrue(validationMsg != null && !validationMsg.isEmpty());
    }

    @Test
    public void SYS_06_Login_Empty_Password() {
        clickJS(By.xpath("//a[contains(@href, '/login')]"));
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Config.EMAIL);
        clickJS(By.xpath("//button[@data-qa='login-button']"));
        WebElement passField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        String validationMsg = passField.getAttribute("validationMessage");
        Assert.assertTrue(validationMsg != null && !validationMsg.isEmpty());
    }
}
