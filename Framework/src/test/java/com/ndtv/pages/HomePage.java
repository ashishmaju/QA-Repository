package com.ndtv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ndtv.locators.PageLocators;

//NDTV Home Page Data Members locators and Behaviors 
public class HomePage 
{
	public HomePage(WebDriver driver)
	{
	}
	
	@FindBy(id=PageLocators.moreIcon) WebElement moreIcon;
	@FindBy(linkText=PageLocators.weatherMenu) WebElement weather;

	public WebElement getMoreIconTab() {
		return moreIcon;
	}

	public WebElement getWeatherMenu() {
		return weather;
	}
	

}
