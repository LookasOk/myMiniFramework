package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends DefaultPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /* OTHER METHODS */
    public void getTabByName(String name) {
        driver.findElement(By.xpath("//a[contains(text(), '" + name + "')]")).click();
    }

    public boolean validateLogin() {
        return driver.findElement(By.xpath("//*[contains(text(), 'Signed in successfully.')]")).isDisplayed();
    }
}
