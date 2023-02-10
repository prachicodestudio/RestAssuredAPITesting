package session09;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationDemo {


	@Test
	public void BearerToken()
	{
		//https://gorest.co.in/public/v2/users
		//create requsest specification
		RequestSpecification requestSpec = RestAssured.given();
		
		requestSpec.baseUri("https://gorest.co.in");
		requestSpec.basePath("/public/v2/users");
		
	/*	{
			"name":"CodeStudio1",
			"gender":"Female",
			"email":"Code1@gmail.com",
			"status":"Active"
			}*/
		
		JSONObject payload = new JSONObject();
		payload.put("name", "prachigupta");
		payload.put("gender", "Female");
		payload.put("email", "prachigupta123@gmail.com");
		payload.put("status", "Active");
		
		String AuthToken = "Bearer 866218be3a275e793abe788f9a83a53da434004c5718abdc6215cd26d5fc6be2bhh";
	
		requestSpec.headers("Authorization", AuthToken).
		contentType(ContentType.JSON).
		body(payload.toJSONString());
		
		//perform post request
		Response response = requestSpec.post();
		

		//validate status code 
		Assert.assertEquals(response.statusCode()/*actual*/, 201/*expected*/,"check for status code");
	
		//print status line & response boy
		System.out.println("Responsne status line:" + response.statusLine());
		System.out.println("Response body:" + response.body().asString());
		
		
	}
}
