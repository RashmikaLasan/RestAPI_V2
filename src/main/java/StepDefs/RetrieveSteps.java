package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Constants.EndPoints.PromoPara1;
import static StepDefs.ConfirmCartSteps.BookingId;
import static StepDefs.ConfirmCartSteps.bookingReference;
import static io.restassured.RestAssured.given;

public class RetrieveSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    
    public RetrieveSteps() {
        
        
        When("^I want Retrieve the Booking ID$", () -> {
            requestSpecification = given();
                        
        });


        Then("^I create the Get request as userId as \"([^\"]*)\" UserName as \"([^\"]*)\" CliendID as \"([^\"]*)\" Expand as \"([^\"]*)\"$", (String userId, String UserName, String CliendID, String Expand) -> {

            response = requestSpecification.
                    queryParam("userId", userId).
                    queryParam("username", UserName).
                    queryParam("cliId", CliendID).
                    queryParam("expand", Expand).
                    header("bkng-tkn",bookingReference).
                    when().get(BaseEnvironmet+PromoPara1+ BookingId);
            response.prettyPrint();

            System.out.println("Booking Retrieve URL is: "+BaseEnvironmet+PromoPara1+ BookingId);

        });
        And("^Display the Booking Reference of the Booking Retrieve Response$", () -> {
            bookingReference = response.jsonPath().getString("data[0].summary.bookingProperties.bookingReference");
            System.out.println("Booking Reference in Booking Retrieve Response is: "+ bookingReference);

        });


    }
}
