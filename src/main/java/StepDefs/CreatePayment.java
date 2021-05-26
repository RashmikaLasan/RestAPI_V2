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

public class CreatePayment implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public String PaymentSessionURL = EndPoints.PaymentSessionURL;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    public JsonObject CreatePaymentSessionBody;
    public static String PaymentReference;



    public CreatePayment() {

        Then("^I want to create Payment Session$", () -> {

            requestSpecification = given();
        });
        And("^I create the Payment Session body$", () -> {

            JsonObject PaymentSessionPayload = new JsonObject();
            PaymentSessionPayload.addProperty("batchReceiptInfo","pm_card_amex_threeDSecureNotSupported");
            PaymentSessionPayload.addProperty("bookingType","STD");
            PaymentSessionPayload.addProperty("brand","CT");
            PaymentSessionPayload.addProperty("cardExpMonth","");
            PaymentSessionPayload.addProperty("cardExpYear","");
            PaymentSessionPayload.addProperty("cardHolderName","");
            PaymentSessionPayload.addProperty("cardNumber","");
            PaymentSessionPayload.addProperty("cardType","");
            PaymentSessionPayload.addProperty("company","CT");
            PaymentSessionPayload.addProperty("currency","GBP");
            PaymentSessionPayload.addProperty("distributionChannel","U");
            PaymentSessionPayload.addProperty("division","CTDIV_LON");
            PaymentSessionPayload.addProperty("email","test@test.test");
            PaymentSessionPayload.addProperty("isWeb",true);
            PaymentSessionPayload.addProperty("noOfDaysToDeparture",10);
            PaymentSessionPayload.addProperty("paymentReference","");
            PaymentSessionPayload.addProperty("paymentStatus","");
            PaymentSessionPayload.addProperty("redirectUrl","");
            PaymentSessionPayload.addProperty("total",BookingPrice);
            PaymentSessionPayload.addProperty("transactionIdentifier","");
            PaymentSessionPayload.addProperty("userId",0);
            PaymentSessionPayload.addProperty("web",true);
            PaymentSessionPayload.addProperty("cartId",CartID);



            CreatePaymentSessionBody = new JsonObject();
            CreatePaymentSessionBody.add("keyControls",KeyControls);
            CreatePaymentSessionBody.add("payload",PaymentSessionPayload);

        });
        Then("^I send the Create Payment Session request$", () -> {

            response = requestSpecification.
                    header("Content-Type","application/json").
                    body(CreatePaymentSessionBody.toString()).
                    when().
                    post(BaseEnvironmet+PaymentSessionURL);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            System.out.println("CreatePaymentSessionBody Response body is below :"+ response.prettyPrint());

        });
        And("^store the paymentReference ID in Create Payment Session response$", () -> {
            PaymentReference = response.jsonPath().getString("data[0].paymentReference");
            System.out.println("Create Payment Session Request URL is: "+ BaseEnvironmet + PaymentSessionURL);
            System.out.println("Update Passenger Response PaymentReference of Booking is: "+ PaymentReference);
            System.out.println("Create Cart ID is: "+ CartID);
            System.out.println("Total Price is: "+ BookingPrice);
        });

    }
}
