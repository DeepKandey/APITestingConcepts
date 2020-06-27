package com.qa.report;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentReportListener implements ITestListener {

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static synchronized ExtentTest getExtentTest() {
		return test.get();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test Case Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b>" + "</summary>" + exceptionMessage.replace(",", "<br>") + "</details>" + " \n");
		// test.get().fail(result.getThrowable());

		String failureLog = "TEST CASE FAILED";
		Markup markup = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		test.get().fail(markup);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip("Test Case Skipped");
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Extent Reports Version 4 Test Suite started! " + context.getOutputDirectory());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Extent Reports Version 4  Test Suite is ending!");
		System.out.println("This is onFinish method. Passed Tests: " + context.getPassedTests());
		System.out.println("This is onFinish method. Failed Test: " + context.getFailedTests());
		System.out.println("This is onFinish method. Skipped Test: " + context.getSkippedTests());
		extent.flush();
		test.remove();
	}
}// End of class ExtentTestNGITestListener