package tests.apiTests;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utils.api.ApiUtils.doGetRequest;
import static utils.api.ApiUtils.readFromApiKeyTxt;
import static utils.constants.ApiConstants.API_CITY_ID;
import static utils.constants.ApiConstants.VILNIUS_CITY_ID;

public class TestReturnedJsonBody {

    @Test
    public void testJsonReturnBodyCorrectSchema() throws IOException {
        String apiTest = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        Response responseTest = doGetRequest(apiTest);

        InputStream inSchemaCorrect = new FileInputStream("src/test/java/utils/testData/jsonSchema.json");
        JSONObject rawSchemaCorrect = new JSONObject(new JSONTokener(inSchemaCorrect));
        Schema schemaCorrect = SchemaLoader.load(rawSchemaCorrect);

        //check if returned Json body matches schema provided
        assertTrue(doesBodyMatchSchema(schemaCorrect, new JSONObject(responseTest.body().asString())),
                "Json body should match schema provided");

    }

    @Test
    public void testJsonReturnBodyBadSchema() throws IOException {
        String apiTest = String.format(API_CITY_ID, VILNIUS_CITY_ID, readFromApiKeyTxt());
        Response responseTest = doGetRequest(apiTest);

        InputStream inSchemaIncorrect = new FileInputStream("src/test/java/utils/testData/jsonSchemaNegative.json");
        JSONObject rawSchemaIncorrect = new JSONObject(new JSONTokener(inSchemaIncorrect));
        Schema schemaIncorrect = SchemaLoader.load(rawSchemaIncorrect);

        //check if returned Json body matches schema provided
        assertFalse(doesBodyMatchSchema(schemaIncorrect, new JSONObject(responseTest.body().asString())),
                "Json body should not match schema provided");

    }

    private boolean doesBodyMatchSchema(Schema schema, JSONObject object) {
        try {
            schema.validate(object);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
