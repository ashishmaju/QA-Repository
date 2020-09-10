package com.ndtv.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

import com.aventstack.extentreports.Status;
import com.ndtv.util.ExcelUtil;
import com.ndtv.util.TemperatureUtility;

public class ComparatorTestCase extends TemperatureUtility
{
	SoftAssert softassert=new SoftAssert();
	TemperatureUtility temp=new TemperatureUtility();
	
	@Test(priority=1)
	public void tempComparatorOfWebUIandAPI()
	{
		TemperatureUtility temp=new TemperatureUtility();
		temp.compareTemperature();
	}
	
	@Test(priority=2)
	public void varianceCheck()
	{
		ExcelUtil util=new ExcelUtil();
		ArrayList<String> al=util.getData("TempVariance");		
		Double temperature=CelsiusToKelvin(Double.parseDouble(al.get(1)));
		if(minimumTemp<=temperature)
		{
			if(maximumTemp>=temperature)
			{
				softassert.assertEquals(maximumTemp>=temperature, "Provided temperature "+temperature+"is met the condition, is between minimum temp "+minimumTemp+" and maximum temp "+maximumTemp);
				listener.getTest().log(Status.PASS,"Provided temperature "+temperature+"is met the condition, is between minimum temp "+minimumTemp+" and maximum temp "+maximumTemp);
			}
			else
			{
				softassert.fail("Provided temperature "+temperature+" not mat the condition, Should be between minimum temp "+minimumTemp+" and maximum temp "+maximumTemp);
				listener.getTest().log(Status.FAIL,"Provided temperature "+temperature+" not mat the condition, Should be between minimum temp "+minimumTemp+" and maximum temp "+maximumTemp);
			}
		}
		
		softassert.assertAll();
	}
}
