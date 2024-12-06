package stepDefinitions;

import commonLibrary.apiUtility;
import io.cucumber.java.en.When;

public class apiCommonSteps {
    apiUtility api = new apiUtility();

    @When("^I perform POST method on (.*) API$")
    public void apiProtocolsActions(String apiEndPoint) {
        System.out.println("API Test for end point: " + apiEndPoint);
        api.setBaseUri();
        api.setApiHeader();
        api.createRequestParams();
        api.performPostMethod(apiEndPoint);
        api.getStatusCode();
        api.getResponseBody();
    }
}
