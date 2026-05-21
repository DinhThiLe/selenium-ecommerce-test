package tests.system.product;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class ProductSystemTests extends BaseTest {

    private void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void clickJS(By by) {
        clickJS(waitVisible(by));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", element);
    }

    private void verifyCategory(String mainCategory, String subCategory) {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        clickJS(By.xpath("//a[@href='#" + mainCategory + "']"));
        clickJS(By.xpath("//*[@id='" + mainCategory + "']//a[contains(text(),'" + subCategory + "')]"));
        Assert.assertTrue(waitVisible(By.xpath("//h2[contains(@class,'title')]")).isDisplayed());
    }

    @Test
    public void SYS_07_Search_Product_System() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        productPage.enterSearch("Blue Top");
        productPage.clickSearch();
        Assert.assertTrue(productPage.isSearchResultDisplayed("Blue Top"));
    }

    @Test
    public void SYS_08_Search_Invalid() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        productPage.enterSearch("abcxyz");
        productPage.clickSearch();
        Assert.assertTrue(driver.findElements(By.className("product-image-wrapper")).isEmpty() || 
            !driver.findElement(By.xpath("//div[@class='features_items']")).getText().contains("Add to cart"));
    }

    @Test
    public void SYS_14_View_Product_Detail_System() {
        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.clickProducts();
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        productPage.clickViewProduct();
        Assert.assertTrue(waitVisible(By.xpath("//div[@class='product-information']")).isDisplayed());
    }

    @Test
    public void SYS_15_Category_Women_Dress() {
        verifyCategory("Women", "Dress");
    }

    @Test
    public void SYS_16_Category_Women_Tops() {
        verifyCategory("Women", "Tops");
    }

    @Test
    public void SYS_17_Category_Women_Saree() {
        verifyCategory("Women", "Saree");
    }

    @Test
    public void SYS_18_Category_Men_Tshirts() {
        verifyCategory("Men", "Tshirts");
    }

    @Test
    public void SYS_19_Category_Men_Jeans() {
        verifyCategory("Men", "Jeans");
    }

    @Test
    public void SYS_20_Category_Kids_Dress() {
        verifyCategory("Kids", "Dress");
    }

    @Test
    public void SYS_21_Category_Kids_Tops_Shirts() {
        verifyCategory("Kids", "Tops & Shirts");
    }

    @Test
    public void SYS_22_Brands() {
        clickJS(By.xpath("//a[contains(@href, '/products')]"));
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/products");
        WebElement brandPolo = waitVisible(By.xpath("//a[contains(@href, '/brand_products/Polo')]"));
        scrollToElement(brandPolo);
        clickJS(brandPolo);
        if(driver.getCurrentUrl().contains("#google_vignette")) driver.get("https://automationexercise.com/brand_products/Polo");
        Assert.assertTrue(waitVisible(By.xpath("//h2[contains(@class,'title')]")).isDisplayed());
    }
}
