import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class CartTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void addProductToCart() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        driver.findElement(By.cssSelector(".option-xs .swatch-label")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons span span")).click();

        WebElement messageProductAddedToCart = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Slim fit Dobby Oxford Shirt was added to your shopping cart.", messageProductAddedToCart.getText());

        boolean isElementPresent = driver.findElement(By.cssSelector(".title-buttons h1")).isDisplayed();
        assertTrue(isElementPresent);
    }

    @Test
    public void removeProductFromCartUsingEmptyCartOption() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        driver.findElement(By.cssSelector(".option-xs .swatch-label")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons span span")).click();
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        driver.findElement(By.id("empty_cart_button")).click();

        WebElement messageEmptyCart = driver.findElement(By.cssSelector(".cart-empty p:nth-child(1)"));
        assertEquals("You have no items in your shopping cart.", messageEmptyCart.getText());

        boolean isElementPresent = driver.findElement(By.cssSelector(".page-title h1")).isDisplayed();
        assertTrue(isElementPresent);

    }

    @Test
    public void editQtyFromCart() {
        addProductToCart();
        driver.findElement(By.cssSelector(".product-cart-actions [title='Edit item parameters']")).click();
        WebElement qty = driver.findElement(By.id("qty"));
        qty.clear();
        qty.sendKeys("3");
        driver.findElement(By.cssSelector(".add-to-cart-buttons span span")).click();

        WebElement messageUpdateOnCart = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Slim fit Dobby Oxford Shirt was updated in your shopping cart.", messageUpdateOnCart.getText());

        boolean isElementDisplayed = driver.findElement(By.cssSelector(".title-buttons h1")).isDisplayed();
        assertTrue(isElementDisplayed);
    }

    @Test
    public void moveToWishlistOption() {
        addProductToCart();
        driver.findElement(By.cssSelector(".product-cart-actions a[href*=fromcart]")).click();

        WebElement messageProductMovedToWishlist = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Slim fit Dobby Oxford Shirt has been moved to wishlist Wishlist", messageProductMovedToWishlist.getText());
    }

    @Test
    public void proceedToCheckoutOption() {
        addProductToCart();
        driver.findElement(By.cssSelector(".top [title='Proceed to Checkout']")).click();

        WebElement elementCheckoutIsOnThePage = driver.findElement(By.cssSelector(".page-title"));
        assertEquals("CHECKOUT", elementCheckoutIsOnThePage.getText());
    }

    @After
    public void closeBrowser() {
//        driver.close();
    }
}
