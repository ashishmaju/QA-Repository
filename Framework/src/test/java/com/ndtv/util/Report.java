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
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setDocumentTitle("Coding Assignment");
    htmlReporter.config().setReportName("Assignment Report");
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.STANDARD);	
    return extent;
	}

}
