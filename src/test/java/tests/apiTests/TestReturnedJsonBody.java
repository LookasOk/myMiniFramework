package tests.apiTests;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static utils.api.ApiUtils.doGetRequest;
import static utils.api.ApiUtils.readFromApiKeyTxt;
import static utils.constants.ApiConstants.API_CITY_ID;
import static utils.constants.ApiConstants.VILNIUS_CITY_ID;

public class TestReturnedJsonBody {

    @Test
    public void testJsonBodyPositiveFlow() throws IOException {
        String apiTest = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        Response responseTest = doGetRequest(apiTest);

        InputStream inSchemaCorrect = new FileInputStream("src/test/java/utils/testData/jsonSchema.json");
        JSONObject rawSchemaCorrect = new JSONObject(new JSONTokener(inSchemaCorrect));
        Schema schemaCorrect = SchemaLoader.load(rawSchemaCorrect);

        //check if returned Json body matches schema provided
        try {
            schemaCorrect.validate(new JSONObject(responseTest.body().asString()));
        } catch (Exception e) {
            throw new ValidationException("Json body did not match schema provided");
        }
    }

    @Test
    public void testJsonBodyNegativeFlow() throws IOException {
        String apiTest = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        Response responseTest = doGetRequest(apiTest);

        InputStream inSchemaCorrect = new FileInputStream("src/test/java/utils/testData/jsonSchemaNegative.json");
        JSONObject rawSchemaCorrect = new JSONObject(new JSONTokener(inSchemaCorrect));
        Schema schemaCorrect = SchemaLoader.load(rawSchemaCorrect);

        //check if returned Json body matches schema provided
        try {
            schemaCorrect.validate(new JSONObject(responseTest.body().asString()));
        } catch (Exception e) {
            throw new ValidationException("Json body did not match schema provided");
        }
    }
}
