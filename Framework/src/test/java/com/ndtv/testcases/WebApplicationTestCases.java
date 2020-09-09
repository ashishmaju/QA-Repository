package com.ndtv.testcases;

import java.io.IOException;
import java.util.ListIterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ndtv.keywords.CommonFunctions;
import com.ndtv.locators.PageLocators;
import com.ndtv.pages.HomePage;
import com.ndtv.pages.WeatherPage;
import com.ndtv.propertiesFile.PropertyFile;

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
	}

	@Test(dependsOnMethods = "launchNDTV")
	public void navigateToWeather() {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		isDisplayed(hp.getMoreIconTab());
		click(hp.getMoreIconTab());
		isDisplayed(hp.getWeatherMenu());
		click(hp.getWeatherMenu());
	}

	@Test(dependsOnMethods = "navigateToWeather")
	public void validateCityOnMap() throws InterruptedException {
		wp = PageFactory.initElements(driver, WeatherPage.class);
		sendKeys(wp.getSearchbox(), PageLocators.cityName);
		Thread.sleep(3000);
		cf.jsClick(driver, wp.cityNameInChecklist());
		isDisplayed(wp.getcityTemperature());
		click(wp.getcityTemperature());
	}

	@Test(dependsOnMethods = "validateCityOnMap")
	public void captureTemp() throws InterruptedException {
		wp = PageFactory.initElements(driver, WeatherPage.class);

		String city = getText(wp.leafletPopUpHeader());
		AssertJUnit.assertTrue(city.contains(PageLocators.cityName));
		ListIterator<String> itr = wp.leafletPopUpTemp().listIterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().toString());
		}
	}

}
