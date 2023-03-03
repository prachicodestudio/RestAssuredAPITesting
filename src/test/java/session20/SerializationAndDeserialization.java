package session20;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SerializationAndDeserialization {

	
	@Test
	public void CreateJSONObjectFromEmployeeClassObject() throws JsonProcessingException
	{
		Employee emp1 = new Employee();
		emp1.setFirstName("Suresh");
		emp1.setLastname("Mehra");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000.00);
		
		//convert employee class object to json payload as string
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String employeeJSON = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		
		System.out.println(employeeJSON);
		
		//create Request Specification 
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("http://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(employeeJSON);
		
		//perform post request
		Response response = reqSpec.post();
		
		response.prettyPrint();
		
		//validate status code
		Assert.assertEquals(response.statusCode(), 200,"Check for status code");
		
		
		//convert JSON String (employeeJSON) to Class object (Employee)
		
		Employee emp2 = objMapper.readValue(employeeJSON, Employee.class);
		
		System.out.println("-----------Print after JSON Object to Class Object------------");
		System.out.println("FirstName:"+ emp2.getFirstName());
		System.out.println("LastName:"+ emp2.getLastname());
		System.out.println("Gender:"+ emp2.getGender());
		System.out.println("Age:"+ emp2.getAge());
		System.out.println("Salary:"+ emp2.getSalary());
		
		
	}
}
