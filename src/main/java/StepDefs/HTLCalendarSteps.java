package StepDefs;

import Constants.EndPoints;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import static Constants.EndPoints.HTLCalendar;
import static io.restassured.RestAssured.given;

public class HTLCalendarSteps implements En {

    public static String GenCalPrice;
    public static String ViatorPricingMatrixPrice;
    public static String HtlCalPrice;
    public static String StartDate;
    public static String EndDate;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;

    public static RequestSpecification requestSpecification;
    public static Response response;

    public HTLCalendarSteps() {

        When("^I click create user HotelID as \"([^\"]*)\" ToDate as \"([^\"]*)\" FromDate as \"([^\"]*)\" City as \"([^\"]*)\"$", (String HotelID, String StartDateCount, String EndDateCount,  String CityName) -> {

            LocalDate futureDateStart = LocalDate.now().plusDays(Long.parseLong(StartDateCount));
            System.out.println("Booking Start Date for Hotel Calendar is : " + futureDateStart);
            StartDate = futureDateStart.toString();

            LocalDate futureDateEnd = LocalDate.now().plusDays(Long.parseLong(EndDateCount));
            System.out.println("Booking End for Hotel Calendar Date is : " + futureDateEnd);
            EndDate = futureDateEnd.toString();


            JsonObject reqBody1 = new JsonObject();
            reqBody1.addProperty("cmp", "CT");
            reqBody1.addProperty("div", "CT_LON");
            reqBody1.addProperty("brand", "CT_OL");
            reqBody1.addProperty("channel", "U");
            reqBody1.addProperty("cliId", 10541);
            reqBody1.addProperty("cliGrp", "Direct");
            reqBody1.addProperty("cliType", "DIRECT_CLIENT");
            reqBody1.addProperty("bkgType", "STD");
            reqBody1.addProperty("srcCountry", "GB");
            reqBody1.addProperty("cur", "USD");
            reqBody1.addProperty("userId", 8778);
            reqBody1.addProperty("username", "codegen");

            JsonObject reqBody2 = new JsonObject();
            reqBody2.addProperty("city", CityName);
            reqBody2.addProperty("endDate", EndDate);
            reqBody2.addProperty("hotelCode", HotelID);
            reqBody2.addProperty("startDate", StartDate);


            JsonObject reqBody = new JsonObject();
            reqBody.add("keyControls", reqBody1);
            reqBody.add("payload", reqBody2);



            requestSpecification = given().contentType("application/json").body(reqBody.toString());

        });
        When("^I click create calendar$", () -> {

            response = requestSpecification.
                    when().post(BaseEnvironmet + HTLCalendar);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            response.prettyPrint();

        });
        And("^extract price from the response for StartDateCount Date$", () -> {

            HtlCalPrice = response.jsonPath().getString("data.products[0].dates[0][0].rateInfo.price");
            System.out.println("Price for " + StartDate + " in Hotel Calendar is: "+ HtlCalPrice);

        });
    }
}
