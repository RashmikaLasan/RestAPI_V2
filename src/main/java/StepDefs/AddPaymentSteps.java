package StepDefs;

import Constants.EndPoints;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static StepDefs.CreateCartSteps.CartID;
import static StepDefs.CreatePayment.PaymentReference;
import static StepDefs.SeacrhSteps.KeyControls;
import static StepDefs.UpdatePassenger.BookingPrice;
import static io.restassured.RestAssured.given;

public class AddPaymentSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String PromoPara1 = EndPoints.PromoPara1;
    public String AddPaymentPara= EndPoints.AddPaymentPara;
    public static JsonObject AddPaymentBody;

    public AddPaymentSteps() {
        When("^I want to Add Payments$", () -> {

            requestSpecification = given();
        });
        Then("^I create the Add Payments request body$", () -> {

            JsonObject uniqueId1 = new JsonObject();
            uniqueId1.addProperty("receiptIndex",1);
            uniqueId1.addProperty("receiptType","CRCD");

            JsonObject uniqueId = new JsonObject();
            uniqueId.add("uniqueId",uniqueId1);

            JsonObject paymentDetails1 = new JsonObject();
            paymentDetails1.addProperty("cardCategory","CreditCard");
            paymentDetails1.addProperty("cardType","VISA");
            paymentDetails1.addProperty("cardHolderName","Rashmika");
            paymentDetails1.addProperty("maskedCardNumber","************4444");
            paymentDetails1.addProperty("expireDate","2044-04-30");
            paymentDetails1.addProperty("gatewayReference","STRIPE");
            paymentDetails1.addProperty("transactionIdentifier",PaymentReference);
            paymentDetails1.addProperty("rrn","505471017057");
            paymentDetails1.addProperty("rrnKey","");
            paymentDetails1.addProperty("authorised",true);
            paymentDetails1.addProperty("authorizationCode","");
            paymentDetails1.addProperty("airlineMerchant",false);
            paymentDetails1.addProperty("cardCommission",0);
            paymentDetails1.addProperty("cardCommissionTax",0);
            paymentDetails1.addProperty("cardToken","");
            paymentDetails1.addProperty("tokenVault","");
            paymentDetails1.addProperty("voidPayment",false);


            JsonObject paymentDetails = new JsonObject();
            paymentDetails.add("paymentDetails",paymentDetails1);

            JsonObject paymentInfo1 = new JsonObject();
            paymentInfo1.addProperty("amount",BookingPrice);
            paymentInfo1.addProperty("currencyCode","GBP");
            paymentInfo1.addProperty("accountNo","");
            paymentInfo1.addProperty("bankCode","");
            paymentInfo1.addProperty("bankTransactionID","");
            paymentInfo1.addProperty("depositBy","10000");
            paymentInfo1.addProperty("payOrRefund",true);
            paymentInfo1.addProperty("realised",true);
            paymentInfo1.addProperty("realisedDate","");
            paymentInfo1.addProperty("receiptToBaseExchangeRate","1");
            paymentInfo1.addProperty("receiptToSellingExchangeRate","1");
            paymentInfo1.addProperty("reconcile",true);
            paymentInfo1.addProperty("reconcileDate","");
            paymentInfo1.addProperty("bouncedReceiptsExist",false);
            paymentInfo1.addProperty("transferReceiptId",123);
            paymentInfo1.addProperty("receiptDate","2021-04-21T10:47:06.818Z");
            paymentInfo1.addProperty("authorised",true);


            JsonObject paymentInfo = new JsonObject();
            paymentInfo.add("paymentInfo",paymentInfo1);


            JsonObject note1 = new JsonObject();
            note1.addProperty("note","Credit Card Payment");


            JsonObject payload1 = new JsonObject();
            payload1.addProperty("note","Credit Card Payment");
            payload1.add("paymentDetails",paymentDetails1);
            payload1.add("paymentInfo",paymentInfo1);
            payload1.add("uniqueId",uniqueId1);


            JsonArray payloadArray = new JsonArray();
            payloadArray.add(payload1);

            AddPaymentBody = new JsonObject();
            AddPaymentBody.add("keyControls",KeyControls);
            AddPaymentBody.add("payload", payloadArray);


        });
        Then("^I send the Add Payments request$", () -> {

            response = requestSpecification.
                    header("Content-Type","application/json").
                    body(AddPaymentBody.toString()).
                    when().
                    post(BaseEnvironmet + PromoPara1 + CartID + AddPaymentPara);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            System.out.println("AddPayment Response is below :"+ response.prettyPrint());

            System.out.println("Create Cart ID is (Add Payments): "+ CartID);
            System.out.println("Total Price is(Add Payments): "+ BookingPrice);
            System.out.println("Payment Reference in Add Payments ID is: "+ PaymentReference);
            System.out.println("AddPaymentBody Request URL is: "+ BaseEnvironmet + PromoPara1 + CartID + AddPaymentPara);
        });


    }
}
