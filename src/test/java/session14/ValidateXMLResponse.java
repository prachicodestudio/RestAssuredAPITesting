package session14;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.hamcrest.Matchers;


public class ValidateXMLResponse {

	@Test
	public void GetTravellersData()
	{
		//create request Specification
				RequestSpecification requestSpec = RestAssured.given();
				
				//http://restapi.adequateshop.com/api/Traveler?page=1
				//specify url
				requestSpec.baseUri("http://restapi.adequateshop.com");
				requestSpec.basePath("/api/Traveler");
				
				//add query parameter
				requestSpec.queryParam("page", "1");
				
				//specify header
				requestSpec.header("accept","application/xml");
				
				//perform get request 
				Response response = requestSpec.get();
				
				response.prettyPrint();
				
				
				//Approach 1
			//	response.then().body("TravelerinformationResponse.travelers.Travelerinformation[2].name", Matchers.equalTo("vano"));

				
				//Approach 2
				XmlPath objXmlPath = new XmlPath(response.asString());
				
				String travellerName = objXmlPath.get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
				Assert.assertEquals(travellerName, "Developer", "check for traveller name.");
				
				//Verify total travelers to be 10
				
				List<String> listOfTravellers = objXmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
				
				int totalTravelerCount = listOfTravellers.size();
				
				Assert.assertEquals(totalTravelerCount, 10,"check for total no. of traveller on page-1");
	
				
				//verify for name vano in travellers list
				List<String> listOfTravellersName = objXmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

				//print all the names in the travellers list
				
				boolean found = false;
				for (String traveller :listOfTravellersName )
				{
					System.out.println(traveller);
					
					if(traveller.equals("vano"))
					{
						found = true;
						break;
					}
					
					
				}
				
				Assert.assertEquals(found, true);
		
	}
	
	
	@Test(enabled=false)
	public void AddPet()
	{
		
		String xmlRequestBody="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<Pet>\r\n"
				+ "	<id>0</id>\r\n"
				+ "	<Category>\r\n"
				+ "		<id>0</id>\r\n"
				+ "		<name>string</name>\r\n"
				+ "	</Category>\r\n"
				+ "	<name>doggie</name>\r\n"
				+ "	<photoUrls>\r\n"
				+ "		<photoUrl>string</photoUrl>\r\n"
				+ "	</photoUrls>\r\n"
				+ "	<tags>\r\n"
				+ "		<Tag>\r\n"
				+ "			<id>0</id>\r\n"
				+ "			<name>string</name>\r\n"
				+ "		</Tag>\r\n"
				+ "	</tags>\r\n"
				+ "	<status>available</status>\r\n"
				+ "</Pet>";
		
		String jsonData = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		//create request Specification
		RequestSpecification requestSpec = RestAssured.given();
		
		//https://petstore.swagger.io/v2/pet
		//specify url
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/pet");
		
		//specify header
		requestSpec.header("accept","application/xml");
		requestSpec.header("Content-Type","application/xml");
		requestSpec.body(xmlRequestBody);
		//perform post request 
		Response response = requestSpec.post();
		
		response.prettyPrint();
		
		//verify status code
		Assert.assertEquals(response.statusCode()/*actual status code*/,/*expected status code*/200,"Check for status code");
		
		response.then().body("Pet.name", Matchers.equalTo("doggie"));
		
		
	}
}
