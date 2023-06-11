Feature: Create,update,get and delete a booking

@regression
Scenario: create a new booking
Given User has below request details
|firstname|lastname	|totalprice|depositpaid|additionalneeds|
|Tom      |Hank    |111       | true      |Breakfast       | 
When User create a new booking by providing below booking details
|checkin    | checkout	 |
|2018-01-01 | 2019-01-01 |
Then booking should be created with response code 200

@regression
Scenario: get a new booking details by its id
When User call the Get booking api by its id
Then User should get booking details with response code 200 

@regression
Scenario: update a new booking details by its id
Given User wants to update below request details
|firstname|lastname	          |totalprice |depositpaid|additionalneeds|
|Dexter    |Morgan            |140       | true       |Dinner         | 
When User calls the update api with below bookingDate updation details 
|checkin    | checkout	|
|2018-02-02 | 2019-02-02 |
Then User should get updated booking details with response code 200 

@regression
Scenario: delete a booking by its id
When User calls the delete booking api by its id
Then User should get response code 201