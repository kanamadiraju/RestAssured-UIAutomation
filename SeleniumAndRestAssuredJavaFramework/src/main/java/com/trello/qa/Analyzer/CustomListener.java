package com.trello.qa.Analyzer;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.trello.qa.base.TestBase;

public class CustomListener extends TestBase implements ITestListener{
	
	
	public void onTestStart(ITestResult result) {

    }
	
    public void onTestSuccess(ITestResult result) {

    }
	
    public void onTestSkipped(ITestResult result) {

    }
	
    public void onTestFailure(ITestResult result) {
		System.out.println("Failed Test");
		captureScreenshot(result.getMethod().getMethodName());

    }

    public void onStart(ITestContext context) {

    }
	
    public void onFinish(ITestContext context) {

    }

}
 