import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void loginWithValidCredentialsTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();

        WebElement elementAfterLogin = driver.findElement(By.cssSelector(".page-title h1"));
        String textFromElement = driver.findElement(By.cssSelector(".hello strong")).getText();
        assertTrue(elementAfterLogin.isDisplayed());
        assertEquals("Hello, Alexandra Monica Rus!", textFromElement);
    }

    @Test
    public void loginWithValidCredentialsFollowedByEnterKeyTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).sendKeys(Keys.ENTER);

        WebElement elementToBeClicked = driver.findElement(By.id("send2"));
        if (elementToBeClicked.isDisplayed() && elementToBeClicked.isEnabled()) {
            elementToBeClicked.click();
        }
        assertTrue(elementToBeClicked.isDisplayed());
    }

    @Test
    public void loginWithInvalidPasswordTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("email")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.id("pass")).sendKeys("Mai@202");
        driver.findElement(By.id("send2")).click();

        WebElement errorTextElement = driver.findElement(By.cssSelector(".error-msg span"));
        assertEquals("Invalid login or password.", errorTextElement.getText());
    }

    @Test
    public void loginWithInvalidEmail() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        String email = RandomStringUtils.randomAlphabetic(10) + "@emailcom";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys("Mai@2022");
        driver.findElement(By.id("send2")).click();

        WebElement messageInfoEmail = driver.findElement(By.id("advice-validate-email-email"));
        assertTrue(messageInfoEmail.isDisplayed());
        assertEquals("Please enter a valid email address. For example johndoe@domain.com.",messageInfoEmail.getText());


    }

    @Test
    public void loginWithoutMandatoryFields() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.id("send2")).click();

        WebElement emailErrorMessage = driver.findElement(By.id("advice-required-entry-email"));
        WebElement passwordErrorMessage = driver.findElement(By.id("advice-required-entry-pass"));

        assertEquals("This is a required field.", emailErrorMessage.getText());
        assertEquals("This is a required field.", passwordErrorMessage.getText());
    }

    @Test
    public void forgotPasswordTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("[title='Log In']")).click();
        driver.findElement(By.cssSelector("#login-form li:nth-child(3) > a")).click();
        driver.findElement(By.id("email_address")).sendKeys("drh_monica@yahoo.com");
        driver.findElement(By.cssSelector("[title~=Submit]")).click();

        WebElement messageIsDisplayed = driver.findElement(By.cssSelector(".welcome-msg"));
        assertEquals("WELCOME", messageIsDisplayed.getText());
    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
