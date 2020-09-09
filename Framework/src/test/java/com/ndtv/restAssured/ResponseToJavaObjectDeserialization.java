package com.ndtv.restAssured;

import org.testng.annotations.Test;

import com.ndtv.pojo.WeatherJsonResponse;
import com.ndtv.propertiesFile.PropertyFile;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ResponseToJavaObjectDeserialization {
	@Test
	public void deserialization() {
		System.out.println();

		RestAssured.baseURI = PropertyFile.propKey("URI");
		RequestSpecification httpRequest = RestAssured.given().queryParam("q", PropertyFile.propKey("CityName"))
				.queryParam("appid", PropertyFile.propKey("AccessKey"));
		Response response = httpRequest.request(Method.GET);
		ResponseBody body = response.getBody();
		WeatherJsonResponse responseBody = body.as(WeatherJsonResponse.class);

		//System.out.println(responseBody.getTimezone());
	}
}
