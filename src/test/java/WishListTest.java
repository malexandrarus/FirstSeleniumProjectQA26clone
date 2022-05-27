import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class WishListTest {

    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void addProductToWishListTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.className("link-wishlist")).click();
        WebElement elementAfterProdWasAdded = driver.findElement(By.cssSelector(".page-title h1"));
        assertEquals("MY WISHLIST", elementAfterProdWasAdded.getText());

        WebElement wishListElement = driver.findElement(By.cssSelector(".my-wishlist .success-msg"));
        assertEquals("Slim fit Dobby Oxford Shirt has been added to your wishlist. Click here to continue shopping.", wishListElement.getText());
    }

    @Test
    public void removeProductFromWishlistAfterItWasAddedTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.className("link-wishlist")).click();
        driver.findElement(By.className("btn-remove2")).click();
        driver.switchTo().alert().accept();

        WebElement messageOnWishListPage = driver.findElement(By.cssSelector(".page-title h1"));
        assertEquals("MY WISHLIST", messageOnWishListPage.getText());

        boolean isWishlistEmpty = driver.findElement(By.cssSelector(".wishlist-empty")).isDisplayed();
        Assert.assertTrue(isWishlistEmpty);
    }

    @Test
    public void addProductFromWishlistToCartTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        driver.findElement(By.cssSelector(".option-xs .swatch-label ")).click();
        driver.findElement(By.cssSelector(".link-wishlist")).click();
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector(".links a[href*=wishlist]")).click();
        driver.findElement(By.cssSelector(".cart-cell .button")).click();

        WebElement messageAddToCart = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Slim fit Dobby Oxford Shirt was added to your shopping cart.", messageAddToCart.getText());

        boolean elementAddToCartPageIsDisplayed = driver.findElement(By.cssSelector(".page-title h1")).isDisplayed();
        Assert.assertTrue(elementAddToCartPageIsDisplayed);
    }

    @Test
    public void shareWishlistTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        driver.findElement(By.cssSelector(".option-xs .swatch-label")).click();
        driver.findElement(By.cssSelector(".link-wishlist")).click();
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector(".links a[href*=wishlist]")).click();
        driver.findElement(By.cssSelector(".btn-share")).click();
        driver.findElement(By.id("email_address")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("message")).sendKeys("This is my Wishlist");
        driver.findElement(By.cssSelector("[title= 'Share Wishlist'] span span")).click();

        WebElement messageSharedWishlist = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Your Wishlist has been shared.", messageSharedWishlist.getText());

        boolean elementSharedWishlistPageIsDisplayed = driver.findElement(By.cssSelector(".page-title h1")).isDisplayed();
        Assert.assertTrue(elementSharedWishlistPageIsDisplayed);

    }

    @Test
    public void modifyQuantityForAProductThatWasAddedToWishlist() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector("a[href*='sale.html'].level0")).click();
        driver.findElement(By.cssSelector("a[href*='.html'].button")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        driver.findElement(By.cssSelector(".option-xs .swatch-label")).click();
        driver.findElement(By.cssSelector("a.link-wishlist")).click();
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector(".links a[href*=wishlist]")).click();
        WebElement qty = driver.findElement(By.cssSelector(".validate-not-negative-number "));

        qty.clear();
        qty.sendKeys("3");
        driver.findElement(By.cssSelector(".buttons-set2 [title='Update Wishlist']")).click();

        // Verify that the quantity was updated after the user changed it
        qty = driver.findElement(By.cssSelector(".validate-not-negative-number"));
        assertEquals("3", qty.getAttribute("value"));


    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
