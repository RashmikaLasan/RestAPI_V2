package StepDefs;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import static Constants.EndPoints.BaseEnvironmet;
import static Constants.EndPoints.GENCalendar;
import static io.restassured.RestAssured.given;

public class GenCalendarSteps implements En {


    public static String GenCalStartDate;
    public static String GenCalEndDate;
    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String GenCalPrice;

    public GenCalendarSteps() {

        Given("^I want to search for one Viator Product$", () -> {
            requestSpecification = given();
        });
        When("^I click Gen Product Availability of \"([^\"]*)\" FromDate as \"([^\"]*)\" ToDate as \"([^\"]*)\"$", (String productID, String StartDateCount, String EndDateCount) -> {

            LocalDate futureDateStart = LocalDate.now().plusDays(Long.parseLong(StartDateCount));
            System.out.println("Gen Calendar Start Date for Gen Calendar Request is : " + futureDateStart);
            GenCalStartDate = futureDateStart.toString();

            LocalDate futureDateEnd = LocalDate.now().plusDays(Long.parseLong(EndDateCount));
            System.out.println("Gen Calendar End Date for Gen Calendar Request is : " + futureDateEnd);
            GenCalEndDate = futureDateEnd.toString();

            response = requestSpecification.
                    queryParam("cmp", "CT").
                    queryParam("channel", "U").
                    queryParam("cur", "GBP").
                    queryParam("cliGrp", "Direct").
                    queryParam("cliType", "DIRECT_CLIENT").
                    queryParam("bkgType", "STD").
                    queryParam("srcCountry", "GB").
                    queryParam("div", "CT_LON").
                    queryParam("brand", "U").
                    queryParam("cliId", 10541).
                    queryParam("fromDate", GenCalStartDate).
                    queryParam("toDate", GenCalEndDate).
                    queryParam("supplierCodes", productID).
                    queryParam("searchType", "CACHE_AND_LIVE_SEARCH").
                    when().get(BaseEnvironmet + GENCalendar);
            response.prettyPrint();


        });
        And("^extract Gen Calendar Price from the response response for StartDateCount Date$", () -> {

            GenCalPrice = response.jsonPath().getString("data[0].products[0].dates[0].rateInfo.price");
            System.out.println("Price for " + GenCalStartDate + " in Gen Calendar is: "+ GenCalPrice);


        });
    }
}
