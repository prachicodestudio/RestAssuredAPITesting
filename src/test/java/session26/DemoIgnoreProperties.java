package session26;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import session26.Employee;

public class DemoIgnoreProperties {

	@Test
	public void test1() throws JsonMappingException, JsonProcessingException
	{
		String payload = "{\r\n"
				+ "  \"firstname\" : \"Suresh\",\r\n"
				+ "  \"lastname\" : \"Mehra\",\r\n"
				+ "  \"gender\" : \"Male\",\r\n"
				+ "  \"age\" : 35,\r\n"
				+ "  \"salary\" : 10000.0,\r\n"
				+ "  \"married\" : true,\r\n"
				+ "  \"fullname\" : \"Suresh Mehra\"\r\n"
				+ "}\r\n"
				+ "";
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		//Configuration to ignore unknown properties of POJO class
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		
		//convert JSON String (employeeJSON) to Class object (Employee)
		
		Employee emp2 = objectMapper.readValue(payload, Employee.class);
		
		System.out.println("-----------Print after JSON Object to Class Object------------");
		System.out.println("FirstName:"+ emp2.getFirstname());
		System.out.println("LastName:"+ emp2.getLastname());
		System.out.println("Gender:"+ emp2.getGender());
		System.out.println("Age:"+ emp2.getAge());
		System.out.println("Salary:"+ emp2.getSalary());
		System.out.println("Is Married:"+ emp2.isMarried());
		
	}
}
