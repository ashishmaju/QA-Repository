package com.ndtv.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report 
{

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentReports extentReportsConfiguration()
	{	
	String path=System.getProperty("user.dir")+"\\result\\ExecutionResults.html";
	htmlReporter=new ExtentHtmlReporter(path);
	extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
   // extent.setSystemInfo("OS", OS);
  //  extent.setSystemInfo("Browser", browser);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setDocumentTitle("Extent Report Demo");
    htmlReporter.config().setReportName("Test Report");
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);
    //htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	
    return extent;
	}

}
