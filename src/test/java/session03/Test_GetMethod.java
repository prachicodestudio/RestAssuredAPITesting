package session03;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Test_GetMethod {

	@Test
	public void test01()
	{
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("Response code:" + response.getStatusCode());
		System.out.println("Response body:" + response.getBody().asString());
		System.out.println("Response Time:" + response.getTime());
		System.out.println("Response Header:" + response.getHeader("Content-Type"));
		
		//validate status code
		//expected status code =200
		
		int expectedStatusCode = 200;
		int ActualStatuscode = response.getStatusCode();
		
		Assert.assertEquals(expectedStatusCode, ActualStatuscode);
		

		
	}
	
	@Test
	public void test02()
	{
		//given , when, then
		baseURI="https://reqres.in/api/users";
		given()
		.queryParam("page", "2")
		.when().get()
		.then().statusCode(208);
		
	}
	
}
