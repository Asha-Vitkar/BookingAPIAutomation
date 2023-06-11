package com.org.booking.stepdefs;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.org.booking.utils.GenerateRandomNumber;

import io.cucumber.java.en.Then;

public class VerifyDeleteBookingStepdef {
	
	Response response;
	@When("User calls the delete booking API with Id")
	public void user_calls_the_delete_booking_api_with_id() {
		
	response=given().contentType(ContentType.JSON).accept("application/json")
		 .header("Cookie","token="+VerifyUpdateBookingStepdef.tokenValue).pathParam("id",GenerateRandomNumber.getRandomNumber()).log().all()
		   .when().delete("/booking/{id}").then().log().all().extract().response();
	   
	}
   
	@Then("Verify response code should be {int}")
	public void verify_response_code_should_be(int  int1) {
	    assertEquals(int1,response.getStatusCode());
	}

}
