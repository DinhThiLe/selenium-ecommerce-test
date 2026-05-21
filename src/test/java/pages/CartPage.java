package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeProduct() {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("cart_quantity_delete")));
        clickJS(removeButton);
    }

    public boolean isCartEmpty() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cart is empty!')] | //*[@id='empty_cart']"))).isDisplayed();
    }

    public void clickProceedToCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed To Checkout")));
        clickJS(checkoutBtn);
    }
}