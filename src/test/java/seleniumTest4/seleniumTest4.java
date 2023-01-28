package seleniumTest4;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class seleniumTest4 {
    private static WebDriver driver;


    @BeforeAll
    public static void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--headless");
//
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLogin()  {
        WebElement logIn = driver.findElement(By.id("login2"));
        logIn.click();

        WebElement username = driver.findElement(By.id("loginusername"));
        username.sendKeys("username");
        WebElement password = driver.findElement(By.id("loginpassword"));
        password.sendKeys("password");
        password.submit();

        WebElement nameofuser = driver.findElement(By.id("nameofuser"));
        assertNotNull(nameofuser);
    }

    @Test
    public void testLogOut()  {
        WebElement logIn = driver.findElement(By.id("login2"));
        logIn.click();

        WebElement username = driver.findElement(By.id("loginusername"));
        username.sendKeys("username");
        WebElement password = driver.findElement(By.id("loginpassword"));
        password.sendKeys("password");
        WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
        loginbtn.click();

        WebElement logout = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
        logout.click();

        WebElement loginButton = driver.findElement(By.id("login2"));
        String loginButtonText = loginButton.getText();
        assertEquals("Log in", loginButtonText);
    }

    @Test
    public void testIncorrectLogin()  {
        WebElement logIn = driver.findElement(By.id("login2"));
        logIn.click();

        WebElement username = driver.findElement(By.id("loginusername"));
        username.sendKeys("ere");
        WebElement password = driver.findElement(By.id("loginpassword"));
        password.sendKeys("ere123!");
        WebElement loginbtn = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
        loginbtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();

        assertNotNull("Wrong password.", alertText );
    }
}
