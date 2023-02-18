package session13;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class QueryRequestSpecification {

	//https://reqres.in/api/users

	@Test
	public void createUser()
	{
		
		//create json data
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Rekha");
		jsonData.put("job", "QA");


		//Create request specification
		RequestSpecification reqSpec = RestAssured.given();

		//specify url
		reqSpec.baseUri("https://reqres.in").
		basePath("/api/users").
		contentType(ContentType.JSON).
		body(jsonData.toJSONString()).header("header1","header1value");


		//query details from request specification

		QueryableRequestSpecification queryRequest = SpecificationQuerier.query(reqSpec);

		//get base URI
		String retrieveBaseURI = queryRequest.getBaseUri();
		System.out.println("Base URI:" + retrieveBaseURI);

		//get base PATH
		String retrieveBasePath = queryRequest.getBasePath();
		System.out.println("Base Path:" + retrieveBasePath);


		//get request body
		String retrieveRequestBody = queryRequest.getBody();
		System.out.println("Body :" + retrieveRequestBody);
		
		//get request Headers
		
		Headers allHeaders = queryRequest.getHeaders();
		System.out.println("\n----------------REQUEST HEADER---------------------\n");
		for(Header h:allHeaders)
		{
			System.out.println("Header name:" + h.getName() + "\tHeader value:" + h.getValue());
		}
		
		
		
		

	}
}
