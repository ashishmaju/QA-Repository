package com.ndtv.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ndtv.locators.PageLocators;

//Weather Page Data Members Locators and it's Behaviors
public class WeatherPage
{
	WebDriver driver;
	public WeatherPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(id=PageLocators.searchbox) WebElement searchbox;
	@FindBy(xpath=PageLocators.selectedCityInList) WebElement cityName;
	@FindBy(xpath=PageLocators.cityTemperatureOnMap) WebElement cityTemperature;
	@FindBy(xpath=PageLocators.zoomButtonOnMap) WebElement zoomButton;
	@FindBy(xpath=PageLocators.leafletPopUp) WebElement leafletPopUp;
	@FindBy(xpath=PageLocators.tempContainer) WebElement tempContainer;
	@FindBy(xpath=PageLocators.leafletPopUpTemp) List<WebElement> leafletPopUpTemp;

	public WebElement getSearchbox() {
		return searchbox;
	}
	
	public WebElement cityNameInChecklist() {
		return cityName;
	}
	
	public WebElement getcityTemperature() {
		return cityTemperature;
	}
	
	public WebElement getZoomButton() {
		return zoomButton;
	}
	
	public WebElement leafletPopUpHeader()
	{
		return leafletPopUp;
	}
	
	public ArrayList<String> leafletPopUpTemp()
	{
		ArrayList<String> al=new ArrayList<String>();
		
		for(int i=0;i<leafletPopUpTemp.size();i++)
		{
			al.add(leafletPopUpTemp.get(i).getText());
		}
		return al;
	}
	public WebElement tempContainer()
	{
		return tempContainer;
	}
}
