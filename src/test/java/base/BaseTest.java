package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utils.Config;

public class BaseTest {

    public WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null && System.getenv("CI").equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationexercise.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    protected WebElement waitVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void removeAds() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "var ads = document.getElementsByClassName('adsbygoogle');" +
                "for(var i=0;i<ads.length;i++){ ads[i].remove(); }" +
                "var ins = document.getElementsByTagName('ins');" +
                "for(var j=0;j<ins.length;j++){ ins[j].remove(); }" +
                "var fixed = document.querySelectorAll('[style*=\"fixed\"], [style*=\"absolute\"]');" +
                "for(var k=0;k<fixed.length;k++){ " +
                "  if(fixed[k].id.indexOf('aswift') !== -1 || fixed[k].className.indexOf('ad') !== -1) { fixed[k].remove(); }" +
                "}" +
                "var iframes = document.getElementsByTagName('iframe');" +
                "for(var i=0; i<iframes.length; i++) {" +
                "  if(iframes[i].id.indexOf('aswift') !== -1 || iframes[i].name.indexOf('aswift') !== -1) {" +
                "    iframes[i].remove();" +
                "  }" +
                "}"
            );
        } catch (Exception e) {
            System.out.println("Cannot remove ads");
        }
    }

    protected void login() {
        LoginPage login = new LoginPage(driver);
        removeAds();
        login.clickSignupLogin();
        waitVisible(By.xpath("//input[@data-qa='login-email']"));
        removeAds();
        login.enterLoginEmail(Config.EMAIL);
        login.enterLoginPassword(Config.PASSWORD);
        login.clickLogin();
        waitVisible(By.xpath("//*[contains(text(),'Logged in as')]"));
    }
}