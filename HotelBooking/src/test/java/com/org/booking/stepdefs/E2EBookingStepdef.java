package com.org.booking.stepdefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;

import com.org.booking.pojo.BookingDates;
import com.org.booking.pojo.CreateBookingPojo;
import com.org.booking.utils.ReadProperties;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class E2EBookingStepdef {
	
	Response response;
	Response getResponse;
	String updateResponse;
	Response deleteResponse;
	CreateBookingPojo cb =new CreateBookingPojo();
	BookingDates bd=new BookingDates();
	public static int e2eBookingId;
	public static String firstName;
	public static String lastName;
	int statuscode;
	@Before
	public void setBaseUrl() throws IOException
	{
		RestAssured.baseURI=ReadProperties.getBaseUrl("baseUrl");
	}
	
	@Given("User has below request details")
	public void user_has_below_request_details(io.cucumber.datatable.DataTable dataTable) {
		String totalPrice=dataTable.row(1).get(2);
	     String depositPaid=dataTable.row(1).get(3);
		 cb.setFirstname(dataTable.row(1).get(0));
		 cb.setLastname(dataTable.row(1).get(1));
		 cb.setTotalprice(Integer.parseInt(totalPrice));
		 cb.setDepositpaid(Boolean.parseBoolean(depositPaid)); 
		 cb.setAdditionalneeds(dataTable.row(1).get(4));
	}

	@When("User create a new booking by providing below booking details")
	public void user_create_a_new_booking_by_providing_below_booking_details(io.cucumber.datatable.DataTable dataTable) {
		bd.setCheckin(dataTable.row(1).get(0));   
	    bd.setCheckout(dataTable.row(1).get(1)); 
	    cb.setBookingdates(bd);
		response=given().header("Content-Type","application/json").body(cb).log().all()
					   .when().post("/booking").then().log().all().extract().response();
	}

	@Then("booking should be created with response code {int}")
	public void booking_should_be_created_with_response_code(int int1) {
		JsonPath js=new JsonPath(response.asString());
		e2eBookingId=js.getInt("bookingid");
		firstName=js.get("booking.firstname");
		lastName=js.get("booking.lastname");
		assertEquals(int1, response.getStatusCode());
	}
	@When("User call the Get booking api by its id")
	public void user_call_the_get_booking_api_by_its_id() {
		getResponse=given().log().all().header("accept","application/json").pathParam("id",e2eBookingId).when().get("/booking/{id}")
				.then().extract().response();
	}

	@Then("User should get booking details with response code {int}")
	public void user_should_get_booking_details_with_response_code(int int1) {
		JsonPath js=new JsonPath(getResponse.asString());
		assertEquals(firstName, js.get("firstname"));
		assertEquals(lastName, js.get("lastname"));
		assertEquals(int1, getResponse.getStatusCode());
	}
	
	@Given("User wants to update below request details")
	public void user_wants_to_update_below_request_details(io.cucumber.datatable.DataTable dataTable) {
		String totalPrice=dataTable.row(1).get(2);
	     String depositPaid=dataTable.row(1).get(3);
		 cb.setFirstname(dataTable.row(1).get(0));
		 cb.setLastname(dataTable.row(1).get(1));
		 cb.setTotalprice(Integer.parseInt(totalPrice));
		 cb.setDepositpaid(Boolean.parseBoolean(depositPaid)); 
		 cb.setAdditionalneeds(dataTable.row(1).get(4));
	}

	@When("User calls the update api with below bookingDate updation details")
	public void user_calls_the_update_api_with_below_booking_date_updation_details(io.cucumber.datatable.DataTable dataTable) {
		 bd.setCheckin(dataTable.row(1).get(0));   
		 bd.setCheckout(dataTable.row(1).get(1)); 
		 cb.setBookingdates(bd);
		
		 updateResponse=given().contentType(ContentType.JSON).accept("application/json")
				 .header("Cookie","token="+VerifyUpdateBookingStepdef.tokenValue).body(cb).pathParam("id",e2eBookingId).log().all()
				   .when().put("/booking/{id}").then().log().all().extract().asPrettyString();
		 statuscode=given().contentType(ContentType.JSON).accept("application/json")
				 .header("Cookie","token="+VerifyUpdateBookingStepdef.tokenValue).body(cb).pathParam("id",e2eBookingId).log().all()
				   .when().put("/booking/{id}").then().log().all().extract().statusCode();
	   
	}

	@Then("User should get updated booking details with response code {int}")
	public void User_should_get_updated_booking_details_with_response_code(int int1) {
		JsonPath js=new JsonPath(updateResponse);
		  int totalPrice =js.getInt("totalprice"); 
		  assertEquals("Dexter",js.getString("firstname"));
		  assertEquals("Morgan",js.getString("lastname"));
		  assertEquals(140,totalPrice);
		  assertEquals(true,js.getBoolean("depositpaid"));
		  assertEquals("Dinner",js.getString("additionalneeds"));
		  assertEquals("2018-02-02",js.getString("bookingdates.checkin"));
		  assertEquals("2019-02-02",js.getString("bookingdates.checkout"));
  		  assertEquals(int1,statuscode);
	}
	
	@When("User calls the delete booking api by its id")
	public void user_calls_the_delete_booking_api_by_its_id() {
		deleteResponse=given().contentType(ContentType.JSON).accept("application/json")
				 .header("Cookie","token="+VerifyUpdateBookingStepdef.tokenValue).pathParam("id",e2eBookingId).log().all()
				   .when().delete("/booking/{id}").then().log().all().extract().response();
	}

	@Then("User should get response code {int}")
	public void user_should_get_response_code(int int1) {
		assertEquals(int1,deleteResponse.getStatusCode());
	}

    
}
