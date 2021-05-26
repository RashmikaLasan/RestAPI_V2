package StepDefs;

import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonPrimitive;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import static Constants.EndPoints.TritiumURL;
import static StepDefs.HTLCalendarSteps.HtlCalPrice;
import static io.restassured.RestAssured.given;

public class TritiumSteps implements En {

    public static String GenCalPrice;
    public static String ViatorPricingMatrixPrice;
    public static String TritiumPrice;
    public static String TritStartDate;
    public static String TritEndDate;

    public static RequestSpecification requestSpecification;
    public static Response response;

    public TritiumSteps() {

        Given("^create body tritiumRequest HotelID as \"([^\"]*)\" FromDate as \"([^\"]*)\" ToDate as \"([^\"]*)\"$", (String HotelID, String StartDateCount, String EndDateCount) -> {

            LocalDate futureDateStart = LocalDate.now().plusDays(Long.parseLong(StartDateCount));
            System.out.println("Booking Start Date for Tritium Request is : " + futureDateStart);
            TritStartDate = futureDateStart.toString();

            LocalDate futureDateEnd = LocalDate.now().plusDays(Long.parseLong(EndDateCount));
            System.out.println("Booking End Date for Tritium Request is : " + futureDateEnd);
            TritEndDate = futureDateEnd.toString();


            JsonObject reqBody1 = new JsonObject();
            reqBody1.addProperty("checkIn", TritStartDate);
            reqBody1.addProperty("checkOut", TritEndDate);

            JsonObject reqBody2 = new JsonObject();
            reqBody2.addProperty("key", "sourceMarket");
            reqBody2.addProperty("value", "GB");

            JsonArray control = new JsonArray();
            control.add(reqBody2);

            JsonObject reqBody3 = new JsonObject();
            reqBody3.addProperty("infants", "0");
            reqBody3.addProperty("children", "0");
            reqBody3.addProperty("adults", "1");

            JsonArray roomsInfo = new JsonArray();
            roomsInfo.add(reqBody3);

            JsonArray jArray = new JsonArray();
            JsonPrimitive element = new JsonPrimitive(HotelID);
            jArray.add(element);


            JsonObject reqBody5 = new JsonObject();
            reqBody5.add("codes", jArray);


            JsonObject reqBody = new JsonObject();
            reqBody.add("hotels",reqBody5);
            reqBody.addProperty("rateCategory", "PKG");
            reqBody.add("stayPeriod", reqBody1);
            reqBody.add("control",control);
            reqBody.add("roomsInfo",roomsInfo);

            requestSpecification = given().
                    contentType("application/json").
                    body(reqBody.toString()).
                    headers("user-key","TEST_CT_2").
                    headers("user-sig","32e4b79370c0539efa1c3b3283095f7f193ed21233006026cdd69ccf51e6e874").
                    header("timestamp","1611779786").
                    headers("Accept","application/json").
                    headers("Content-Type","application/json");


        });
        When("^I click create Tritium$", () -> {

            response = requestSpecification.
                    when().
                    post(TritiumURL);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            response.prettyPrint();

        });
        And("^extract price from the Tritium response for June First$", () -> {

            TritiumPrice = response.jsonPath().getString("availability.hotels[0].rooms[0].rates[0].rate");
            System.out.println("June 1st Price in Tritium Response is: "+ TritiumPrice);
        });
        And("^Compare the TritiumPrice and HTLCalendarPrice$", () -> {

            if (TritiumPrice.equals(HtlCalPrice)){

                System.out.println("Comparison Success");
                System.out.println("TritiumPrice is "+ TritiumPrice);
                System.out.println("HtlCalPrice is "+ HtlCalPrice);

            }
            else {

                System.out.println("Comparison Not Success");
                System.out.println("Not Success TritiumPrice is "+ TritiumPrice);
                System.out.println("Not Success HtlCalPrice is "+  HtlCalPrice);
            }

        });
    }
}
