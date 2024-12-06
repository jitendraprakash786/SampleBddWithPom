package stepDefinitions;

import commonLibrary.apiUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.HashMap;

public class apiCommonSteps {
    apiUtility api = new apiUtility();

    @When("^I perform POST method on (.*) API$")
    public void apiProtocolsActions(String apiEndPoint) throws IOException {
        System.out.println("API Test for end point: " + apiEndPoint);
        api.setApiEnvironment("ApiBaseUri");
        api.setApiHeader();
        api.createRequestParams();
        api.performPostMethod(apiEndPoint);
        api.getStatusCode();
        api.getResponseBody();
    }

    @And("^I setup the environment for (.*) API$")
    public void setupEnvironment(String apiName) throws IOException {
        api.setApiEnvironment(apiName);
    }

    @And("^I add the parameter values for (.*) API$")
    public void addApiParameters(HashMap<String, String> apiParams) {
        api.createRequestParams();
    }

    @And("^I perform the (.*) method for the (.*) API$")
    public void performApiMethod(String method, String endPoint) {
        api.performPostMethod(endPoint);
    }

    @Then("^I verify the status code as (.*) for the (.*) API$")
    public void validateResponseCode(String statusCode, String apiName) {

    }

    @Then("^I verify the response body of (.*) API$")
    public void validateTheResponseBody(String apiName) {

    }
}
