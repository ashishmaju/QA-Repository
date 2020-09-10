package com.ndtv.driverSetup;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import com.ndtv.propertiesFile.PropertyFile;

public class BaseClass
{
	public static WebDriver driver=null;
	@SuppressWarnings("deprecation")
	public static WebDriver initDriver()
	{
		String browser=PropertyFile.propKey("Browser");
		if(browser.equalsIgnoreCase("CHROME"))
		{
			DesiredCapabilities capability=new DesiredCapabilities();
			DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver=new ChromeDriver(capability);
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	@AfterTest
	public void shutBrowser()
	{
		driver.quit();
	}
}
