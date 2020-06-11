package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static utils.api.apiUtils.doGetRequest;
import static utils.api.apiUtils.readFromApiKeyTxt;

public class TestApi {
    private final SoftAssert softAssert = new SoftAssert();

    //TODO add enum constants
    @Test
    public void testApi() throws IOException {
        String vilniusCityID = "593116";
        String londonCityID = "1006984";
        String apiKey = readFromApiKeyTxt();
        String apiVilnius = "http://api.openweathermap.org/data/2.5/weather?id=" + vilniusCityID + "&appid=" + apiKey;
        String apiLondon = "http://api.openweathermap.org/data/2.5/weather?id=" + londonCityID + "&appid=" + apiKey;
        String apiBeverlyHills = "http://api.openweathermap.org/data/2.5/weather?zip=90210,us&appid=" + apiKey;
        String badApi = "http://api.openweathermap.org/data/2.5/weather?zip=90210,us&appid=" + "123456";

        Response responseVilnius = doGetRequest(apiVilnius);
        Response responseLondon = doGetRequest(apiLondon);
        Response responseBeverlyHills = doGetRequest(apiBeverlyHills);

        double vilniusTemp = ((Number) responseVilnius.jsonPath().getMap("main").get("temp")).doubleValue();
        double londonHumidity = ((Number) responseLondon.jsonPath().getMap("main").get("humidity")).doubleValue();

        softAssert.assertTrue(vilniusTemp > 100, "Temp at this time of the year should be more then 10 degrees celsius");
        softAssert.assertTrue(londonHumidity > 0, "Humidity in London is always more then 0");
        softAssert.assertEquals(responseBeverlyHills.jsonPath().getString("name"), "Beverly Hills");
        softAssert.assertEquals(responseVilnius.getStatusCode(), 200, "Response code of successful api call should be 200");
        softAssert.assertNotEquals(doGetRequest(badApi), 200, "Response code with bad api key should not be 200");
        softAssert.assertAll();

    }
}
