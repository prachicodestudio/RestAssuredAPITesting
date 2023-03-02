package session19;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class OwnAPITesting {


	@BeforeClass
	public void setupDefault()
	{
		//create request specification
		RequestSpecification requestSpec = RestAssured.given();

		//specify url
		requestSpec.baseUri("http://localhost:3000");
		requestSpec.basePath("/users");

		RestAssured.requestSpecification = requestSpec;
	}

	@Test
	public void readUserData()
	{

		//perform get request

		Response response = RestAssured.get();

		//print response body

		System.out.println("ResponseBody of read User");
		response.prettyPrint();

		//validate status code
		Assert.assertEquals(response.statusCode(), 200,"Check for status code.");


	}

	@Test(enabled=false)
	public void CreateUser()
	{
		//perform post request

		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Anil");
		jsonData.put("age", 35);


		//perform post request
		Response response=	RestAssured.given().
				header("Content-type","application/json").
				contentType(ContentType.JSON).
				body(jsonData.toJSONString()).post();

		//print response body
		System.out.println("ResponseBody of create User");
		response.prettyPrint();

		//validate status code
		Assert.assertEquals(response.statusCode(), 201,"Check for status code.");


	}

	@Test(enabled=false)
	public void UpdateUser()
	{
		//perform post request

		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Anil");
		jsonData.put("age", 40);


		//perform put request
		Response response=	RestAssured.given().
				header("Content-type","application/json").
				contentType(ContentType.JSON).
				body(jsonData.toJSONString()).put("/3");

		//print response body



		System.out.println("ResponseBody of Update User");
		response.prettyPrint();

		//validate status code
		Assert.assertEquals(response.statusCode(), 200,"Check for status code.");


	}

	@Test
	public void DeleteUser()
	{
		
		//perform delete request
		Response response=	RestAssured.delete("/3");

		//print response body
		System.out.println("ResponseBody of Delet User");
		response.prettyPrint();

		//validate status code
		Assert.assertEquals(response.statusCode(), 200,"Check for status code.");


	}
}
