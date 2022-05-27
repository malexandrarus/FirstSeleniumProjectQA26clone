import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void checkoutAsGuest() {
        WebElement womenCategory = driver.findElement(By.cssSelector(".level0 > a[href*='women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenCategory).perform();

        driver.findElement(By.cssSelector(".nav-1-2 a[href*=tops-blouses]")).click();
        driver.findElement(By.id("product-collection-image-421")).click();
        driver.findElement(By.cssSelector("#swatch28 img")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons [title='Add to Cart']")).click();
        driver.findElement(By.cssSelector(".method-checkout-cart-methods-onepage-bottom [title='Proceed to Checkout']")).click();
        driver.findElement(By.id("login:guest")).isSelected();
        driver.findElement(By.id("onepage-guest-register-button")).click();
        driver.findElement(By.id("billing:firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("billing:lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        driver.findElement(By.id("billing:email")).sendKeys(email);
        driver.findElement(By.id("billing:street1")).sendKeys("Buna Ziua");
        driver.findElement(By.id("billing:city")).sendKeys("Cluj-Napoca");

        Select dropDown = new Select(driver.findElement(By.name("billing[country_id]")));
        dropDown.selectByVisibleText("România");
        Select dropDownRegion = new Select(driver.findElement(By.name("billing[region_id]")));
        dropDownRegion.selectByVisibleText("Cluj");
        driver.findElement(By.id("billing:postcode")).sendKeys("400495");
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        driver.findElement(By.id("billing:telephone")).sendKeys(String.valueOf(number));
        driver.findElement(By.id("billing:use_for_shipping_yes")).isSelected();
        driver.findElement(By.cssSelector("#billing-buttons-container [title='Continue']")).click();
        Utils.wait(6000);
        driver.findElement(By.id("s_method_freeshipping_freeshipping")).click();
        driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
        Utils.wait(3000);
        driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();
        Utils.wait(3000);
        driver.findElement(By.cssSelector("#review-buttons-container .button")).click();
        Utils.wait(3000);

        WebElement confirmationMessage = driver.findElement(By.cssSelector("h2.sub-title"));
        assertEquals("THANK YOU FOR YOUR PURCHASE!", confirmationMessage.getText());

    }

    @Test
    public void checkoutWithRegisterAndCheckoutOption() {
       checkoutData();
       driver.findElement(By.id("billing:customer_password")).sendKeys("Pass123#");
       driver.findElement(By.id("billing:confirm_password")).sendKeys("Pass123#");
       driver.findElement(By.id("billing:use_for_shipping_yes")).isSelected();
       driver.findElement(By.cssSelector("#billing-buttons-container [title='Continue']")).click();
       Utils.wait(6000);
       driver.findElement(By.id("s_method_freeshipping_freeshipping")).click();
       driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
       Utils.wait(4000);
       driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();
       Utils.wait(3000);
       driver.findElement(By.cssSelector("#review-buttons-container .button")).click();
       Utils.wait(3000);

       boolean isElementDisplayed = driver.findElement(By.cssSelector(".col-main h1")).isDisplayed();
       assertTrue("YOUR ORDER HAS BEEN RECEIVED.", isElementDisplayed);

    }

    @Test
    public void checkoutWithShipToDifferentAddressOption() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).sendKeys(Keys.ENTER);
        WebElement womenCategory = driver.findElement(By.cssSelector(".level0 > a[href*='women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenCategory).perform();

        driver.findElement(By.cssSelector(".nav-1-2 a[href*=tops-blouses]")).click();
        driver.findElement(By.id("product-collection-image-421")).click();
        driver.findElement(By.cssSelector("#swatch28 img")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons [title='Add to Cart']")).click();
        driver.findElement(By.cssSelector(".method-checkout-cart-methods-onepage-bottom [title='Proceed to Checkout']")).click();
        driver.findElement(By.id("billing:use_for_shipping_no")).isSelected();
        driver.findElement(By.cssSelector("[title='Use Billing Address']")).click();
        driver.findElement(By.cssSelector("#shipping-buttons-container .button")).click();
        Utils.wait(4000);
        driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();
        Utils.wait(3000);
        driver.findElement(By.cssSelector("#review-buttons-container .button")).click();
        Utils.wait(3000);

        boolean isElementDisplayed = driver.findElement(By.cssSelector(".col-main h1")).isDisplayed();
        assertTrue("YOUR ORDER HAS BEEN RECEIVED.", isElementDisplayed);
    }

    @After
    public void closeBrowser() {
//        driver.close();
    }

    /*
    This method was created to store data necessary for checkout
     */
    private void checkoutData() {
        WebElement womenCategory = driver.findElement(By.cssSelector(".level0 > a[href*='women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenCategory).perform();

        driver.findElement(By.cssSelector(".nav-1-2 a[href*=tops-blouses]")).click();
        driver.findElement(By.id("product-collection-image-421")).click();
        driver.findElement(By.cssSelector("#swatch28 img")).click();
        driver.findElement(By.id("swatch79")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons [title='Add to Cart']")).click();
        driver.findElement(By.cssSelector(".method-checkout-cart-methods-onepage-bottom [title='Proceed to Checkout']")).click();
        driver.findElement(By.id("login:register")).click();
        driver.findElement(By.id("onepage-guest-register-button")).click();
        driver.findElement(By.id("billing:firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("billing:lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        driver.findElement(By.id("billing:email")).sendKeys(email);
        driver.findElement(By.id("billing:street1")).sendKeys("Buna Ziua");
        driver.findElement(By.id("billing:city")).sendKeys("Cluj-Napoca");
        Select dropDown = new Select(driver.findElement(By.name("billing[country_id]")));
        dropDown.selectByVisibleText("România");
        Select dropDownRegion = new Select(driver.findElement(By.name("billing[region_id]")));
        dropDownRegion.selectByVisibleText("Cluj");
        driver.findElement(By.id("billing:postcode")).sendKeys("400495");
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        driver.findElement(By.id("billing:telephone")).sendKeys(String.valueOf(number));
    }
}
