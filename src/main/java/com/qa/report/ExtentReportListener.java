package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.qa.util.LoggerUtil.log;

public class ExtentReportListener implements ITestListener {

  // Extent Report Declarations
  private static ExtentReports extent = ExtentManager.createInstance();
  private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

  public static synchronized ExtentTest getExtentTest() {
    try {
      return test.get();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void onTestStart(ITestResult result) {
    ExtentTest extentTest =
        extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
    test.set(extentTest);
    log(result.getMethod().getMethodName() + " started!");
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    log((result.getMethod().getMethodName() + " passed!"));
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log((result.getMethod().getMethodName() + " failed!"));

    String exceptionMessage = (result.getThrowable().getMessage());
    test.get()
        .fail(
            "<details>"
                + "<summary>"
                + "<b>"
                + "<font color="
                + "red>"
                + "Exception Occurred:Click to see"
                + "</font>"
                + "</b>"
                + "</summary>"
                + exceptionMessage.replace(",", "<br>")
                + "</details>"
                + " \n");
    // test.get().fail(result.getThrowable());

    String failureLog = "TEST CASE FAILED";
    Markup markup = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
    test.get().fail(markup);
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    log((result.getMethod().getMethodName() + " skipped!"));
    test.get().skip("Test Case Skipped");
    test.get().skip(result.getThrowable());
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    log(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
  }

  @Override
  public void onStart(ITestContext context) {
    log("Extent Reports Version 5 Test Suite started! " + context.getOutputDirectory());
  }

  @Override
  public void onFinish(ITestContext context) {
    log("Extent Reports Version 5  Test Suite is ending!");
    extent.flush();
    test.remove();
  }
} // End of class ExtentTestNGITestListener
