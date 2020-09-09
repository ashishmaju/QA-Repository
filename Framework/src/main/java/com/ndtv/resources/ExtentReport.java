package com.ndtv.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport 
{

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentReports extentReportsConfiguration()
	{
		
	System.out.println(System.getProperty("user.dir")+"\\result\\");
	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\result\\ExecutionResults.html");
	extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setDocumentTitle("Extent Report Demo");
    htmlReporter.config().setReportName("Test Report");
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	
    return extent;
	}

}
