package session17;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSONObjectUsingJacksonAPI {

	@Test
	public void CreateUser()
	{
		//https://reqres.in/api/users

		/*{
   "firstName":"Prerna",
   "lastName":"Gupta",
   "age": 28,
   "salary": 10000.56,
   "IsMarried":true,
   "TechSkill":{
		"Programming language":"Java",
		"WebAutomation":"Selenium",
		"API testing" : "Rest Assured"
             }

 }*/

		//Create ObjectMapper class instance

		ObjectMapper objectMapper = new ObjectMapper();

		//Create object node i.e json node 
		ObjectNode userDetails = objectMapper.createObjectNode();

		userDetails.put("firstNme", "Prerna");
		userDetails.put("lastName", "Gupta");
		userDetails.put("age", 28);
		userDetails.put("salary", 10000.56);
		userDetails.put("IsMarried", false);
		userDetails.set("Hobbies",objectMapper.convertValue(Arrays.asList("Cooking","Music"),JsonNode.class));

		ObjectNode techSkill = objectMapper.createObjectNode();
		techSkill.put("Programming language", "Java");
		techSkill.put("WebAutomation", "Selenium");
		techSkill.put("API testing", "Rest Assured");

		userDetails.set("TechSkill", techSkill);

		//print userDetails JSON Object

		try {
			String UserDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);

			System.out.println("Created JSON Node is:" + UserDetailsAsString);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Retrieve field value
		String firstName = userDetails.get("firstNme").asText();
		System.out.println("Value of firstname field is :" + firstName);


		Boolean isMarried = userDetails.get("IsMarried").asBoolean();
		System.out.println("Value of Is Married field is :" + isMarried);

		//retried field value of webAutomation
		String WebAutomationValue = userDetails.get("TechSkill").get("WebAutomation").asText();
		System.out.println("Value of WebAutomation is :" + WebAutomationValue);

		System.out.println("---------------Print all fields name---------------\n");

		Iterator <String> fieldNameIterator = userDetails.fieldNames();

		while(fieldNameIterator.hasNext())
		{
			System.out.println(fieldNameIterator.next());
		}

		System.out.println("---------------Print all fields values---------------\n");

		Iterator <JsonNode> fieldValuesterator = userDetails.elements();

		while(fieldValuesterator.hasNext())
		{
			System.out.println(fieldValuesterator.next());
		}


		System.out.println("---------------Print all field name and value both (key, value pair)---------------\n");

		Iterator <Entry<String,JsonNode>> KeyValueEntries = userDetails.fields();

		while(KeyValueEntries.hasNext())
		{
			Entry<String,JsonNode> node= KeyValueEntries.next();				

			System.out.println("Key:" + node.getKey() + "," + "Value:" + node.getValue() );
		}

		//Remove field "first name" from json object or objectnode

		String removedValued = userDetails.remove("firstNme").asText();
		System.out.println("Removed firstname value is: " + removedValued);



		//update json object or object node
		userDetails.put("lastName", "Sharma");

		techSkill.put("Programming language", "C#");
		userDetails.set("TechSkill", techSkill);



		//print userDetails JSON Object

		try {
			String UserDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);

			System.out.println("JSON Node After Remove method:" + UserDetailsAsString);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		
		//specify URL
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(userDetails);

		//perform post request
		Response response = reqSpec.post();

		System.out.println("-------------Print Http response body-----------------------------");
		response.prettyPrint();

		//Validate the status code
		Assert.assertEquals(response.statusCode(), 201,"Check for status code.");

	}

}
