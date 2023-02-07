package Session07;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class QueryParameterDemo {

	@Test
	public void filterData()
	{
		//Get Request Specification for the given request
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify url
		requestSpec.baseUri("https://reqres.in");
		requestSpec.basePath("/api/users");
		requestSpec.queryParam("page", 2).queryParam("id", 10);
		
		//perform get request
		Response response =requestSpec.get();
		
		//read response body
		 String responseBodyString = response.getBody().asString();
		 
		 //print response body
		 System.out.println("Response body:" + responseBodyString );
	
		 //get json path view of response body
		JsonPath jsonPathView = response.jsonPath();
		
		//get first_name
		//x.data.first_name
		String firstname = jsonPathView.get("data.first_name");
		
		Assert.assertEquals(firstname,"prachi","Verify firstname.");
		
		
	}
}
