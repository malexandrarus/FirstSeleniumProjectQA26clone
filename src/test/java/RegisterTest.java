import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class RegisterTest {

    private WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }

    @Test
    public void validRegisterTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Mai@2022");
        driver.findElement(By.id("confirmation")).sendKeys("Mai@2022");
        WebElement checkbox = driver.findElement(By.cssSelector(".checkbox"));
        checkbox.click();
        driver.findElement(By.cssSelector(".buttons-set span span")).click();
        String elementAfterRegistration = driver.findElement(By.cssSelector(".success-msg span")).getText();

        assertEquals("Thank you for registering with Madison Island.", elementAfterRegistration);
    }

    @Test
    public void registerWithInvalidEmailTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "email.com";
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Mai@2022");
        driver.findElement(By.id("confirmation")).sendKeys("Mai@2022");
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement emailField = driver.findElement(By.id("email_address"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isValidInput = (Boolean)js.executeScript("return arguments[0].checkValidity();", emailField);
        String validationMessage = (String)js.executeScript("return arguments[0].validationMessage;", emailField);

        assertEquals(String.format("Please include an '@' in the email address. '%s' is missing an '@'.", email), validationMessage);
        assertFalse(isValidInput);

    }

    @Test
    public void registerWithEmailWithoutDotSighTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@emailcom";
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Mai@2022");
        driver.findElement(By.id("confirmation")).sendKeys("Mai@2022");
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement messageValidationEmail = driver.findElement(By.id("advice-validate-email-email_address"));
        assertEquals("Please enter a valid email address. For example johndoe@domain.com.", messageValidationEmail.getText());
    }

    @Test
    public void registerWithMandatoryFieldsWithoutFillingAnyDataAndClickOnRegisterTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("");
        driver.findElement(By.id("lastname")).sendKeys("");
        driver.findElement(By.id("email_address")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("confirmation")).sendKeys("");
        WebElement checkbox = driver.findElement(By.cssSelector(".checkbox"));
        checkbox.click();
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement firstNameFieldMessage = driver.findElement(By.id("advice-required-entry-firstname"));
        WebElement lastNameFieldMessage = driver.findElement(By.id("advice-required-entry-lastname"));
        WebElement emailAddressMessage = driver.findElement(By.id("advice-required-entry-email_address"));
        WebElement passwordMessage = driver.findElement(By.id("advice-required-entry-password"));
        WebElement passwordConfirmationMessage = driver.findElement(By.id("advice-required-entry-confirmation"));

        assertEquals("This is a required field.", firstNameFieldMessage.getText());
        assertEquals("This is a required field.", lastNameFieldMessage.getText());
        assertEquals("This is a required field.", emailAddressMessage.getText());
        assertEquals("This is a required field.", passwordMessage.getText());
        assertEquals("This is a required field.", passwordConfirmationMessage.getText());
    }


    @Test
    public void registerWithMandatoryFieldsByFillingWithBlankSpacesAndClickOnRegisterTest() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys(" ");
        driver.findElement(By.id("lastname")).sendKeys(" ");
        driver.findElement(By.id("email_address")).sendKeys(" ");
        driver.findElement(By.id("password")).sendKeys(" ");
        driver.findElement(By.id("confirmation")).sendKeys(" ");
        WebElement checkbox = driver.findElement(By.cssSelector(".checkbox"));
        checkbox.click();
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement firstNameFieldMessage = driver.findElement(By.id("advice-required-entry-firstname"));
        WebElement lastNameFieldMessage = driver.findElement(By.id("advice-required-entry-lastname"));
        WebElement emailAddressMessage = driver.findElement(By.id("advice-required-entry-email_address"));
        WebElement passwordMessage = driver.findElement(By.id("advice-required-entry-password"));
        WebElement passwordConfirmationMessage = driver.findElement(By.id("advice-required-entry-confirmation"));

        assertEquals("This is a required field.", firstNameFieldMessage.getText());
        assertEquals("This is a required field.", lastNameFieldMessage.getText());
        assertEquals("This is a required field.", emailAddressMessage.getText());
        assertEquals("This is a required field.", passwordMessage.getText());
        assertEquals("This is a required field.", passwordConfirmationMessage.getText());
    }

    @Test
    public void registerWithMinValueForPassword() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("mai20");
        driver.findElement(By.id("confirmation")).sendKeys("mai20");
        WebElement checkbox = driver.findElement(By.cssSelector(".checkbox"));
        checkbox.click();
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement passwordMessageAdvice = driver.findElement(By.id("advice-validate-password-password"));

        assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passwordMessageAdvice.getText());

    }

    @Test
    public void registerWithMaxValueForPassword() {
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account :nth-child(5) >a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        String email = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("mai20000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        driver.findElement(By.id("confirmation")).sendKeys("mai20000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        WebElement checkbox = driver.findElement(By.cssSelector(".checkbox"));
        checkbox.click();
        driver.findElement(By.cssSelector(".buttons-set span span")).click();

        WebElement elementAfterLogin = driver.findElement(By.cssSelector(".success-msg span"));
        assertEquals("Thank you for registering with Madison Island.", elementAfterLogin.getText());
    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
