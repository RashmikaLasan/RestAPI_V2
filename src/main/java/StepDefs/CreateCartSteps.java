package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static StepDefs.SeacrhSteps.GetRequestBody;
import static StepDefs.SeacrhSteps.KeyControls;
import static io.restassured.RestAssured.given;

public class CreateCartSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String CartID;
    public static String TravellersKey1;
    public static String TravellersKey2;
    public String CreateCartURLPara = EndPoints.CreateCartURLPara;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;



    public CreateCartSteps() {


        Then("^I want to createCart$", () -> {
            requestSpecification = given();
        });


        Then("^I Create Cart by adding the body$", () -> {
            JsonObject createCartBody = new JsonObject();
            createCartBody.add("keyControls",KeyControls);
            createCartBody.add("payload",GetRequestBody);

            response = requestSpecification.
                    contentType("application/json").
                    body(createCartBody.toString()).
                    when().post(BaseEnvironmet+CreateCartURLPara);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            response.prettyPrint();

        });
        And("^I store the Cart ID$", () -> {

            CartID = response.jsonPath().getString("data[0].summary.id");
            System.out.println("Add to Cart, CartID is: "+ CartID);

        });

        And("^I store the TravellersKey of Passenger One$", () -> {

            TravellersKey1 = response.jsonPath().getString("data[0].travellers[0].key");
            System.out.println("Travellers, Key one is: "+ TravellersKey1);

        });

        And("^I store the TravellersKey of Passenger Two$", () -> {

            TravellersKey2 = response.jsonPath().getString("data[0].travellers[0].key");
            System.out.println("Travellers, Key one is: "+ TravellersKey2);

        });


    }
}
