package com.ndtv.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import com.google.common.base.Verify;
import com.ndtv.pojo.WeatherJsonResponse;

public class TemperatureUtility {
	
	public double kelvinValue;
	public static String[] tempDistribution=null;
	public static Double humidityInNumber=null;
	public static Double tempInKelvin=null;
	public static Double humidity=null;
	public static Double tempInKelvinAPI=null;
	public static Map<String,Double> hmap=null;

	public static Double CelsiusToKelvin(Double celsiusValueInString) {
		Double kelvinValue = celsiusValueInString + 273.15;
		return kelvinValue;
	}

	public Map<String,Double> extractTempFromWebApp(ArrayList<String> tempSummary) throws InterruptedException {
		String temp = tempSummary.get(3).toString();
		String[] humidity=tempSummary.get(2).toString().trim().split(":");
		humidityInNumber=Double.parseDouble(humidity[1].substring(1,3));
		tempDistribution = temp.split(":");
		tempInKelvin=Double.parseDouble(tempDistribution[1].substring(1, 3));
		hmap=new HashMap<String,Double>();
		hmap.put("humidityInNumber", Double.parseDouble(humidity[1].substring(1,3)));
		hmap.put("tempInKelvin", CelsiusToKelvin(tempInKelvin));
		
		return hmap;
	}
	
	public void extractTempFromAPI(WeatherJsonResponse response)
	{
		tempInKelvinAPI=response.getMain().getTemp();
		humidity=response.getMain().getHumidity();		
		
	}
	public void compareTemperature()
	{
		if(tempInKelvinAPI==hmap.get("tempInKelvin"))
		{
		Verify.verify(tempInKelvinAPI==(hmap.get("tempInKelvin"))," Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
		}
		else
		{
			Assert.assertEquals(tempInKelvinAPI==(hmap.get("tempInKelvin")),"Temperature in Kelvin from API response : "+tempInKelvinAPI+" and Temperature in Kelvin from WebUI is "+hmap.get("tempInKelvin"));
		}
		
		if(humidity==hmap.get("humidityInNumber"))
		{
			Verify.verify(humidity== hmap.get("humidityInNumber")," Temperature in Kelvin from API response : "+humidity+" and Temperature in Kelvin from WebUI is "+hmap.get("humidityInNumber"));
		}
		else
		{
			Assert.assertEquals(humidity== hmap.get("humidityInNumber")," Temperature in Kelvin from API response : "+humidity+" and Temperature in Kelvin from WebUI is "+hmap.get("humidityInNumber"));
		}
	}

}
