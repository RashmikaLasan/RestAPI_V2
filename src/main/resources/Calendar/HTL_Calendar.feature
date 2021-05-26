Feature: Verify the Hotel Pricing Details

  @Htl @all @htlcal
  Scenario Outline: Verifying price for Hotel Calendar Request
    When I click create user HotelID as "<HotelID>" ToDate as "<StartDateCount>" FromDate as "<EndDateCount>" City as "<CityName>"
    When I click create calendar
    Then I should get a successful response with status code 200 for HTL Calendar Response
    And extract price from the response for StartDateCount Date


    Examples:
      | HotelID     | StartDateCount    | EndDateCount    |CityName|
      |   EAN139938 |   30              |  120            |LON     |

  @Htl @all @trit
  Scenario Outline: Verifying Price for  Tritium Request
    Given create body tritiumRequest HotelID as "<HotelID>" FromDate as "<StartDateCount>" ToDate as "<EndDateCount>"
    When I click create Tritium
    Then I should get a successful response with status code 200 for Tritium Response
    And HotelID "<HotelID>" should be matched in response
    And extract price from the Tritium response for June First
    And Compare the TritiumPrice and HTLCalendarPrice

    Examples:
      | HotelID       | StartDateCount   | EndDateCount |
      |   EAN25075169 |  30            |  31         |
