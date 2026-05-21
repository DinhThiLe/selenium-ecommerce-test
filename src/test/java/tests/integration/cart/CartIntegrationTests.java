package tests.integration.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class CartIntegrationTests extends BaseTest {

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
    public void INT_17_Add_And_View_Cart() {
        login();
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        waitVisible(By.xpath("//*[text()='Added!']"));
        
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        Assert.assertTrue(waitVisible(By.id("cart_info_table")).isDisplayed());
    }

    @Test
    public void INT_18_Open_Product_Add_Add_Cart() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement viewProd = waitVisible(By.xpath("(//ul[contains(@class,'nav')]/li/a[contains(@href, '/product_details')])[1]"));
        scrollToElement(viewProd);
        clickJS(viewProd);
        
        clickJS(By.xpath("//button[contains(@class, 'cart')]"));
        Assert.assertTrue(waitVisible(By.id("cartModal")).isDisplayed() || waitVisible(By.xpath("//*[text()='Added!']")).isDisplayed());
    }

    @Test
   
    public void INT_19_View_UpdateCart() {
        // Số lượng muốn thêm
        String expectedQty = "3";

        // 1. Đăng nhập thành công
        login();
        removeAds();

        // [MỚI] Xóa sạch giỏ hàng trước khi test để tránh ảnh hưởng từ số lượng của những test cũ
        clickJS(By.xpath("//a[contains(text(), 'Cart')]"));
        removeAds();
        java.util.List<WebElement> deleteBtns = driver.findElements(By.className("cart_quantity_delete"));
        for (WebElement btn : deleteBtns) {
            clickJS(btn);
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf(btn));
        }

        // 2. Vào Products
        clickJS(By.xpath("//a[contains(@href, '/products')]"));

        if(driver.getCurrentUrl().contains("#google_vignette")) {
            driver.get("https://automationexercise.com/products");
        }

        removeAds();

        // 3. Chọn sản phẩm đầu tiên (sản phẩm 4)
        WebElement viewProd = waitClickable(
            By.xpath("(//a[contains(text(),'View Product')])[4]")
        );

        scrollToElement(viewProd);
        clickJS(viewProd);

        // 4. Update quantity
        WebElement qty = waitVisible(By.id("quantity"));

        qty.click();
        qty.clear();
        qty.sendKeys(expectedQty);

        // 5. Add to cart
        clickJS(By.xpath("//button[contains(@class,'cart')]"));

        waitVisible(By.xpath("//*[text()='Added!']"));

        // 6. View Cart
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));

        // 7. Lấy quantity thực tế trong cart
        WebElement quantityElement = waitVisible(
            By.xpath("//td[contains(@class,'cart_quantity')]")
        );

        String actualQty = quantityElement.getText().trim();

        // 8. Verify - Lúc này do giỏ hàng đã bị xóa sạch trước đó nên chắc chắn số lượng sẽ đúng bằng 3
        Assert.assertEquals(
            actualQty, expectedQty, 
            "Quantity does not match! Expected: " + expectedQty + " but found: " + actualQty
        );
    }
    @Test
    public void INT_21_Remove_Product_From_Cart() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        
        WebElement firstProductAdd = waitVisible(By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
        scrollToElement(firstProductAdd);
        clickJS(firstProductAdd);
        waitVisible(By.xpath("//*[text()='Added!']"));
        
        clickJS(By.xpath("//u[contains(text(),'View Cart')]"));
        clickJS(By.className("cart_quantity_delete"));
        
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Cart is empty!')]")).isDisplayed() || waitVisible(By.id("empty_cart")).isDisplayed());
    }

    @Test
    public void INT_22_Empty_Cart_Checkout() {
        clickJS(By.xpath("//a[contains(@href, '/view_cart')]"));
        Assert.assertTrue(waitVisible(By.xpath("//*[contains(text(),'Cart is empty')]")).isDisplayed() || waitVisible(By.id("empty_cart")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.linkText("Proceed To Checkout")).isEmpty() || !driver.findElement(By.linkText("Proceed To Checkout")).isDisplayed());
    }
}
