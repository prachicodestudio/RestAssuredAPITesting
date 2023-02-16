package Session07;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;


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


		//query details from request specification

		QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);

		//get base URI
		String retrieveBaseURI = queryRequest.getBaseUri();
		System.out.println("Base URI:" + retrieveBaseURI);

		//get base PATH
		String retrieveBasePath = queryRequest.getBasePath();
		System.out.println("Base Path:" + retrieveBasePath);

		//get request Headers

		Headers allHeaders = queryRequest.getHeaders();
		System.out.println("\n----------------REQUEST HEADER---------------------\n");
		for(Header h:allHeaders)
		{
			System.out.println("Header name:" + h.getName() + "\tHeader value:" + h.getValue());
		}





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
