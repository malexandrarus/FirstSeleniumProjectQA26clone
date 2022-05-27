import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void searchProduct() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.id("search")).sendKeys("Necklace");
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        String items = driver.findElement(By.cssSelector(".category-products > .toolbar .amount strong")).getText();

        String finalString = items.replace(" Item(s)", "").trim();
        int finalNumber = Integer.parseInt(finalString);
        List<WebElement> myListOfElements = driver.findElements(By.cssSelector(".products-grid .item"));

        assertEquals(myListOfElements.size(), finalNumber);

    }

    @Test
    public void searchForANonExistingItem() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.id("search")).sendKeys("dog");
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);

        WebElement messageAfterSearch = driver.findElement(By.cssSelector(".note-msg"));
        assertEquals("Your search returns no results.", messageAfterSearch.getText());
    }

    @Test
    public void searchUsingAKeyword() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.id("search")).sendKeys("silver");
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);

        String allProductsText = driver.findElement(By.cssSelector(".products-grid.first")).getText();
        assertTrue(allProductsText.contains("SILVER DESERT NECKLACE"));

        List<WebElement> listOfProductsAfterSearch = driver.findElements(By.cssSelector(".products-grid .product-name"));

        boolean isPresent = false;
        for (WebElement element : listOfProductsAfterSearch) {
            if (element.getText().equals("SILVER DESERT NECKLACE")) {
                isPresent = true;
                break;
            }
        }
        assertTrue("Requested product was not found !", isPresent);
    }


    @After
    public void closeBrowser() {
        driver.close();
    }
}
