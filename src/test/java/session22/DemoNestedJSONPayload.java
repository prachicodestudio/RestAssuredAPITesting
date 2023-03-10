package session22;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoNestedJSONPayload {

	@Test
	public void createUser() throws JsonProcessingException
	{
		/*
		 * 
{
  "firstName": "Suresh",
  "lastName": "Mehra",
  "gender": "Male",
  "age": 35,
  "salary:10000.56,
  "Address":{
	"Street": "Park Avenue",
	"City": "Vijaywada",
	"State": "Andhra Pradesh",
	"pin code":530012
        }
}
*/
		
		
		EmployeePojoClass emp1 = new EmployeePojoClass();
		emp1.setFirstname("Suresh");
		emp1.setLastname("Mehra");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000.56);
		
		EmployeeAddress emp1Address = new EmployeeAddress();
		emp1Address.setStreet("Park Avenue");
		emp1Address.setCity("vijaywada");
		emp1Address.setState("Andhra Pradesh");
		emp1Address.setPincode(530012);
		
		emp1.setAddress(emp1Address);
		
		//convert class object to json object as string
		
		ObjectMapper objetMapper = new ObjectMapper();
		
		String josnpayload = objetMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		
		
		RequestSpecification reqSpec = RestAssured.given();
		
		//specify url
		reqSpec.baseUri("http://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(josnpayload);
		
		//perform post request
		Response response = reqSpec.post();
		
		response.prettyPrint();
		
		
	//	System.out.println("jsonpayload : " + josnpayload);
		
	}
}
