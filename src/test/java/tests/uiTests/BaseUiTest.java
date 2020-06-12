package tests.uiTests;

import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MainPage;
import tabs.ApiKeysTab;
import tests.DefaultTest;

public abstract class BaseUiTest extends DefaultTest {
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ApiKeysTab apiKeysTab;

    protected SoftAssert softAssert;

    @BeforeClass
    public void preconditions() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        apiKeysTab = new ApiKeysTab(driver);
        softAssert = new SoftAssert();
    }

}
