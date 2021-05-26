package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static StepDefs.CreateCartSteps.CartID;
import static StepDefs.SeacrhSteps.KeyControls;
import static StepDefs.UpdatePassenger.BookingPrice;
import static io.restassured.RestAssured.given;

public class ConfirmCartSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String PromoPara1 = EndPoints.PromoPara1;
    public String ConfirmPara = EndPoints.ConfirmPara;
    public static JsonObject ConfirmCartBody;
    public static String BookingId;
    public static String LeadName;
    public static String bookingReference;


    public ConfirmCartSteps() {
        When("^I want to Confirm Cart$", () -> {
            requestSpecification = given();

        });
        Then("^I create the Confirm Cart body$", () -> {
            ConfirmCartBody = new JsonObject();
            ConfirmCartBody.add("keyControls",KeyControls);
        });

        Then("^I send the Confirm Cart request$", () -> {

            response = requestSpecification.
                    header("Content-Type","application/json").
                    body(ConfirmCartBody.toString()).
                    when().
                    post(BaseEnvironmet+ PromoPara1 + CartID + ConfirmPara);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            System.out.println("Confirm Cart  Response is below :"+ response.prettyPrint());
            System.out.println("Confirm Cart Request URL is: "+ BaseEnvironmet+ PromoPara1 + CartID + ConfirmPara);
            System.out.println("Create Cart ID is: "+ CartID);
            System.out.println("Total Price is: "+ BookingPrice);

        });

        And("^Display the Booking ID of the Booking$", () -> {

            BookingId = response.jsonPath().getString("data[0].summary.id");
            System.out.println("BookingID in Booking Confirmation Response is: "+ BookingId);

        });


        And("^Display the Booking Reference of the Booking$", () -> {

            bookingReference = response.jsonPath().getString("data[0].summary.bookingProperties.bookingReference");
            System.out.println("Booking Reference in Booking Confirmation Response is: "+ bookingReference);

        });


    }
}
