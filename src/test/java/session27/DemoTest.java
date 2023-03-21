package session27;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoTest {

	@Test
	public void test1()
	{
		
		//https://run.mocky.io/v3/5052e820-5e78-407d-a2ce-7bdd4cb73e06
		
		//create request specification
		
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify url
		requestSpec.baseUri("https://run.mocky.io/v3/5052e820-5e78-407d-a2ce-7bdd4cb73e06");
		
		//perform get request
		Response response= requestSpec.get();
		
		//print response body
		response.prettyPrint();
		
		
		//validate status code to be 200
		
		Assert.assertEquals(response.statusCode(), 200,"check for status code to be 200");
		
	}
	
	
	@Test
	public void test2()
	{
		
		//https://run.mocky.io/v3/5052e820-5e78-407d-a2ce-7bdd4cb73e06
		
		//create request specification
		
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify url
		requestSpec.baseUri("https://run.mocky.io/v3/5052e820-5e78-407d-a2ce-7bdd4cb73e06");
		
		//perform get request
		EmpPojoClass emp = requestSpec.get().as(EmpPojoClass.class);
		
		System.out.println("-----------Print after JSON Object to Class Object------------");
		System.out.println("FirstName:"+ emp.getFirstname());
		System.out.println("LastName:"+ emp.getLastname());
		System.out.println("Gender:"+ emp.getGender());
		System.out.println("Age:"+ emp.getAge());
		System.out.println("Salary:"+ emp.getSalary());
		System.out.println("Is Married:"+ emp.isMarried());
		
		System.out.println("Hobbies:");
		
		String [] hobbiesStr = emp.getHobbies();
		
		for(int i=0; i<hobbiesStr.length;i++)
		{
			System.out.println(hobbiesStr[i]);
		}
		

		// using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String,String> entry : emp.getFamilyMembers().entrySet()) 
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
		
	}
}
