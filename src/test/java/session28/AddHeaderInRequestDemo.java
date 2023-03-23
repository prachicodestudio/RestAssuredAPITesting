


package session28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddHeaderInRequestDemo {
	
	@Test
	public void testMethod1()
	{
		Map<String, String> requestHeader = new HashMap<>();
		requestHeader.put("Header1", "Value1");
		requestHeader.put("Header2", "Value2");

		
		Header header1 = new Header("Header1","Value1");
		Header header2 = new Header("Header2","Value2");
		Header header3 = new Header("Header3","Value3");
		
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(header1);
		headerList.add(header2);
		headerList.add(header3);

		
		Headers headersObj = new Headers(headerList);

		
		
	/*	RequestSpecification requestSpec = RestAssured.given();
		
		
		//add header
		//requestSpec.header(requestHeaderObj);
		requestSpec.headers(headersObj);
		requestSpec.log().headers();
		//specify url
		requestSpec.baseUri("https://reqres.in/api/users?page=1");
		
		//perform get request
		Response resopnse = requestSpec.get();
		
		//validate response code
		Assert.assertEquals(resopnse.statusCode(), 200);
		
		//https://reqres.in/api/users?page=1*/
		
		//BDD Style (Given, when ,then)
		
		RestAssured
		.given()
			.headers(headersObj)
			.log().headers()
		.when()
			.get("https://reqres.in/api/users?page=1")
		.then()
			.log().body();
			
		
		
	}

}
