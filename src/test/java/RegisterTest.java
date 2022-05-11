import io.netty.util.internal.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {

    public void validRegisterTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        driver.findElement(By.id("email_address")).sendKeys("drh_moni@yahoo.com");
        driver.findElement(By.id("password")).sendKeys("Mai@2022");
        driver.findElement(By.id("confirmation")).sendKeys("Mai@2022");
        WebElement checkbox = driver.findElement(By.cssSelector("#form-validate > div.fieldset > ul > li.control > label"));
        checkbox.click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        String elementAfterRegistration = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > ul.messages > li > ul > li > span")).getText();
        if (elementAfterRegistration.equals("Thank you for registering with Madison Island.")) {
            System.out.println("Success!");
        } else {
            System.err.println("Fail");
        }

        driver.close();
    }

    public void registerWithInvalidEmailTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        driver.findElement(By.id("email_address")).sendKeys("monica.alexandrarusgmail.com");
        driver.findElement(By.id("password")).sendKeys("Mai@2022");
        driver.findElement(By.id("confirmation")).sendKeys("Mai@2022");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        driver.close();
    }

    public void registerWithMandatoryFieldsWithoutFillingAnyDataAndClickOnRegisterTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("");
        driver.findElement(By.id("lastname")).sendKeys("");
        driver.findElement(By.id("email_address")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("confirmation")).sendKeys("");
        WebElement checkbox = driver.findElement(By.cssSelector("#form-validate > div.fieldset > ul > li.control > label"));
        checkbox.click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        driver.close();

    }

    public void registerWithMandatoryFieldsByFillingWithBlankSpacesAndClickOnRegisterTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys(" ");
        driver.findElement(By.id("lastname")).sendKeys(" ");
        driver.findElement(By.id("email_address")).sendKeys(" ");
        driver.findElement(By.id("password")).sendKeys(" ");
        driver.findElement(By.id("confirmation")).sendKeys(" ");
        WebElement checkbox = driver.findElement(By.cssSelector("#form-validate > div.fieldset > ul > li.control > label"));
        checkbox.click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        driver.close();
    }

    public void registerWithMinValueForPassword() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        driver.findElement(By.id("email_address")).sendKeys("drhmon@yahoo.com");
        driver.findElement(By.id("password")).sendKeys("mai20");
        driver.findElement(By.id("confirmation")).sendKeys("mai20");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        driver.close();
    }

    public void registerWithMaxValueForPassword() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.id("firstname")).sendKeys("Alexandra");
        driver.findElement(By.id("middlename")).sendKeys("Monica");
        driver.findElement(By.id("lastname")).sendKeys("Rus");
        driver.findElement(By.id("email_address")).sendKeys("drhmon@yahoo.com");
        driver.findElement(By.id("password")).sendKeys("mai2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        driver.findElement(By.id("confirmation")).sendKeys("mai2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span")).click();
        driver.close();
    }
}
