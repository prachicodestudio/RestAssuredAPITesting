package session04;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class CheckForValidResponse {
//https://reqres.in/api/users/2
	
	
	@Test(enabled = false )
	public void GetSingleUser()
	{
		//specify base URL
		baseURI = "https://reqres.in/api/users/19";
		
		//Get Request specifcation of the request
		RequestSpecification requestSpec =  given();
	
		//call get method
		Response response = requestSpec.get();
		
		
		//gets response code
		int statusCode = response.getStatusCode();
		
		//validate actual status code with expected
		
		//Assert.assertEquals(statusCode/*actual status code*/,200/*expected status code*/,"incorrect status code received");		
		
		String statusLine = response.getStatusLine();
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","incorrect status line returned");
		
		
	}
	
	@Test(enabled=false)
	public void GetSingleUserUsingValidatableResponse()
	{
		//specify base URL
		baseURI = "https://reqres.in/api/users/19";
		
		//Get Request specifcation of the request
		RequestSpecification requestSpec =  given();
	
		//call get method
		Response response = requestSpec.get();
		
		
		ValidatableResponse validateRes = response.then();
		
		//status code 
	//	validateRes.statusCode(200);
		
	//	System.out.println("second validation");
		//status line
		validateRes.statusLine("HTTP/1.1 200 OK");
		
		
	}
	
	@Test
	public void GetSingleUser_BDDStyle()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users/2")
		
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK");
	}
	
}
