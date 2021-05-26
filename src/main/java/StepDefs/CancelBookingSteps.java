package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static StepDefs.ConfirmCartSteps.BookingId;
import static io.restassured.RestAssured.given;

public class CancelBookingSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String CancellationMesssage;

    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String PromoPara1 = EndPoints.PromoPara1;

    public CancelBookingSteps() {

        When("^I want to cancel the booking$", () -> {

            requestSpecification = given();

        });
        Then("^I create the cancellation request confirm as \"([^\"]*)\" reason as (\\d+) cause as (\\d+) userID as (\\d+) bkgSource as \"([^\"]*)\" tbxOnly as \"([^\"]*)\" cnxEmptyBkg as \"([^\"]*)\"$", (String confirm, Integer reason, Integer cause, Integer userId, String bkgSource, String tbxOnly, String cnxEmptyBkg) -> {

            response = requestSpecification.
                    queryParam("confirm",confirm).
                    queryParam("reason", reason).
                    queryParam("cause", cause).
                    queryParam("userId",userId).
                    queryParam("bkgSource",bkgSource).
                    queryParam("tbxOnly",tbxOnly).
                    queryParam("cnxEmptyBkg",cnxEmptyBkg).
                    when().delete(BaseEnvironmet+ PromoPara1+ BookingId);
                    response.prettyPrint();

        });
        And("^display the message of response$", () -> {

            CancellationMesssage = response.jsonPath().getString("status.message");
            System.out.println("Cancellation Messsage in Response is: "+ CancellationMesssage);
            System.out.println("BookingID in Booking Cancellation is: "+ BookingId);

        });
    }
}
