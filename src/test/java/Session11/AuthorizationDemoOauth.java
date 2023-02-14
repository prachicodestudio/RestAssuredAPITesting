package Session11;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AuthorizationDemoOauth {

	//https://api-m.sandbox.paypal.com/v1/oauth2/token
	
	static String accessToken;
	@Test
	public void GetAccessToken()
	{
		
		String clientId = "Af6ZaIMAoqOuIxoWdFXu0-DagBIO-16R84vyhxBkAIGynL8IIuTrue3qP3wAOmAZ52qSV3I240SsXkoP";
		String clientSecret = "EBWh5kucLCxYHILbnQiAHeG6fpz4cFZ1YH4mNXjlGo92BdZkSTr0iLZW_nrSIvKYylpaQ26oVtaJAQd6";
		
		//create request Specification 
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify URL
		requestSpec.baseUri("https://api-m.sandbox.paypal.com");
		requestSpec.basePath("/v1/oauth2/token");
		
		//Basic authorization
		Response response = requestSpec.auth().preemptive().basic(clientId, clientSecret).param("grant_type", "client_credentials").post();
		
		response.prettyPrint();
		
		
		//print status code & status line
		System.out.println("Response code:" + response.statusCode() );
		System.out.println("status line:" + response.statusLine() );
		
		//validate repsonse code
		Assert.assertEquals(response.statusCode(), 200,"check for response code");
		
		//get access token from response body.
		
		accessToken= response.getBody().path("access_token");
		
		System.out.println("access token:" + accessToken);
		
	}
	
	@Test(dependsOnMethods ="GetAccessToken")
	public void ListInvoice()
	{
		//page=3&page_size=4&total_count_required=true
		Response res= RestAssured.given().auth().oauth2(accessToken).queryParam("page", "3")
		 .queryParam("page_size", "4")
		 .queryParam("total_count_required", "true")
		 .get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices");
		
		System.out.println("\n------------------LIST INVOICE------------------------");
		
		res.prettyPrint();
		//print status code & status line
				System.out.println("Response code:" + res.statusCode() );
				System.out.println("status line:" + res.statusLine() );
		
				//validate repsonse code
				Assert.assertEquals(res.statusCode(), 200,"check for response code");
				
		
	}
	
	
	
}
