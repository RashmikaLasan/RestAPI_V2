Feature: Verify the V2 Gen Flow

  @genpromono
  Scenario Outline: Verifying V2 HTL Flow with the PromoCode
    Given I want to search for Generic Availability search for two Adults
    When I Enter the checkIn date as "<FutureDateCount>" Product Code as "<ProductCode>" City as "<City>"
    Then I should get a successful response as status code 200
    And Store the Data Body
    And Store the total Price of the Booking

    #Add To Cart
    Then I want to createCart
    Then I Create Cart by adding the body
    Then I should get a successful response as status code 200 for Add to Cart
    And I store the Cart ID
    And I store the TravellersKey of Passenger One
    And I store the TravellersKey of Passenger Two

    #Booking Questions Update
    When I want to Update the Booking Questions
    Then I set the answers
    Then I add booking question answers to the body
    Then I send the request
    And I should get a successful response as status code 200 for Booking Questions Update

    #Update Passenger
    When I want to create Update Passenger
    Then I create update passenger body
    Then I send the Update Passenger request
    Then I should get a successful response with status code 200 for Update Passenger
    And store the Price need to be paid in booking

    #Create Payment Session
    Then I want to create Payment Session
    And I create the Payment Session body
    Then I send the Create Payment Session request
    Then I should get a successful response with status code 200 for Create Payment Session
    And store the paymentReference ID in Create Payment Session response

    #Capture Payment
    Then I want to Capture the Payment
    Then I create the Capture Payment body
    Then I send the Capture Payment request
    Then I should get a successful response with status code 200 for the Capture Payment

    #Add Payment
    When I want to Add Payments
    Then I create the Add Payments request body
    Then I send the Add Payments request
    Then I should get a successful response with status code 200 for Add Payment


    #Confirm Cart
    When I want to Confirm Cart
    Then I create the Confirm Cart body
    Then I send the Confirm Cart request
    Then I should get a successful response with status code 200 for Confirm Cart
    And Display the Booking ID of the Booking
    And the Booking Status Name should "Confirmed"



    Examples:
      | FutureDateCount     | ProductCode | City |
      | 4                  | VT110804P146 | PAR |


    ##########################################
  @genpromo
  Scenario Outline: Verifying V2 HTL Flow with the PromoCode
    Given I want to search for Generic Availability search for two Adults
    When I Enter the checkIn date as "<FutureDateCount>" Product Code as "<ProductCode>" City as "<City>"
    Then I should get a successful response as status code 200
    And Store the Data Body
    And Store the total Price of the Booking

    #Add To Cart
    Then I want to createCart
    Then I Create Cart by adding the body
    Then I should get a successful response as status code 200 for Add to Cart
    And I store the Cart ID
    And I store the TravellersKey of Passenger One
    And I store the TravellersKey of Passenger Two

#    Questions Update
    When I want to Update the Booking Questions
    Then I set the answers
    Then I add booking question answers to the body
    Then I send the request
    And I should get a successful response as status code 200 for Booking Questions Update

    #Promo Code Apply
    When I want to add Promo Code
    Then I Create Promo Code Body with promo code as "<PromoCode>"
    Then I send the request in Promo Code
    Then I should get a successful response as status code 200 for Promo Code Request

    #Update Passenger
    When I want to create Update Passenger
    Then I create update passenger body
    Then I send the Update Passenger request
    Then I should get a successful response with status code 200 for Update Passenger
    And store the Price need to be paid in booking

    #Create Payment Session
    Then I want to create Payment Session
    And I create the Payment Session body
    Then I send the Create Payment Session request
    Then I should get a successful response with status code 200 for Create Payment Session
    And store the paymentReference ID in Create Payment Session response

    #Capture Payment
    Then I want to Capture the Payment
    Then I create the Capture Payment body
    Then I send the Capture Payment request
    Then I should get a successful response with status code 200 for the Capture Payment

    #Add Payment
    When I want to Add Payments
    Then I create the Add Payments request body
    Then I send the Add Payments request
    Then I should get a successful response with status code 200 for Add Payment


    #Confirm Cart
    When I want to Confirm Cart
    Then I create the Confirm Cart body
    Then I send the Confirm Cart request
    Then I should get a successful response with status code 200 for Confirm Cart
    And Display the Booking ID of the Booking
    And the Booking Status Name should "Confirmed"



    Examples:
      | FutureDateCount     | ProductCode | City |PromoCode|
      | 60                  | VT110804P146 | PAR |CTPromo  |

    ##############################Booking Cancellation#############################################

  @gencancel
  Scenario Outline: Verifying V2 HTL Flow with the PromoCode
    Given I want to search for Generic Availability search for two Adults
    When I Enter the checkIn date as "<FutureDateCount>" Product Code as "<ProductCode>" City as "<City>"
    Then I should get a successful response as status code 200
    And Store the Data Body
    And Store the total Price of the Booking

    #Add To Cart
    Then I want to createCart
    Then I Create Cart by adding the body
    Then I should get a successful response as status code 200 for Add to Cart
    And I store the Cart ID
    And I store the TravellersKey of Passenger One
    And I store the TravellersKey of Passenger Two

    #Booking Questions Update
    When I want to Update the Booking Questions
    Then I set the answers
    Then I add booking question answers to the body
    Then I send the request
    And I should get a successful response as status code 200 for Booking Questions Update

    #Update Passenger
    When I want to create Update Passenger
    Then I create update passenger body
    Then I send the Update Passenger request
    Then I should get a successful response with status code 200 for Update Passenger
    And store the Price need to be paid in booking

    #Create Payment Session
    Then I want to create Payment Session
    And I create the Payment Session body
    Then I send the Create Payment Session request
    Then I should get a successful response with status code 200 for Create Payment Session
    And store the paymentReference ID in Create Payment Session response

    #Capture Payment
    Then I want to Capture the Payment
    Then I create the Capture Payment body
    Then I send the Capture Payment request
    Then I should get a successful response with status code 200 for the Capture Payment

    #Add Payment
    When I want to Add Payments
    Then I create the Add Payments request body
    Then I send the Add Payments request
    Then I should get a successful response with status code 200 for Add Payment


    #Confirm Cart
    When I want to Confirm Cart
    Then I create the Confirm Cart body
    Then I send the Confirm Cart request
    Then I should get a successful response with status code 200 for Confirm Cart
    And Display the Booking ID of the Booking
    And the Booking Status Name should "Confirmed"

    #Calculate the Cancellation Charge
    When I want to check the Cancellation Charge
    Then I create the Cancellation Charge request ChargeType as "<chargeType>" Reason as "<reason>" Cause as "<cause>" excludeInsurance as "<excludeInsurance>"
    Then I should get a successful response with status code 200 for Cancellation Charge Calculation
    And display the Cancellation charge of the booking

    #Cancel the Booking
    When I want to cancel the booking
    Then I create the cancellation request confirm as "true" reason as 2 cause as 499 userID as 8778 bkgSource as "TC" tbxOnly as "false" cnxEmptyBkg as "true"
    Then I should get a successful response with status code 200 for Booking Cancellation
    And display the message of response


    Examples:
      | FutureDateCount     | ProductCode | City |chargeType|reason|cause|excludeInsurance|
      | 60                  | VT110804P146 | PAR |CNX       |2     |499  |false           |