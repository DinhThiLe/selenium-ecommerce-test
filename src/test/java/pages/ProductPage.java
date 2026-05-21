package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickProducts() {
        WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/products')]")));
        clickJS(products);
    }

    public void enterSearch(String text) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public void clickSearch() {
        WebElement submitSearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_search")));
        clickJS(submitSearch);
    }
    
    public void addFirstProductToCart() {
        WebElement firstProductAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'add-to-cart')])[1]")));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
    }

    public void clickViewCart() {
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(text(),'View Cart')]")));
        clickJS(viewCart);
    }
    
    public void clickViewProduct() {
        WebElement viewProd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[contains(@class,'nav')]/li/a[contains(@href, '/product_details')])[1]")));
        scrollToElement(viewProd);
        clickJS(viewProd);
    }
    
    public void addToCartFromDetail() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'cart')]")));
        clickJS(btn);
    }

    public boolean isSearchResultDisplayed(String productName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'" + productName + "')]"))).isDisplayed();
    }
}