Feature: Verify the Pricing Calendar Details


  @cba @all @gen
  Scenario Outline: Verifying price for Generic Calendar Request
    Given I want to search for one Viator Product
    When I click Gen Product Availability of "<productID>" FromDate as "<StartDateCount>" ToDate as "<EndDateCount>"
    Then I should get a successful response with status code 200 for Gen Calendar
    And response should return Product Name as "<SupplierName>" in response
    And response should return Product Code as "<productID>"
    And extract Gen Calendar Price from the response response for StartDateCount Date
    Examples:
#      | productID | FromDate        | ToDate      |SupplierName|
#      | VT12596P7 | 2021-06-01    |  2021-08-30   |Evening Desert Safari With BBQ Dinner, Henna Painting, Camel Ride and Belly Dance|
      | productID  | StartDateCount        | EndDateCount      |SupplierName             |
      | VT206123P4 | 30                    |  120              |Desert Safari Bus Package|



    ######################################################################################################################################################################
  @msd @all @gen
  Scenario Outline: Verifying price for Viator Pricing Matrix
    Given I want to search for one Viator Product
    When i click Gen Product Availability of "<productID>" Month as "<Month>" Year as "<Year>" Currency as "<Currency>"
    Then I should get a successful response with status code 200
    And response should return Product Name as "<ProductName>"
    And extract price from the response for first of June
    And Compare GenPrice and CalPrice


    Examples:
      |  productID   |Month| Year|Currency|ProductName                                                                            |
      |  206123P4   |07   | 2021|GBP     |Desert Safari Bus Package 15:30|