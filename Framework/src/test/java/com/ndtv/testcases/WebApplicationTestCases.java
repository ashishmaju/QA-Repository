package com.ndtv.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;
import com.ndtv.driverSetup.BaseClass;
import com.ndtv.keywords.CommonFunctions;
import com.ndtv.locators.PageLocators;
import com.ndtv.pages.HomePage;
import com.ndtv.pages.WeatherPage;
import com.ndtv.propertiesFile.PropertyFile;
import com.ndtv.util.TemperatureUtility;

public class WebApplicationTestCases extends CommonFunctions {
	public WebDriver driver;
	public WeatherPage wp;
	CommonFunctions cf = new CommonFunctions();

	@BeforeTest
	public void initializeBrowserDriver() {
		driver = BaseClass.initDriver();
	}

	@Test
	public void launchNDTV() throws IOException {
		driver.get(PropertyFile.propKey("URL"));
		listener.getTest().log(Status.PASS, "Navigated to "+PropertyFile.propKey("URL"));
	}

	@Test(dependsOnMethods = "launchNDTV")
	public void navigateToWeather() {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		visibilityOfElement(driver,hp.getMoreIconTab());
		click(hp.getMoreIconTab());
		isDisplayed(hp.getWeatherMenu());
		click(hp.getWeatherMenu());
	}

	@Test(dependsOnMethods = "navigateToWeather")
	public void validateCityOnMap() throws InterruptedException {
		wp = PageFactory.initElements(driver, WeatherPage.class);
		visibilityOfElement(driver, wp.tempContainer());
		sendKeys(wp.getSearchbox(), PageLocators.cityName);
		cf.jsClick(driver, wp.cityNameInChecklist());
		isDisplayed(wp.getcityTemperature());
		click(wp.getcityTemperature());
	}

	@Test(dependsOnMethods = "validateCityOnMap")
	public void captureTemp() throws InterruptedException {
		wp = PageFactory.initElements(driver, WeatherPage.class);

		String city = getText(wp.leafletPopUpHeader());
		Assert.assertTrue(city.contains(PageLocators.cityName));
		ListIterator<String> itr = wp.leafletPopUpTemp().listIterator();
		ArrayList<String> al=new ArrayList<String>();
		while (itr.hasNext()) {
			al.add(itr.next().toString());
		}
		TemperatureUtility tu=new TemperatureUtility();
		tu.extractTempFromWebApp(al);

	}

}
