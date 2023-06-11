package com.org.booking.stepdefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import com.org.booking.pojo.CreateBookingPojo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;




public class VerifyGetBookingByIdStepdef {
	
	
	Response response;
	CreateBookingPojo cb =new CreateBookingPojo();
	@When("User calls the get booking API with Id")
	public void user_calls_the_get_booking_api_with_id() {
		response=given().log().all().header("accept","application/json").pathParam("id",VerifyCreateAPIStepdef.newlyCreatedBookingId).when().get("/booking/{id}")
				.then().extract().response();
				
	}

	@Then("Verify all the booking details")
	public void verify_all_the_booking_details()
	{
		    cb=response.as(CreateBookingPojo.class);
	        int totalPrice=cb.getTotalprice();	      
			assertEquals("Tom",cb.getFirstname());
			assertEquals("Hank",cb.getLastname());
			assertEquals(111,totalPrice);
			assertEquals(true,cb.getDepositpaid());
			assertEquals("Breakfast",cb.getAdditionalneeds());
			assertEquals("2018-01-01",cb.getBookingdates().getCheckin());
			assertEquals("2019-01-01",cb.getBookingdates().getCheckout());
	    
	}
	@Then("Verify the get bookingId api response code should be {int}")
	public void Verify_the_get_bookingId_api_response_code_should_be(int int1) {
		assertEquals(int1, response.getStatusCode());
	}

	
}
