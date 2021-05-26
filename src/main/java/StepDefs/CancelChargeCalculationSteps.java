package StepDefs;

import Constants.EndPoints;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Constants.EndPoints.CancellationPara1;
import static Constants.EndPoints.CancellationPara2;
import static StepDefs.ConfirmCartSteps.BookingId;
import static io.restassured.RestAssured.given;


public class CancelChargeCalculationSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String CancellationCharge;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String PromoPara1 = EndPoints.PromoPara1;

    
    public CancelChargeCalculationSteps() {
        
        
        When("^I want to check the Cancellation Charge$", () -> {

            requestSpecification = given();

        });
        Then("^I create the Cancellation Charge request ChargeType as \"([^\"]*)\" Reason as \"([^\"]*)\" Cause as \"([^\"]*)\" excludeInsurance as \"([^\"]*)\"$", (String chargeType, String reason, String cause, String excludeInsurance) -> {
            response = requestSpecification.
                    queryParam("chargeType",chargeType).
                    queryParam("reason", reason).
                    queryParam("cause", cause).
                    queryParam("excludeInsurance",excludeInsurance).
                    when().get(BaseEnvironmet+CancellationPara1+BookingId + CancellationPara2);
            response.prettyPrint();
            System.out.println("Cancellation Charge Calculation URL is: "+ BaseEnvironmet+CancellationPara1+BookingId + CancellationPara2);


        });
        And("^display the Cancellation charge of the booking$", () -> {

            CancellationCharge = response.jsonPath().getString("data[0].totalCharge");
            System.out.println("Cancellation Charge Calculation in Response is:    "+ CancellationCharge);
            System.out.println("BookingID in Charge Calculation is: "+ BookingId);



        });

    }
}
