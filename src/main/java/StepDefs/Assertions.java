package StepDefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.restassured.response.ValidatableResponse;

import static Constants.EndPoints.HTLCalendar;
import static StepDefs.ConfirmCartSteps.BookingId;
import static org.hamcrest.CoreMatchers.equalTo;


public class Assertions implements En {

    public static ValidatableResponse validatableResponse;
    public static int TotPrice;
    public Assertions() {


        And("^Booking Status Name should \"([^\"]*)\"$", (String Confirmed) -> {
            validatableResponse.body("data[0].summary.bookingStatusName",equalTo(Confirmed));
            System.out.println("Booking Status Name Assertion is success");

        });
        And("^Assert the Booking Price$", () -> {

//            TotPrice = Integer.parseInt(TotalPriceGetReq);

            validatableResponse.body("data[0].summary.rate.price",equalTo(TotPrice));
            System.out.println("Price Assertion is success");

        });
        And("^Assert CheckIn Date as \"([^\"]*)\" and CheckOut Date as \"([^\"]*)\"$", (String CheckIn, String CheckOut) -> {

            validatableResponse.body("data[0].summary.departureDate",equalTo(CheckIn));
            validatableResponse.body("data[0].summary.returnDate",equalTo(CheckOut));
            System.out.println("CheckIn and CheckOut Dates Assertion are success and CheckIn Date is: "+CheckIn + "CheckOut Date is: "+ CheckOut );

        });
        And("^Assert Night Count as \"([^\"]*)\" Hotel Code as \"([^\"]*)\" City as \"([^\"]*)\"$", (String NightCount, String HotelCode, String City) -> {

            validatableResponse.body("data[0].summary.bookingProperties.noOfNights",equalTo(NightCount));
            System.out.println("NightCount Assertion is success and Night Count is: "+ NightCount);

            validatableResponse.body("data[0].detail.destination.cityCode",equalTo(City));
            System.out.println("City Code Assertion is success and City is: "+ City);

            validatableResponse.body("data[0].products[0].attributes[0].value",equalTo(HotelCode));
            System.out.println("HotelCode Assertion is success and Hotel Code is: "+ HotelCode);

        });
        Then("^I should get a successful response as status code (\\d+)$", (Integer statusCode) -> {
            validatableResponse = SeacrhSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success");
        });
        Then("^I should get a successful response as status code (\\d+) for Add to Cart$", (Integer statusCode) -> {
            validatableResponse = CreateCartSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Add to Cart");
        });
        Then("^I should get a successful response as status code (\\d+) for Promo Code Request$", (Integer statusCode) -> {
            validatableResponse = PromoCodeSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Promo Code Request");
        });
        Then("^I should get a successful response with status code (\\d+) for Update Passenger$", (Integer statusCode) -> {
            validatableResponse = UpdatePassenger.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Update Passenger");
        });
        Then("^I should get a successful response with status code (\\d+) for Create Payment Session$", (Integer statusCode) -> {
            validatableResponse = CreatePayment.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Create Payment Session");
        });
        Then("^I should get a successful response with status code (\\d+) for the Capture Payment$", (Integer statusCode) -> {
            validatableResponse = CapturePaymentSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Capture Payment");
        });
        Then("^I should get a successful response with status code (\\d+) for Add Payment$", (Integer statusCode) -> {
            validatableResponse = AddPaymentSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Add Payment");
        });
        Then("^I should get a successful response with status code (\\d+) for Confirm Cart$", (Integer statusCode) -> {
            validatableResponse = ConfirmCartSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Confirm Cart");
        });

        And("^the Booking Status Name should \"([^\"]*)\"$", (String Confirmed) -> {

            validatableResponse.body("data[0].summary.bookingStatusName",equalTo(Confirmed));
            System.out.println("Booking Status Name Assertion is success");
        });
        And("^I should get a successful response as status code (\\d+) for Booking Questions Update$", (Integer statusCode) -> {
            validatableResponse = BookingQuesUpdateSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Booking Questions Update");
        });
        Then("^I should get a successful response with status code (\\d+) for Cancellation Charge Calculation$", (Integer statusCode) -> {

            validatableResponse = CancelChargeCalculationSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for CancelChargeCalculationSteps");

        });
        Then("^I should get a successful response with status code (\\d+) for Booking Cancellation$", (Integer statusCode) -> {

            validatableResponse = CancelBookingSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Booking Cancellation");

        });
        Then("^I should get a successful response with status code (\\d+) for Booking Retrieve$", (Integer statusCode) -> {

            validatableResponse = RetrieveSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Booking Retrieve");

        });

        And("^Display the Booking ID of the Booking in Retrieve Response$", () -> {


            validatableResponse.body("data[0].summary.id",equalTo(Integer.parseInt(BookingId)));
            System.out.println("Booking ID Assertion is success and Booking ID is:  "+ BookingId);

        });
        Then("^I should get a successful response with status code (\\d+) for HTL Calendar Response$", (Integer statusCode) -> {

            validatableResponse = HTLCalendarSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for HTL Calendar Response");

        });
        Then("^I should get a successful response with status code (\\d+) for Tritium Response$", (Integer statusCode) -> {

            validatableResponse = TritiumSteps.response.then().statusCode(statusCode);
            System.out.println("Status Code 200 and its success for Tritium Response");

        });
        And("^HotelID \"([^\"]*)\" should be matched in response$", (String HotelID) -> {

            validatableResponse.body("availability.hotels[0].code",equalTo(HotelID));
            System.out.println("Hotel Code Assertion Success");

        });
        Then("^I should get a successful response with status code (\\d+) for Gen Calendar$", (Integer statusCode) -> {

            validatableResponse = GenCalendarSteps.response.then().statusCode(statusCode);
            System.out.println("GenCalendar status code is 200");

        });
        And("^response should return Product Name as \"([^\"]*)\" in response$", (String SupplierName) -> {

            validatableResponse.body("data[0].products[0].supplierName",equalTo(SupplierName));
            System.out.println("Product Code Assertion Success");

        });
        And("^response should return Product Code as \"([^\"]*)\"$", (String productID) -> {

            validatableResponse.body("data[0].products[0].supplierCode",equalTo(productID));
            System.out.println("SupplierCode Assertion Success");


        });

    }
}
