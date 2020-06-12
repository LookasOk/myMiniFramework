package tests.uiTests;

import org.testng.annotations.Test;

import java.io.IOException;

import static utils.api.ApiUtils.isCurrentApiActive;
import static utils.api.ApiUtils.writeToApiKeyTxt;
import static utils.constants.UiConstants.API_KEYS_TAB;
import static utils.testData.RandomTdGenerator.randomTd4;

public class TestGenerateApiKey extends BaseUiTest {


    @Test
    public void test() throws IOException {
        loginPage.login();
        softAssert.assertTrue(mainPage.validateLogin(), "Sign in was not successful");

        mainPage.getTabByName(API_KEYS_TAB);

        int apiKeyCount = apiKeysTab.getApiTableRowCount();
        String apiName = randomTd4();

        apiKeysTab.generateApiKey(apiName);
        softAssert.assertTrue(apiKeyCount < apiKeysTab.getApiTableRowCount(), "New API was not (created) added to the API table");

        apiKeysTab.editApiName(apiName, randomTd4());
        softAssert.assertNotEquals(apiKeysTab.getApiName(apiKeyCount + 1), apiName, "API name was'nt changed");
        softAssert.assertAll();

        //check if api saved in apiKey.txt is active and if not overwrite with the one that's been just generated (new api won't work for up to 10 minutes)
        if (!isCurrentApiActive()) writeToApiKeyTxt(apiKeysTab.getApiKey(apiKeyCount + 1));
    }


}
