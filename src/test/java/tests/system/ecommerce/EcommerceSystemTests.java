package tests.system.ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class EcommerceSystemTests extends BaseTest {

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
    public void SYS_12_Subscription_System() {
        WebElement emailInput = waitVisible(By.id("susbscribe_email"));
        scrollToElement(emailInput);
        emailInput.sendKeys("test@gmail.com");
        clickJS(By.id("subscribe"));
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Successfully subscribed!')]")).isDisplayed());
    }

    @Test
    public void SYS_13_Contact_Us_System() {
        clickJS(By.xpath("//a[contains(@href, '/contact_us')]"));
        waitVisible(By.name("name")).sendKeys("Le Tester");
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.id("message")).sendKeys("Test Message content");
        WebElement submitBtn = driver.findElement(By.name("submit"));
        scrollToElement(submitBtn);
        clickJS(submitBtn);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Success! Your details have been submitted successfully.')]")).isDisplayed());
    }

    @Test
    public void SYS_23_Scroll_Up() {
        WebElement footer = waitVisible(By.id("footer"));
        scrollToElement(footer);
        clickJS(By.id("scrollUp"));
        Assert.assertTrue(waitVisible(By.xpath("//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")).isDisplayed());
    }

    @Test
    public void SYS_24_Scroll_Down() {
        WebElement footer = waitVisible(By.id("footer"));
        scrollToElement(footer);
        Assert.assertTrue(footer.isDisplayed());
    }

    @Test
    public void SYS_25_Navigate_Products() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        Assert.assertTrue(driver.getCurrentUrl().contains("products"));
    }

    @Test
    public void SYS_26_Navigate_TestCases() {
        clickJS(By.xpath("//a[contains(text(), 'Test Cases')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/test_cases");
        Assert.assertTrue(driver.getCurrentUrl().contains("test_cases"));
    }

    @Test
    public void SYS_27_Navigate_API() {
        clickJS(By.xpath("//a[contains(text(), 'API Testing')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/api_list");
        Assert.assertTrue(driver.getCurrentUrl().contains("api_list"));
    }

    @Test
    public void SYS_28_Navigate_Video() {
        String originalWindow = driver.getWindowHandle();
        clickJS(By.xpath("//a[contains(text(), 'Video Tutorials')]"));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));
    }

    @Test
    public void SYS_29_Navigate_ContactUs() {
        clickJS(By.xpath("//a[contains(@href, '/contact_us')]"));
        Assert.assertTrue(driver.getCurrentUrl().contains("contact_us"));
    }
}
