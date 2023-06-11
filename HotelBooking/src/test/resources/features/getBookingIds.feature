Feature: Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids
@sanity
Scenario: Verify all bookingIds wihout any filter  
Given User access the get bookingIds url  
When User calls the get bookingIds API without any filter  
Then Verify all the bookingIds  
And  Verify the get bookingIds API without filter response code should be 200

@sanity
Scenario: Verify all bookingIds filter by name 
Given User access the get bookingIds url  
When User calls the get bookingIds API with filter firstname and lastname 
Then Verify all the bookingIds for the filter firstname and lastname  
And  Verify the get bookingIds API response code should be 200