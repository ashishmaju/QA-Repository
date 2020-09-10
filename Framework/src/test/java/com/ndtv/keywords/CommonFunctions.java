package com.ndtv.keywords;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ndtv.driverSetup.BaseClass;
import com.ndtv.listeners.Listeners;
import com.relevantcodes.extentreports.LogStatus;

//Some General Functions are defined in this class
public class CommonFunctions extends BaseClass {
	public static Listeners listener=new Listeners();
	
	public static boolean visibilityOfElement(WebDriver driver, WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(ele));
			if(true)
			{
				listener.getTest().log(Status.PASS, ele+"is present on page");	
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void jsClick(WebDriver driver, WebElement ele) {
		if(visibilityOfElement(driver,ele))
		{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		listener.getTest().log(Status.PASS, ele+"is clicked");
		}
		else
		{
			listener.getTest().log(Status.FAIL, ele+"not present on the page");
		}
	}

	public static void isDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			Assert.assertEquals(element.isDisplayed(), true);
			listener.getTest().log(Status.PASS, element+"is present on the page");
		} else {
			listener.getTest().log(Status.FAIL, element+"not present on the page");
		}
	}

	public static void click(WebElement element) {
		if (visibilityOfElement(driver, element)) {
			element.click();
			listener.getTest().log(Status.PASS,"Element " + element + "is clicked sucessfully");
		} else {
			listener.getTest().log(Status.FAIL,"Element " + element + "is not present on the page");
		}
	}

	public void sendKeys(WebElement element, String value) {
		if (visibilityOfElement(driver, element)) {
			element.sendKeys(value);
			listener.getTest().log(Status.PASS,"Element " + element + "is entered with value " + value + " sucessfully");
		} else {
			listener.getTest().log(Status.FAIL,"Element " + element + " is not present on the page");
		}
	}

	public String getText(WebElement element) {
		String text = null;
		if (visibilityOfElement(driver, element)) {
			listener.getTest().log(Status.PASS,"Element " + element + " have value " + element.getText()+" on the screen");
			text = element.getText();
		} else {
			text = null;
			listener.getTest().log(Status.FAIL,"Element " + element + " is not present on the page");
		}
		return text;
	}

	public void screenShot(String testCaseName, WebDriver driver) {
		try {
			TakesScreenshot scrnshot = (TakesScreenshot) driver;
			File source = scrnshot.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "\\Screenshots\\" + testCaseName + ".png";
			File dest = new File(destination);
			FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public static void testStatus(Boolean bool,Object obj)
	{
		if(bool==true)
		{
		listener.getTest().log(Status.PASS, "Identifier "+obj);
		}
		else
		{
		listener.getTest().log(Status.FAIL, "Identifier "+obj);
		}
	}
}
