Feature: Updates a current booking with a partial payload
@sanity
Scenario: Verify updated details  
Given User access the get bookingIds url  
When User calls the update booking API with Id and below details updation  
|firstname|lastname	         |totalprice |depositpaid|additionalneeds|
|Peter    |Parker             |140       | true      |Dinner         | 
And User also updates below bookingdates Details
|checkin    | checkout	|
|2018-01-01 | 2019-01-01 |
Then Verify the booking updation details 
And  Verify the update bookingId API response code should be 200