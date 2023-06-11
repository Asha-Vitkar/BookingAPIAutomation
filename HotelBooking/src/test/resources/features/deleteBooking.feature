Feature: Delete a booking by its bookingId

@sanity
Scenario: Verify booking deleted based upon booking id provided  
Given User access the get bookingIds url  
When User calls the delete booking API with Id  
Then Verify response code should be 201
