package session18;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class FileUploadDemo {

	
	@Test(enabled=false)
	public void uploadFile()
	{
		//Create file file object
		
		File testFileUpload = new File("C:\\Users\\ASUS\\Desktop\\testFileUpload.txt");
		File testFileUpload2 = new File("C:\\Users\\ASUS\\Desktop\\testFileUpload2.txt");

		//create Request Specification
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify URL
		requestSpec.baseUri("http://httpbin.org/post");
		
		requestSpec.multiPart("files",testFileUpload);
		requestSpec.multiPart("files",testFileUpload2);
		
		requestSpec.contentType("multipart/form-data");
		
		//perform post request
		Response response = requestSpec.post();
		
		//print response body
		response.prettyPrint();
		
		//validate status code
		Assert.assertEquals(response.statusCode(), 200,"Check for status code");
			
		
	
	}
	
	@Test
	public void uploadImage()
	{
		//https://petstore.swagger.io/v2/pet/1/uploadImage
		
		//Create file file object
		
				File testFileUpload = new File("C:\\Users\\ASUS\\Desktop\\rose.jpg");

				//create Request Specification
				RequestSpecification requestSpec = RestAssured.given();
				
				//specify URL
				requestSpec.baseUri("https://petstore.swagger.io/v2/pet/1/uploadImage");
				
				requestSpec.multiPart("file",testFileUpload);
				
				requestSpec.contentType("multipart/form-data");
				
				//perform post request
				Response response = requestSpec.post();
				
				//print response body
				response.prettyPrint();
				
				//validate status code
				Assert.assertEquals(response.statusCode(), 200,"Check for status code");
					
				
	}
}
