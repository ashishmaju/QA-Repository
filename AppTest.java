package Framework.TestAutomation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import snippet.Snippet;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public JavascriptExecutor js;
	public String str ;
	public Snippet snp=new Snippet();
	
	public WebDriver DriverInit()
	{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		 	ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        options.addArguments("window-size=1200x600");
	        WebDriver driver = new ChromeDriver(options);
	        driver.get("https://www.google.co.in/");
	        return driver;
	}
	
	@Test
	public void jsOperation() throws InterruptedException
	{
		WebDriver driver = DriverInit();

		System.out.println("Execution start");
		js = (JavascriptExecutor) driver;

		js.executeScript("sessionStorage.setItem('a','test')");

		Thread.sleep(2000);

		str= getItemFromLocalStorage("a");

		System.out.println(str);
		updateJsonValue();
		
		snp.writeToFile();
	}
	
	public String getItemFromLocalStorage(String key) {
	    return (String) js.executeScript(String.format(
	        "return window.sessionStorage.getItem('%s');", key));
	  }
	

	public String updateJsonValue() 
	{	
		try
		{
		JSONObject json=snp.getJsonString();
		System.out.println(json.toString());
		JSONArray array=json.getJSONArray("values");
		JSONObject json2=null;
		for(int i=0;i<=array.length()-1;i++)
		{
			if(array.getJSONObject(i).getString("key").equals("a"))
			{
				json2=array.getJSONObject(i);
				System.out.println(json2.getString("value"));
				json2.put("value", str);
			}
		}
		System.out.println(json.toString());
		
		return json.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
