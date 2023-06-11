Feature: Returns a specific booking based upon the booking id provided

@sanity
Scenario: Verify booking details based upon booking id provided  
Given User access the get bookingIds url  
When User calls the get booking API with Id  
Then Verify all the booking details
And  Verify the get bookingId api response code should be 200
