package com.ndtv.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.ndtv.keywords.CommonFunctions;
import com.ndtv.pojo.WeatherJsonResponse;

public class TemperatureUtility extends CommonFunctions{
	
	public double kelvinValue;
	public static String[] tempDistribution=null;
	public static Double humidityInNumber=null;
	public static Double tempInKelvinWeb=null;
	public static Double humidity=null;
	public static Double tempInKelvinAPI=null;
	public static Map<String,Double> hmap=null;
	public static Double minimumTemp=null;
	public static Double maximumTemp=null;
	public static SoftAssert softAssert=new SoftAssert();

	public static Double CelsiusToKelvin(Double celsiusValueInString) {
		Double kelvinValue = celsiusValueInString + 273.15;
		DecimalFormat df = new DecimalFormat("#.##");      
		kelvinValue = Double.valueOf(df.format(kelvinValue));
		return kelvinValue;
	}

	public Map<String,Double> extractTempFromWebApp(ArrayList<String> tempSummary) throws InterruptedException {
		String temp = tempSummary.get(3).toString();
		String[] humidity=tempSummary.get(2).toString().trim().split(":");
		humidityInNumber=Double.parseDouble(humidity[1].substring(1,3));
		tempDistribution = temp.split(":");
		tempInKelvinWeb=Double.parseDouble(tempDistribution[1].substring(1, 3));
		hmap=new HashMap<String,Double>();
		hmap.put("humidityInNumber", Double.parseDouble(humidity[1].substring(1,3)));
		hmap.put("tempInKelvin", CelsiusToKelvin(tempInKelvinWeb));
		
		return hmap;
	}
	
	public void extractTempFromAPI(WeatherJsonResponse response)
	{
		tempInKelvinAPI=response.getMain().getTemp();
		listener.getTest().log(Status.PASS,"Temperature in JSON response : " + tempInKelvinAPI);
		humidity=response.getMain().getHumidity();	
		listener.getTest().log(Status.PASS,"Humidity in JSON response : " + humidity);
		minimumTemp=response.getMain().getTemp_min();
		listener.getTest().log(Status.PASS,"Minimum temperature in JSON response : " + minimumTemp);
		maximumTemp=response.getMain().getTemp_max();	
		listener.getTest().log(Status.PASS,"Maximum temperature in JSON response : " + maximumTemp);
	}
	
	public void compareTemperature()
	{
		if(tempInKelvinAPI==hmap.get("tempInKelvin"))
		{
			listener.getTest().log(Status.PASS,"Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
			softAssert.assertEquals(tempInKelvinAPI==(hmap.get("tempInKelvin"))," Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
		}
		else
		{
			listener.getTest().log(Status.FAIL,"Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
			softAssert.fail("Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
		}
		
		if(humidity==hmap.get("humidityInNumber"))
		{
			listener.getTest().log(Status.PASS,"Humidity from API response : "+humidity+" and humidity from WebUI is "+hmap.get("humidityInNumber"));
			softAssert.assertEquals(humidity== hmap.get("humidityInNumber"),"Humidity from API response : "+humidity+" and humidity from WebUI is "+hmap.get("humidityInNumber"));
		}
		else
		{
			listener.getTest().log(Status.FAIL,"Humidity from API response : "+humidity+" and humidity from WebUI is "+hmap.get("humidityInNumber"));
			softAssert.fail("Humidity from API response : "+humidity+" and humidity from WebUI is "+hmap.get("humidityInNumber"));
		}
		softAssert.assertAll();
	}
}
