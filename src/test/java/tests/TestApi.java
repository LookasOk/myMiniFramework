package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static utils.api.ApiUtils.doGetRequest;
import static utils.api.ApiUtils.readFromApiKeyTxt;
import static utils.constants.ApiConstants.*;

public class TestApi {
    private final SoftAssert softAssert = new SoftAssert();

    @Test
    public void testApi() throws IOException {
        String apiVilnius = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        String apiLondon = String.format(API_CITY_ID, LONDON_CITY_ID, readFromApiKeyTxt());
        String apiBeverlyHills = String.format(API_ZIP_CODE, BEVERLY_HILLS_ZIP_CODE, readFromApiKeyTxt());
        String badApi = String.format(API_CITY_ID, VILNIUS_CITY_ID, "12345");

        Response responseVilnius = doGetRequest(apiVilnius);
        Response responseLondon = doGetRequest(apiLondon);
        Response responseBeverlyHills = doGetRequest(apiBeverlyHills);

        double vilniusTemp = ((Number) responseVilnius.jsonPath().getMap(MAIN).get(TEMP)).doubleValue();
        double londonHumidity = ((Number) responseLondon.jsonPath().getMap(MAIN).get(HUMIDITY)).doubleValue();

        softAssert.assertTrue(vilniusTemp > 100.00, "Temp at this time of the year should be more then 10 degrees celsius");
        softAssert.assertTrue(londonHumidity > 0, "Humidity in London is always more then 0");
        softAssert.assertEquals(responseBeverlyHills.jsonPath().getString("name"), "Beverly Hills");
        softAssert.assertEquals(responseVilnius.getStatusCode(), 200, "Response code of a successful api call should be 200");
        softAssert.assertNotEquals(doGetRequest(badApi).getStatusCode(), 200, "Response code of an unsuccessful api call should not be 200");
        softAssert.assertAll();

    }
}
