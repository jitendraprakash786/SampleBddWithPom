package commonLibrary;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class apiUtility {
    JSONObject requestParams = null;
    RequestSpecification request = null;

    public String getDetailsFromApiTestData(String apiUrl) throws IOException {
        String retValue = null;
        String filePath = System.getProperty("user.dir") + "/src/main/java/store/API/staticData.properties";
        FileInputStream fis = new FileInputStream(filePath);
        Properties propApi = new Properties();
        propApi.load(fis);
        return propApi.getProperty(apiUrl);
    }

    Response response = null;
    public void setApiEnvironment(String apiBaseUri) throws IOException {
        RestAssured.baseURI = getDetailsFromApiTestData(apiBaseUri);
        request = RestAssured.given();
    }

    public void performPostMethod(String endPoint) {
        request.body(requestParams);
        response = request.post(endPoint);
    }

    public void getResponseBody() {
        System.out.println("Response Body: " + response.getBody().asString());
    }

    public void getStatusCode() {
        System.out.println("Status code: " + response.getStatusCode());
    }

    public void setApiHeader() {
        request.header("Content-Type", "application/json");
    }

    public void createRequestParams() {
        requestParams = new JSONObject();
        requestParams.put("name", "John"); // Adding the information as key-value pair in the JSON
        requestParams.put("job", "tester");
    }


}
