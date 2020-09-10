package com.ndtv.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.ndtv.keywords.CommonFunctions;
import com.ndtv.pojo.WeatherJsonResponse;
import com.ndtv.propertiesFile.PropertyFile;
import com.ndtv.util.TemperatureUtility;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class APITestCases {
	public WeatherJsonResponse responseBody=null;
	
	@Parameters({"type"})
	public static void switchToAPI(String type)
	{
		if(type.equalsIgnoreCase("API"))
		{
			PropertyFile.prop.setProperty("API", "True");
		}
	}
	
	@Test
	public void weatherJsonResponse() {
		RestAssured.baseURI = PropertyFile.propKey("URI");
		RequestSpecification httpRequest = RestAssured.given().queryParam("q", PropertyFile.propKey("CityName"))
				.queryParam("appid", PropertyFile.propKey("AccessKey"));
		Response response = httpRequest.request(Method.GET);
		ResponseBody body = response.getBody();
		responseBody = body.as(WeatherJsonResponse.class);
		
		if(body!=null)
		{
		CommonFunctions.testStatus(true,response.getBody().asString());
		}
}
	
	@Test(dependsOnMethods="weatherJsonResponse")
	public void extractResponseBody()
	{
		TemperatureUtility temputil=new TemperatureUtility();
		temputil.extractTempFromAPI(responseBody);
		//temputil.extractTempFromAPI(responseBody);
	}

}
