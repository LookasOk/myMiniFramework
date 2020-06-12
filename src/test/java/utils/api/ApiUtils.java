package utils.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.io.*;

import static io.restassured.RestAssured.given;
import static utils.constants.ApiConstants.API_CITY_ID;
import static utils.constants.ApiConstants.VILNIUS_CITY_ID;

public class ApiUtils {

    /* TXT FILE HANDLING */
    public static void writeToApiKeyTxt(String apiKey) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/utils/testData/apiKey.txt"));
        writer.write(apiKey);
        writer.close();
    }

    public static String readFromApiKeyTxt() throws IOException {
        File file = new File("src/test/java/utils/testData/apiKey.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }


    /* API RESPONSE HANDLING */
    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    /* OTHER HELPER METHODS */

    //checks if api currently saved in apiKey.txt is active
    public static boolean isCurrentApiActive() throws IOException {
        String apiTest = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        Response responseTest = doGetRequest(apiTest);
        return responseTest.getStatusCode() == 200;
    }

    //waits for the api currently saved in apiKey.txt to activate (takes up to 10 minutes)
    public static void waitForApiToActivate() throws IOException, InterruptedException {
        while (!isCurrentApiActive()) {
            Thread.sleep(30000);
        }
    }
}
