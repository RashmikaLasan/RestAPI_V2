package StepDefs;

import Constants.EndPoints;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static StepDefs.CreateCartSteps.CartID;
import static StepDefs.CreateCartSteps.TravellersKey1;
import static StepDefs.CreateCartSteps.TravellersKey2;
import static StepDefs.SeacrhSteps.KeyControls;
import static StepDefs.SeacrhSteps.TotalPriceGetRequest;
import static io.restassured.RestAssured.given;

public class UpdatePassenger implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public String UpdatePassengerPara = EndPoints.UpdatePassengerPara;
    public String PromoPara1 = EndPoints.PromoPara1;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    public JsonObject updatePassengerBody;
    public static String BookingPrice;

    public UpdatePassenger() {

        When("^I want to create Update Passenger$", () -> {

            requestSpecification = given();

        });
        Then("^I create update passenger body$", () -> {

            JsonObject Contact1 = new JsonObject();
            Contact1.addProperty("contactNo",1);
            Contact1.addProperty("contactType","Telephone");
            Contact1.addProperty("value","+94711996777");
            Contact1.addProperty("main",true);
            Contact1.addProperty("preferred",true);

            JsonObject Contact2 = new JsonObject();
            Contact2.addProperty("contactNo",2);
            Contact2.addProperty("contactType","E-mail");
            Contact2.addProperty("value","lasanrash@gmail.com");
            Contact2.addProperty("main",false);
            Contact2.addProperty("preferred",false);

            JsonArray Contact = new JsonArray();
            Contact.add(Contact1);
            Contact.add(Contact2);

            JsonObject Address = new JsonObject();
            Address.addProperty("no", 0);
            Address.addProperty("street", "MEL");
            Address.addProperty("city", "MEL");
            Address.addProperty("county", "");
            Address.addProperty("postalCode", "81000");
            Address.addProperty("country", "AU");
            Address.addProperty("main", true);
            Address.addProperty("houseNo", "travelnow");
            Address.addProperty("category", 0);
            Address.addProperty("external", false);
            Address.add("contacts", Contact);

            JsonArray Addresses = new JsonArray();
            Addresses.add(Address);

            JsonArray frequentFlyerNumbers1 = new JsonArray();
            JsonArray memberships1 = new JsonArray();


            JsonObject profile1 = new JsonObject();
            profile1.addProperty("title","Mr");
            profile1.addProperty("firstName","Lasan");
            profile1.addProperty("middleName","LR");
            profile1.addProperty("lastName","Rashmika");
            profile1.addProperty("type","A");
            profile1.addProperty("subType","A");
            profile1.addProperty("dob","1990-01-01");
            profile1.addProperty("dobUnknown",false);
            profile1.addProperty("age",31);
            profile1.addProperty("gender","M");
            profile1.addProperty("vip",false);
            profile1.addProperty("smoker",false);
            profile1.addProperty("dummy",false);
            profile1.add("addresses",Addresses);
            profile1.add("frequentFlyerNumbers",frequentFlyerNumbers1);
            profile1.add("memberships",memberships1);

            JsonArray externalRefs1 = new JsonArray();
            JsonArray externalRefs2 = new JsonArray();

            JsonObject reference1 = new JsonObject();
            reference1.addProperty("key",TravellersKey1);
            reference1.add("externalRefs",externalRefs1);

            JsonObject reference2 = new JsonObject();
            reference2.addProperty("key",TravellersKey2);
            reference2.add("externalRefs",externalRefs2);

            JsonObject payload1 = new JsonObject();
            payload1.addProperty("key",TravellersKey1);
            payload1.addProperty("no",1);
            payload1.addProperty("lead",true);
            payload1.addProperty("driver",false);
            payload1.addProperty("activeForBooking",true);
            payload1.addProperty("refuseInsurance",false);
            payload1.addProperty("infantSeatRequired",false);
            payload1.addProperty("primaryPpForBooking",false);
            payload1.addProperty("endorsedPpForBooking",false);
            payload1.addProperty("endorsedPassengerNo",0);
            payload1.addProperty("refuseInsurance",false);
            payload1.addProperty("allocatedForBooking",false);
            payload1.add("profile",profile1);
            payload1.add("reference",reference1);

            JsonObject payload2 = new JsonObject();
            payload2.addProperty("key",TravellersKey2);
            payload2.addProperty("no",2);
            payload2.addProperty("lead",false);
            payload2.addProperty("driver",false);
            payload2.addProperty("activeForBooking",true);
            payload2.addProperty("refuseInsurance",false);
            payload2.addProperty("infantSeatRequired",false);
            payload2.addProperty("primaryPpForBooking",false);
            payload2.addProperty("endorsedPpForBooking",false);
            payload2.addProperty("endorsedPassengerNo",0);
            payload2.addProperty("refuseInsurance",false);
            payload2.addProperty("allocatedForBooking",false);
            payload2.add("profile",profile1);
            payload2.add("reference",reference2);

            JsonArray payloadBody = new JsonArray();
            payloadBody.add(payload1);
            payloadBody.add(payload2);
            System.out.println("Payload 1 is"+payload1);
            System.out.println("Payload 2 is"+payload2);


            updatePassengerBody = new JsonObject();
            updatePassengerBody.add("keyControls",KeyControls);
            updatePassengerBody.add("payload",payloadBody);


            System.out.println("Updates Traveller Complete Body is:     "+ updatePassengerBody);
            System.out.println("----------------Request and Response Below-----------------------");


        });
        Then("^I send the Update Passenger request$", () -> {
            response = requestSpecification.
                    header("Content-Type","application/json").
                    body(updatePassengerBody.toString()).
                    when().
                    put(BaseEnvironmet + PromoPara1 +CartID + UpdatePassengerPara);

            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            System.out.println("Update Traveller Response is below :"+ response.prettyPrint());
            System.out.println("Update Passenger URL is:  "+ BaseEnvironmet + PromoPara1 +CartID + UpdatePassengerPara);
            System.out.println("Create Cart ID is(UpdatesTraveller): "+ CartID);
            System.out.println("Total Price is(UpdatesTraveller): "+ TotalPriceGetRequest);

        });


        And("^store the Price need to be paid in booking$", () -> {

            BookingPrice = response.jsonPath().getString("data[0].summary.rate.price");
            System.out.println("Update Passenger Response Total Price of Booking is: "+ BookingPrice);

        });
    }
}