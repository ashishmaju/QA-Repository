package com.ndtv.keywords;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ndtv.testcases.BaseClass;


//Some General Functions are defined in this class
public class CommonFunctions extends BaseClass
{
	private static Logger log=LogManager.getLogger(CommonFunctions.class.getName());
	
	public void visibilityOfElements(WebDriver driver,WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By)ele));
	}
	
	public void jsClick(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	
	public static void isDisplayed(WebElement element)
	{
		if(element.isDisplayed())
		{
			Assert.assertEquals(element.isDisplayed(), true);
			log.info("Element "+element+" is present");
		}
		else
		{
			log.error("Element "+element+" is not present on page");
		}
	}
	
	public static void click(WebElement element)
	{
		if(element.isDisplayed())
		{
		element.click();
		log.info("Element "+element+" is clicked sucessfully");
		}
		else
		{
			log.error("Element "+element+" is not present on current page");
		}
	}
	
	public void sendKeys(WebElement element,String value)
	{
		if(element.isDisplayed())
		{
		element.sendKeys(value);
		log.info("Element "+element+" is entered with value "+value+" sucessfully");
		}
		else
		{
			log.error("Element "+element+" is not present on current page");
		}
	}
	
	public String getText(WebElement element)
	{
		String text=null;
		if(element.isDisplayed())
		{
			log.info("Element "+element+" have value "+element.getText());
			text= element.getText();
		}
		else
		{
			text=null;
			log.error("Element "+element+" is not present on current page");
		}
		
		return text;
		
	}
	
	public static void findBy(WebDriver driver,String LocatorType, String Locator)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		driver.findElement(By.xpath(Locator));
	}
	
	public void screenShot(String testCaseName,WebDriver driver)
	{
		try		
		{
		TakesScreenshot scrnshot=(TakesScreenshot)driver;
		File source=scrnshot.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png";
		File dest=new File(destination);
		FileUtils.copyFile(source, dest);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}
