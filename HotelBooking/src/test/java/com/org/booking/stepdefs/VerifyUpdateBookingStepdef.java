package com.org.booking.stepdefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import com.org.booking.pojo.BookingDates;
import com.org.booking.pojo.CreateBookingPojo;
import com.org.booking.utils.GenerateToken;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
public class VerifyUpdateBookingStepdef {	
	
	
	CreateBookingPojo cb =new CreateBookingPojo();
	BookingDates bd=new BookingDates();
    public static String tokenValue;
	String response;
	int statusCode;
	@Before
	public void getToken() throws IOException
	{
		
		tokenValue=GenerateToken.getToken();
		
	}
	@When("User calls the update booking API with Id and below details updation")
	public void user_calls_the_update_booking_api_with_id_and_below_details_updation(io.cucumber.datatable.DataTable dataTable) {
	   	String totalPrice=dataTable.row(1).get(2);
	     String depositPaid=dataTable.row(1).get(3);
		 cb.setFirstname(dataTable.row(1).get(0));
		 cb.setLastname(dataTable.row(1).get(1));
		 cb.setTotalprice(Integer.parseInt(totalPrice));
		 cb.setDepositpaid(Boolean.parseBoolean(depositPaid)); 
		 cb.setAdditionalneeds(dataTable.row(1).get(4));

	}
	@When("User also updates below bookingdates Details")
	public void user_also_updates_below_bookingdates_details(io.cucumber.datatable.DataTable dataTable) {
	
		 bd.setCheckin(dataTable.row(1).get(0));   
		 bd.setCheckout(dataTable.row(1).get(1)); 
		 cb.setBookingdates(bd);
		response=given().contentType(ContentType.JSON).accept("application/json")
				 .header("Cookie","token="+tokenValue).body(cb).pathParam("id",VerifyCreateAPIStepdef.newlyCreatedBookingId).log().all()
				   .when().patch("/booking/{id}").then().log().all().extract().body().asPrettyString();
		
		statusCode=given().contentType(ContentType.JSON).accept("application/json")
				 .header("Cookie","token="+tokenValue).body(cb).pathParam("id",VerifyCreateAPIStepdef.newlyCreatedBookingId).log().all()
				   .when().patch("/booking/{id}").then().log().all().extract().statusCode();
		
	}

	@Then("Verify the booking updation details")
	public void verify_the_booking_updation_details() {	
		  JsonPath js=new JsonPath(response);
		  int totalPrice =js.getInt("totalprice"); 
		  assertEquals("Peter",js.getString("firstname"));
		  assertEquals("Parker",js.getString("lastname"));
		  assertEquals(140,totalPrice);
		  assertEquals(true,js.getBoolean("depositpaid"));
		  assertEquals("Dinner",js.getString("additionalneeds"));
	}
	@Then("Verify the update bookingId API response code should be {int}")
	public void verify_the_update_bookingId_APIresponse_code_should_be(int int1) {
		assertEquals(int1,statusCode);
	}

}
