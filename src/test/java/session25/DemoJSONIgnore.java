package session25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import session20.Employee;
import session25.employeePojoClass;

public class DemoJSONIgnore {

	@Test
	public void testMethod1() throws JsonProcessingException
	{
		//create payload
		employeePojoClass emp1 = new employeePojoClass();
		emp1.setFirstname("Suresh");
		emp1.setLastname("Mehra");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000);
		emp1.setMarried(true);
		emp1.setFullName("Suresh Mehra");
	
		//serialization : convert employee class object to json payload as string

		ObjectMapper objMapper = new ObjectMapper();

		String employeeJSON = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);

		System.out.println(employeeJSON);

		// Deserialization: JSON Payload String to Employee Class Object
		String payload = "{\r\n"
				+ "  \"firstname\" : \"Suresh\",\r\n"
				+ "  \"lastname\" : \"Mehra\",\r\n"
				+ "  \"gender\" : \"Male\",\r\n"
				+ "  \"age\" : 35,\r\n"
				+ "  \"salary\" : 10000.0,\r\n"
				+ "  \"fullName\" : \"Suresh Mehra\",\r\n"
				+ "  \"married\" : true\r\n"
				+ "}";
		

		
		employeePojoClass emp2 = objMapper.readValue(payload, employeePojoClass.class);
		
		System.out.println("-----------Print after JSON Object to Class Object------------");
		System.out.println("FirstName:"+ emp2.getFirstname());
		System.out.println("LastName:"+ emp2.getLastname());
		System.out.println("Gender:"+ emp2.getGender());
		System.out.println("Age:"+ emp2.getAge());
		System.out.println("Salary:"+ emp2.getSalary());
		System.out.println("Full Name:"+ emp2.getFullName());

		
	}
}
