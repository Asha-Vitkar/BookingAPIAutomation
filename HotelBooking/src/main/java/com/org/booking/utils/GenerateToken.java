package com.org.booking.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

public class GenerateToken {
	
	public static String getToken() throws IOException
	{
		String response;
		HashMap<String,String> hm=new HashMap<String,String>();
		RestAssured.baseURI=ReadProperties.getBaseUrl("baseUrl");
		hm=ReadProperties.getCredentials("username", "password");
		response=given().log().all().contentType("application/json").body(hm)
		.when().post("/auth").then().log().all().extract().asString();
		JsonPath js=new JsonPath(response);
        String token=js.getString("token");
		return token;
	}

}
