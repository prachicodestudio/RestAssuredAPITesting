package session16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class JSONArrayDemo {
	
	@Test(enabled= false)
	public void createUserUsingJSONArray()
	{
		//create JSONOBjects for users
		
		/*{
 "firstName":"Prachi",
   "lastName":"Gupta",
   "age": 28,
   "salary": 10000.56,
}*/
		JSONObject user1 = new JSONObject();
		user1.put("firstName","Prachi");
		user1.put("lastName","Gupta");
		user1.put("age",28);
		user1.put("salary",10000.56);
		
		JSONObject user2 = new JSONObject();
		user2.put("firstName","Prerna");
		user2.put("lastName","Gupta");
		user2.put("age",28);
		user2.put("salary",10000.56);
		
		JSONObject user3 = new JSONObject();
		user3.put("firstName","Jatin");
		user3.put("lastName","Gupta");
		user3.put("age",28);
		user3.put("salary",10000.56);
		
		
		//add JSON Object to JSON Array
		JSONArray UsersPayLoad = new JSONArray();
		
		UsersPayLoad.add(user1);
		UsersPayLoad.add(user2);
		UsersPayLoad.add(user2);
		
		//create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		
		//specify URL
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(UsersPayLoad);
		
		//perform post request
		Response response = reqSpec.post();
		
		response.prettyPrint();
		
		//Validate the status code
		Assert.assertEquals(response.statusCode(), 201,"Check for status code.");
		


		
	}
	
	@Test
	public void CreateJSonArrayUsingList()
	{
		//create JSONOBjects for users
		
		/*{
 "firstName":"Prachi",
   "lastName":"Gupta",
   "age": 28,
   "salary": 10000.56,
}*/
		//JSONObject user1 = new JSONObject();
		Map<String, Object> user1 = new HashMap<String, Object>();

		user1.put("firstName","Prachi");
		user1.put("lastName","Gupta");
		user1.put("age",28);
		user1.put("salary",10000.56);
		
	//	JSONObject user2 = new JSONObject();
		Map<String, Object> user2 = new HashMap<String, Object>();
		user2.put("firstName","Prerna");
		user2.put("lastName","Gupta");
		user2.put("age",28);
		user2.put("salary",10000.56);
		
		//JSONObject user3 = new JSONObject();
		Map<String, Object> user3 = new HashMap<String, Object>();

		user3.put("firstName","Jatin");
		user3.put("lastName","Gupta");
		user3.put("age",28);
		user3.put("salary",10000.56);
		
		
		//create JSON Array using List
	//	JSONArray UsersPayLoad = new JSONArray();
		
		List<Map<String, Object>> jsonArrayListPayload = new ArrayList<>();
		
		jsonArrayListPayload.add(user1);
		jsonArrayListPayload.add(user2);
		jsonArrayListPayload.add(user2);
		
		//create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		
		//specify URL
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonArrayListPayload);
		
		//perform post request
		Response response = reqSpec.post();
		
		response.prettyPrint();
		
		//Validate the status code
		Assert.assertEquals(response.statusCode(), 201,"Check for status code.");
		


		
	}
}
