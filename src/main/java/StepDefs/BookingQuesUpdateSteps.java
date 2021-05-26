package StepDefs;

import Constants.EndPoints;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.*;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static StepDefs.CreateCartSteps.CartID;
import static StepDefs.SeacrhSteps.GetRequestBody;
import static StepDefs.SeacrhSteps.KeyControls;
import static io.restassured.RestAssured.given;

public class BookingQuesUpdateSteps implements En {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public String abc;
    public ObjectNode objectNode;
    public JsonObject BookingQuestionsBody;
    public String PromoPara1 = EndPoints.PromoPara1;
    private String BaseEnvironmet = EndPoints.BaseEnvironmet;
    private String UpdateQuestions = EndPoints.UpdateQuestions;

    public BookingQuesUpdateSteps() {

        When("^I want to Update the Booking Questions$", () -> {
            requestSpecification = given();

        });





        Then("^I set the answers$", () -> {

            abc = GetRequestBody.toString();

            //Creating an instance of ObjectMapper class
            ObjectMapper objectMapper = new ObjectMapper();

            // Get ObjectNode representation of json as json is an Object
            objectNode = objectMapper.readValue(abc, ObjectNode.class);
            System.out.println(objectNode.toPrettyString());

            JsonNode jsonNode = objectMapper.readTree(abc);
            JsonNode AdditionalRequirements = jsonNode.get("additionalRequirements");
            ObjectNode firstQuestion = (ObjectNode)AdditionalRequirements.get(0);

            firstQuestion.put("answer", "en/SERVICE_AUDIO");

            ObjectNode secondQuestion = (ObjectNode)AdditionalRequirements.get(1);
            secondQuestion.put("answer", "TestRequirementsMSD");


            objectNode.set("additionalRequirements",AdditionalRequirements);

            System.out.println(objectNode.toPrettyString());
            System.out.println("objectNode  is:" + objectNode);


        });
        Then("^I add booking question answers to the body$", () -> {

            final String UpdatedPayload = objectNode.toString(); // Or use response.raw(), if want raw response string
            JsonParser jsonParser = new JsonParser();
            JsonObject PayLoad = jsonParser.parse(UpdatedPayload).getAsJsonObject();


            BookingQuestionsBody = new JsonObject();
            BookingQuestionsBody.add("keyControls",KeyControls);
            BookingQuestionsBody.add("payload",PayLoad);

            System.out.println("================ fuck ------------------ ===================");
            System.out.println(BookingQuestionsBody);
        });

        Then("^I send the request$", () -> {

            response = requestSpecification.
                    contentType("application/json").
                    body(BookingQuestionsBody.toString()).
                    when().put(BaseEnvironmet+ PromoPara1+ CartID + UpdateQuestions);
            System.out.println(((RequestSpecificationImpl) requestSpecification).getBody());
            response.prettyPrint();
        });
    }
}
