package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import utilities.ExtentReportGenerator;

public class MyListeners extends Base implements ITestListener{

	ExtentReports report= ExtentReportGenerator.getExtentReport();
	ExtentTest eTest;
	WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getName();
		eTest = report.createTest(testName);
		eTest.log(Status.INFO, testName + " started execution");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		String testName = result.getName();
		eTest.log(Status.PASS, testName+" got successfully execured ");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.FAIL, testName+" got failed ");
		
		try {
		 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		try {
			eTest.addScreenCaptureFromPath(takeScreenshotFilePath(testName,driver),testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.SKIP, testName+" got Skipped ");
		
			}

	@Override
	public void onFinish(ITestContext context) {
		
		report.flush();
	//	eTest.log(Status.PASS, testName+" got successfully finished ");
		
	}
	
	

}
