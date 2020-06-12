package tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

public class ApiKeysTab extends DefaultPage {

    /* TEXT FIELDS */
    @FindBy(id = "api_key_form_name")
    private WebElement apiKeyNameField;

    @FindBy(id = "edit_key_form_name")
    private WebElement popUpNameChangeField;


    /* BUTTONS */
    @FindBy(xpath = "//input[@name='commit']")
    private WebElement buttonGenerate;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement buttonPopUpSave;


    public ApiKeysTab(WebDriver driver) {
        super(driver);
    }

    /* HELPER METHODS */
    public void generateApiKey(String keyName) {
        apiKeyNameField.sendKeys(keyName);
        buttonGenerate.click();
    }

    public int getApiTableRowCount() {
        return driver.findElements(By.xpath("//table[@class='material_table api-keys']/tbody/tr")).size();
    }

    public void editApiName(String currentName, String newName) {
        driver.findElement(By.xpath("//td[contains(text(),'" + currentName + "')]/..//td[3]//a")).click();
        defaultWait(buttonPopUpSave);
        popUpNameChangeField.clear();
        popUpNameChangeField.sendKeys(newName);
        buttonPopUpSave.click();
    }

    public String getApiName(int rowNumber) {
        return driver.findElement(By.xpath("//table[@class='material_table api-keys']/tbody/tr[" + rowNumber + "]/td[2]")).getText();
    }

    public String getApiKey(int rowNumber) {
        return driver.findElement(By.xpath("//table[@class='material_table api-keys']/tbody/tr[" + rowNumber + "]/td[1]")).getText();
    }


}
