package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MainPage;
import tabs.ApiKeysTab;

import java.io.IOException;

import static utils.testData.RandomTdGenerator.randomTd4;

public class TestGenerateApiKey extends DefaultTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private ApiKeysTab apiKeysTab;
    private SoftAssert softAssert;
//TODO initialize variables in context interface
    @BeforeClass
    public void preconditions() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        apiKeysTab = new ApiKeysTab(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void test() throws IOException {
        loginPage.login();
        softAssert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Signed in successfully.')]")).isDisplayed(),
                "Sign in was not successful");

        mainPage.getTabByName("API keys");

        int apiKeyCount = apiKeysTab.getApiTableRowCount();
        String apiName = randomTd4();

        apiKeysTab.generateApiKey(apiName);
        softAssert.assertTrue(apiKeyCount < apiKeysTab.getApiTableRowCount(), "New API was not (created) added to the API table");

        apiKeysTab.editApiName(apiName, randomTd4());
        softAssert.assertNotEquals(apiKeysTab.getApiName(apiKeyCount + 1), apiName, "API name was'nt changed");
        softAssert.assertAll();

        apiKeysTab.writeToApiKeyTxt(apiKeysTab.getApiKey(apiKeyCount + 1));
    }


}
