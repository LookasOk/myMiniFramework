package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends DefaultPage {


    /* TEXT FIELDS */
    @FindBy(id = "user_email")
    private WebElement enterEmailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    /* BUTTONS */
    @FindBy(xpath = "//input[@name='commit']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /* OTHER METHODS */
    public void login() {
        enterEmailField.sendKeys("lokunauskas@eisgroup.com");
        passwordField.sendKeys("password");
        buttonLogin.click();
    }


}
