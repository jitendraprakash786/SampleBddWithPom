package commonLibrary;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class apiUtility {
    JSONObject requestParams = null;
    RequestSpecification request = null;

    Response response = null;
    public void setBaseUri(){
        RestAssured.baseURI = "https://reqres.in/";
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
