package session10;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationDemo {

	//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

	@Test
	public void GetWeatherDataByCity()
	{
		//create request specification
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://api.openweathermap.org");
		requestSpec.basePath("/data/2.5/weather");
		requestSpec.queryParam("q", "delhi").queryParam("appid", "ed9997485c5aebf644a45kef046becfceb6e6");
		Response response = requestSpec.get();

		//validate status code 
		Assert.assertEquals(response.statusCode()/*actual*/, 200/*expected*/,"check for status code");

		//print status line & response boy
		System.out.println("Responsne status line:" + response.statusLine());
		System.out.println("Response body:" + response.body().asString());

	}
}
