package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickSignupLogin() {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/login')]")));
        clickJS(el);
    }

    public void enterLoginEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']"))).sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-password']"))).sendKeys(password);
    }

    public void clickLogin() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='login-button']")));
        clickJS(el);
    }

    public void enterSignupName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-name']"))).sendKeys(name);
    }

    public void enterSignupEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-email']"))).sendKeys(email);
    }

    public void clickSignup() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='signup-button']")));
        clickJS(el);
    }

    public boolean isLoginSuccess() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Logged in as')]"))).isDisplayed();
    }

    public void clickLogout() {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/logout')]")));
        clickJS(el);
    }
}