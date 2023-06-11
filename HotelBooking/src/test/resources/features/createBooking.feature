Feature: Creates a new booking in the API
@sanity
Scenario: Verify booking is created when all details provided 
Given User access the create booking url  
When User provides below  details  
|firstname|lastname	|totalprice|depositpaid|additionalneeds|
|Tom      |Hank    |111       | true      |Breakfast      | 
And User also provides bookingdates Details
|checkin    | checkout	|
|2018-01-01 | 2019-01-01 |
Then Verify the booking details 
And  Verify the response code should be 200