package Session21;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import session20.Employee;

public class JSONArrayPojoClassDemo {


	@Test
	public void createEmployeesJSONArray() throws JsonProcessingException
	{
		//create first employeeObject
		Employee emp1 = new Employee();

		emp1.setFirstName("Suresh");
		emp1.setLastname("Mehra");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000.56);


		//create second employeeObject
		Employee emp2 = new Employee();

		emp2.setFirstName("Ram");
		emp2.setLastname("Singh");
		emp2.setGender("Male");
		emp2.setAge(30);
		emp2.setSalary(34000.56);

		//create third employeeObject
		Employee emp3 = new Employee();

		emp3.setFirstName("Sita");
		emp3.setLastname("Gupta");
		emp3.setGender("Female");
		emp3.setAge(28);
		emp3.setSalary(39000.56);
		
		//Create List Of Employee
		
		List<Employee> listOfEmp = new ArrayList<Employee>();
		listOfEmp.add(emp1);
		listOfEmp.add(emp2);
		listOfEmp.add(emp3);
		
		//Convert employee class objects to json array payload as String
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonArrayPayload = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfEmp);
		
		System.out.println("employee class objects to json array payload");
		System.out.println(jsonArrayPayload);
		
		
		//create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		
		//specify URL
		reqSpec.baseUri("http://httpbin.org/post");
		
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonArrayPayload);
		
		//perform POST request
		Response response = reqSpec.post();
		System.out.println("----------ResponseBody----------------");
		
		response.prettyPrint();
		
		
		//verify the status code		
		Assert.assertEquals(response.statusCode(), 200,"check for status code.");
		
		
		
		//convert JsonArray to Employee class objects (Deserialization)
		
		ResponseBody responseBody = response.getBody();
		
		JsonPath jsonPathView = responseBody.jsonPath();
		
		List<Employee> allEmployees = jsonPathView.getList("json", Employee.class);

	
		System.out.println("----------Emoployee objects in ResponseBody----------------");

		for(Employee emp:allEmployees)
		{
			System.out.println(emp.getFirstName()+ " " + emp.getLastname());
		}
		
		
	
	}
}
