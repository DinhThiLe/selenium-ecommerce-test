package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void enterPaymentDetails(String name, String number, String cvcCode, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card"))).sendKeys(name);
        driver.findElement(By.name("card_number")).sendKeys(number);
        driver.findElement(By.name("cvc")).sendKeys(cvcCode);
        driver.findElement(By.name("expiry_month")).sendKeys(month);
        driver.findElement(By.name("expiry_year")).sendKeys(year);
    }

    public void clickPayAndConfirm() {
        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
        scrollToElement(submitBtn);
        clickJS(submitBtn);
    }
}
