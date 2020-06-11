package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class DefaultTest {
    protected static WebDriver driver;

    @BeforeClass
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://home.openweathermap.org/users/sign_in");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}
