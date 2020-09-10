package com.ndtv.locators;

import com.ndtv.propertiesFile.PropertyFile;

//This page contains locators of Home Page and Weather Page
public class PageLocators 
{
public static String cityName=PropertyFile.propKey("CityName");
public final static String moreIcon="h_sub_menu";
public final static String weatherMenu="WEATHER";
public final static String searchbox="searchBox";
public final static String selectedCityInList="//div[@class='messages']//input[@id='Allahabad']";
public final static String cityTemperatureOnMap="//div[text()='Allahabad']";
public final static String zoomButtonOnMap="//a[@title='Zoom in']";
public final static String leafletPopUp="//div[@class='leaflet-popup-content']//span[contains(text(),'Allahabad')]";
public final static String leafletPopUpTemp="//span[@class='heading']/b[text()]";
public final static String tempContainer="(//div[@class='temperatureContainer'])[1]";

}
