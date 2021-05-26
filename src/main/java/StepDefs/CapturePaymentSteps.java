package StepDefs;

import Constants.EndPoints;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static StepDefs.CreateCartSteps.CartID;
import static StepDefs.CreatePayment.PaymentReference;
import static StepDefs.SeacrhSteps.KeyControls;
import static StepDefs.UpdatePassenger.BookingPrice;
import static io.restassured.RestAssured.given;

public class CapturePaymentSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public JsonObject CapturePaymentBody;
    public String PaymentSessionURL = EndPoints.PaymentSessionURL;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;


    public CapturePaymentSteps() {


        Then("^I want to Capture the Payment$", () -> {

            requestSpecification = given();

        });
        Then("^I create the Capture Payment body$", () -> {

            JsonObject CapturePaymentPayload = new JsonObject();
            CapturePaymentPayload.addProperty("batchReceiptInfo","pm_card_amex_threeDSecureNotSupported");
            CapturePaymentPayload.addProperty("bookingType","STD");
            CapturePaymentPayload.addProperty("brand","CT");
            CapturePaymentPayload.addProperty("cardExpMonth","11");
            CapturePaymentPayload.addProperty("cardExpYear",2035);
            CapturePaymentPayload.addProperty("cardHolderName","Lasan");
            CapturePaymentPayload.addProperty("cardNumber","4242");
            CapturePaymentPayload.addProperty("cardType","VISA");
            CapturePaymentPayload.addProperty("company","CT");
            CapturePaymentPayload.addProperty("currency","GBP");
            CapturePaymentPayload.addProperty("distributionChannel","U");
            CapturePaymentPayload.addProperty("division","CTDIV_LON");
            CapturePaymentPayload.addProperty("email","test@test.test");
            CapturePaymentPayload.addProperty("isWeb",true);
            CapturePaymentPayload.addProperty("noOfDaysToDeparture",10);
            CapturePaymentPayload.addProperty("paymentReference",PaymentReference);
            CapturePaymentPayload.addProperty("paymentStatus","");
            CapturePaymentPayload.addProperty("redirectUrl","");
            CapturePaymentPayload.addProperty("total",BookingPrice);
            CapturePaymentPayload.addProperty("transactionIdentifier","");
            CapturePaymentPayload.addProperty("userId",0);
            CapturePaymentPayload.addProperty("web",true);
            CapturePaymentPayload.addProperty("cartId",CartID);


            CapturePaymentBody = new JsonObject();
            CapturePaymentBody.add("keyControls",KeyControls);
            CapturePaymentBody.add("payload",CapturePaymentPayload);
        });

        Then("^I send the Capture Payment request$", () -> {

            response = requestSpecification.
                    header("Content-Type","application/json").
                    body(CapturePaymentBody.toString()).
                    when().
                    put(BaseEnvironmet+PaymentSessionURL + "/"+ PaymentReference);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            System.out.println("Capture Payment Response body is below :"+ response.prettyPrint());
            System.out.println(PaymentSessionURL + "/"+ PaymentReference);
            System.out.println("Create Cart ID is(Capture Payment): "+ CartID);
            System.out.println("Total Price is(Capture Payment): "+ BookingPrice);
        });



    }
}
