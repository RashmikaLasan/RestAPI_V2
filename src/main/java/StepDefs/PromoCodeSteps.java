package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonPrimitive;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static StepDefs.CreateCartSteps.CartID;
import static io.restassured.RestAssured.given;

public class PromoCodeSteps implements En {
    public static RequestSpecification requestSpecification;
    public static Response response;
    public static JsonObject createPromoBody;
    public String PromoPara2 = EndPoints.PromoPara2;
    public String PromoPara1 = EndPoints.PromoPara1;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;

    public PromoCodeSteps() {

        When("^I want to add Promo Code$", () -> {

            requestSpecification = given();

        });

        Then("^I Create Promo Code Body with promo code as \"([^\"]*)\"$", (String PromoCode) -> {

            JsonArray payloadPromo = new JsonArray();
            JsonPrimitive element = new JsonPrimitive(PromoCode);
            payloadPromo.add(element);

            createPromoBody = new JsonObject();
            createPromoBody.add("keyControls",SeacrhSteps.KeyControls);
            createPromoBody.add("payload",payloadPromo);
            System.out.println("Promo Codes Request URL is: "+BaseEnvironmet+ PromoPara1 +CartID +PromoPara2);
        });


        Then("^I send the request in Promo Code$", () -> {
            response = requestSpecification.
                    contentType("application/json").
                    body(createPromoBody.toString()).
                    when().post(BaseEnvironmet+ PromoPara1 +CartID +PromoPara2);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            response.prettyPrint();
        });
    }
}
