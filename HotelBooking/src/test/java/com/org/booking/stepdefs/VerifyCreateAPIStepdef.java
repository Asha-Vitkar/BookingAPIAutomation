package com.org.booking.stepdefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.org.booking.pojo.BookingDates;
import com.org.booking.pojo.CreateBookingPojo;
import com.org.booking.utils.ReadProperties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class VerifyCreateAPIStepdef {
	
	
	Response response;
	CreateBookingPojo cb =new CreateBookingPojo();
	BookingDates bd=new BookingDates();
	public static int newlyCreatedBookingId;
	@Given("User access the create booking url")
	public void user_access_the_create_booking_url() throws IOException {
		RestAssured.baseURI=ReadProperties.getBaseUrl("baseUrl");
	}
 
	@When("User provides below  details")
	public void user_provides_below_details(io.cucumber.datatable.DataTable dataTable) {
	 String totalPrice=dataTable.row(1).get(2);
     String depositPaid=dataTable.row(1).get(3);
	 cb.setFirstname(dataTable.row(1).get(0));
	 cb.setLastname(dataTable.row(1).get(1));
	 cb.setTotalprice(Integer.parseInt(totalPrice));
	 cb.setDepositpaid(Boolean.parseBoolean(depositPaid)); 
	 cb.setAdditionalneeds(dataTable.row(1).get(4));
	 
	}
	@When("User also provides bookingdates Details")
	public void user_also_provides_bookingdates_details(io.cucumber.datatable.DataTable dataTable) {
	 bd.setCheckin(dataTable.row(1).get(0));   
	 bd.setCheckout(dataTable.row(1).get(1)); 
	 cb.setBookingdates(bd);
		response=given().header("Content-Type","application/json").body(cb).log().all()
				   .when().post("/booking").then().log().all().extract().response();
	}

	@Then("Verify the booking details")
	public void verify_the_booking_details() {
		JsonPath js=response.getBody().jsonPath();
		newlyCreatedBookingId=js.getInt("bookingid");
		assertNotNull(js.getInt("bookingid"));
		assertEquals("Tom",js.getString("booking.firstname"));
		assertEquals("Hank",js.getString("booking.lastname"));
		assertEquals(111,js.getInt("booking.totalprice"));
		assertEquals(true,js.getBoolean("booking.depositpaid"));
		assertEquals("Breakfast",js.getString("booking.additionalneeds"));
		assertEquals("2018-01-01",js.getString("booking.bookingdates.checkin"));
		assertEquals("2019-01-01",js.getString("booking.bookingdates.checkout"));
	}

	@Then("Verify the response code should be {int}")
	public void verify_the_response_code_should_be(int int1) {
		assertEquals(int1, response.getStatusCode());
	}

}
