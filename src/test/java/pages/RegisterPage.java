package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerAccount(String email) {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
        clickJS(title);
        driver.findElement(By.id("password")).sendKeys("241003Le.");
        new Select(driver.findElement(By.id("days"))).selectByValue("17");
        new Select(driver.findElement(By.id("months"))).selectByValue("11");
        new Select(driver.findElement(By.id("years"))).selectByValue("2003");
        
        driver.findElement(By.id("first_name")).sendKeys("Le");
        driver.findElement(By.id("last_name")).sendKeys("Tester");
        driver.findElement(By.id("company")).sendKeys("QA Company");
        driver.findElement(By.id("address1")).sendKeys("Ha Noi");
        new Select(driver.findElement(By.id("country"))).selectByVisibleText("India");
        driver.findElement(By.id("state")).sendKeys("Ha Noi");
        driver.findElement(By.id("city")).sendKeys("Hoan Kiem");
        driver.findElement(By.id("zipcode")).sendKeys("100000");
        driver.findElement(By.id("mobile_number")).sendKeys("0123456789");
        
        WebElement createAccountBtn = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        scrollToElement(createAccountBtn);
        clickJS(createAccountBtn);
    }
    
    public void clickContinue() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-qa='continue-button']")));
        clickJS(btn);
    }
    
    public boolean isAccountCreated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Account Created')]"))).isDisplayed();
    }
}