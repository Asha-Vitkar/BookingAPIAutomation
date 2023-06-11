package com.org.booking.stepdefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import com.org.booking.utils.ReadProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.hasItem;
public class VerifyGetBookingIdsStepdef {
	
	
	Response response;
	Response responseByName;
	
	@Given("User access the get bookingIds url")
	public void user_access_the_get_booking_ids_url() throws IOException {
		RestAssured.baseURI=ReadProperties.getBaseUrl("baseUrl");
	}

	@When("User calls the get bookingIds API without any filter")
	public void user_calls_the_get_booking_ids_api_without_any_filter() {
		response=given().log().all().header("accept","application/json").when().get("/booking").then()
				.body("bookingid",hasItem(VerifyCreateAPIStepdef.newlyCreatedBookingId))
				.log().all().extract().response();
	}

	@Then("Verify all the bookingIds")
	public void verify_all_the_booking_ids() {
		JsonPath js=response.getBody().jsonPath();
		assertNotNull(js.get("bookingid"));
	}
	@Then("Verify the get bookingIds API without filter response code should be {int}")
	public void verify_the_get_bookingIds_API_without_filter_response_code_should_be(int int1) {
		assertEquals(int1, response.getStatusCode());
	}

	
	@When("User calls the get bookingIds API with filter firstname and lastname")
	public void user_calls_the_get_booking_ids_api_with_filter_firstname_and_lastname() {
		responseByName=given().log().all().header("accept","application/json").queryParams("firstname", "Tom", "lastname","Hank")
				.when().get("/booking").then().log().all().extract().response();
	}

	@Then("Verify all the bookingIds for the filter firstname and lastname")
	public void verify_all_the_booking_ids_for_the_filter_firstname_and_lastname() {
		JsonPath js=responseByName.getBody().jsonPath();
		int bookingid=js.get("[0].bookingid");
		assertEquals(VerifyCreateAPIStepdef.newlyCreatedBookingId, bookingid);
		assertNotNull(js.get("bookingid"));	  
	}
	@Then("Verify the get bookingIds API response code should be {int}")
	public void verify_the_get_bookingIds_API_response_code_should_be(int int1) {
		assertEquals(int1, responseByName.getStatusCode());
	}

	
}
