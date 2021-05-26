package StepDefs;

import Constants.EndPoints;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonElement;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;

public class SeacrhSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static JsonElement GetRequestBody;
    public static JsonElement KeyControls;
    public static String TotalPriceGetRequest;

    private String SearchURLPara = EndPoints.SearchURLPara;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String SearchURLParaGen = EndPoints.SearchURLParaGen;
    
    
    public SeacrhSteps() {
        
        Given("^I want to search for Hotel Availability search for two Adults$", () -> {

            requestSpecification = given();

        });

        When("^I Enter the checkIn date as \"([^\"]*)\" Night Count as \"([^\"]*)\" Hotel Code as \"([^\"]*)\" City as \"([^\"]*)\"$", (String FutureDateCount, String NightCount, String HotelCode, String City) -> {

            LocalDate futureDate = LocalDate.now().plusDays(Long.parseLong(FutureDateCount));
            System.out.println("Booking Date is : " + futureDate);
            String StartDate = futureDate.toString();


            response = requestSpecification.
                    queryParam("cmp", "CT").
                    queryParam("channel", "U").
                    queryParam("bkgType", "STD").
                    queryParam("cliGrp", "Direct").
                    queryParam("cliId", -1).
                    queryParam("cliType", "DIRECT_CLIENT").
                    queryParam("cur", "GBP").
                    queryParam("srcCountry", "GB").
                    queryParam("userId", 8778).
                    queryParam("username", "codegen").
                    queryParam("div", "CTDIV_LON").
                    queryParam("brand", "CT").
                    queryParam("tax", "true").
                    queryParam("quote", "false").
                    queryParam("tbxResults", "false").
                    queryParam("h2hResult", "true").
                    queryParam("expand", "all").
                    queryParam("hotelCode", HotelCode).
                    queryParam("city", City).
                    queryParam("startDate", StartDate).
                    queryParam("nights", 3).
                    queryParam("adult", "2~30~1990-01-01~1").
                    when().get(BaseEnvironmet+SearchURLPara);
            response.prettyPrint();

        });
        And("^Store the Data Body$", () -> {
            Gson gson = new Gson();
            GetRequestBody = gson.toJsonTree(response.jsonPath().getJsonObject("data[0]"));
            System.out.println("Get Request Body is:     "+GetRequestBody);
            KeyControls = gson.toJsonTree(response.jsonPath().getJsonObject("keyControls"));
            System.out.println("KeyControls are:     "+KeyControls);
        });
        And("^Store the total Price of the Booking$", () -> {

            TotalPriceGetRequest = response.jsonPath().getString("data[0].rate.price");
            System.out.println("Get Search Request Total Price is: "+ TotalPriceGetRequest);

        });
        Given("^I want to search for Generic Availability search for two Adults$", () -> {

            requestSpecification = given();
        });
        When("^I Enter the checkIn date as \"([^\"]*)\" Product Code as \"([^\"]*)\" City as \"([^\"]*)\"$", (String FutureDateCount, String ProductCode, String City) -> {

            LocalDate futureDate = LocalDate.now().plusDays(Long.parseLong(FutureDateCount));
            System.out.println("Booking Date is : " + futureDate);
            String StartDate = futureDate.toString();

            response = requestSpecification.
                    queryParam("cmp", "CT").
                    queryParam("channel", "U").
                    queryParam("brand", "CT").
                    queryParam("div", "CTDIV_LON").
                    queryParam("cur", "GBP").
                    queryParam("bkgType", "STD").
                    queryParam("cliGrp", "Direct").
                    queryParam("cliId", -1).
                    queryParam("cliType", "DIRECT_CLIENT").
                    queryParam("srcCountry", "GB").
                    queryParam("userId", 8778).
                    queryParam("username", "codegen").
                    queryParam("tax", "true").
                    queryParam("quote", "false").
                    queryParam("tbxResults", "false").
                    queryParam("h2hResult", "true").
                    queryParam("expand", "all").
                    queryParam("hotelCode", ProductCode).
                    queryParam("city", City).
                    queryParam("startDate", StartDate).
                    queryParam("adult", "2~31~1990-01-01~1").
                    when().get(BaseEnvironmet+SearchURLParaGen);
            response.prettyPrint();
        });

    }
}
