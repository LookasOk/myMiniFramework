package utils.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class apiUtils {

    public static String readFromApiKeyTxt() throws IOException {
        File file = new File("src/test/java/utils/api/apiKey.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }
}
