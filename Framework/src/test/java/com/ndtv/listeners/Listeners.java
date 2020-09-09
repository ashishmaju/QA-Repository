package com.ndtv.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ndtv.keywords.CommonFunctions;
import com.ndtv.propertiesFile.PropertyFile;
import com.ndtv.util.Report;

public class Listeners implements ITestListener {
	public ExtentTest test;
	Report extent = new Report();
	ExtentReports reports = extent.extentReportsConfiguration();

	public void onFinish(ITestContext arg0) {
		reports.flush();
	}

	public void onStart(ITestContext result) {
		Report extent = new Report();
		ExtentReports reports = extent.extentReportsConfiguration();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult result) {

			WebDriver driver = null;
			CommonFunctions cf = new CommonFunctions();
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			try {
				String methodName = result.getMethod().getMethodName();
				if (!PropertyFile.propKey("API").equalsIgnoreCase("true"))
				{
				driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
				cf.screenShot(methodName, driver);
				}
			} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
		test.skip(result.getThrowable());

	}

	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));

	}

}
