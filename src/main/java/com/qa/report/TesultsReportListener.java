package com.qa.report;

import com.tesults.tesults.Results;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TesultsReportListener implements ITestListener {

  List<Map<String, Object>> testCases = new ArrayList<>();

  public static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  public static Object[] getTestParams(ITestResult iTestResult) {
    return iTestResult.getParameters();
  }

  public byte[] saveScreenshotPNG(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void onTestStart(ITestResult result) {
    System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    Map<String, Object> testCase = new HashMap<>();
    testCase.put("name", getTestMethodName(iTestResult));
    testCase.put("suite", "TesultsExample");
    testCase.put("result", "pass");
    testCase.put("params", getTestParams(iTestResult));
    testCases.add(testCase);
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    Map<String, Object> testCase = new HashMap<>();
    testCase.put("name", getTestMethodName(iTestResult));
    testCase.put("suite", "TesultsExample");
    testCase.put("result", "fail");
    testCase.put("params", getTestParams(iTestResult));
    List<String> files = new ArrayList<>();

    testCase.put("files", files);

    testCases.add(testCase);
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    Map<String, Object> testCase = new HashMap<>();
    testCase.put("name", getTestMethodName(iTestResult));
    testCase.put("suite", "TesultsExample");
    testCase.put("result", "fail");
    testCase.put("params", getTestParams(iTestResult));
    List<String> files = new ArrayList<>();

    testCase.put("files", files);

    testCases.add(testCase);
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void onStart(ITestContext context) {
    System.out.println("I am in onStart method " + context.getName());
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onFinish(ITestContext iTestContext) {
    // Map<String, Object> to hold your test results data.
    Map<String, Object> data = new HashMap<>();
    data.put(
        "target",
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjAzM2RjMDQyLWVjYmMtNGE4OS1iMTFlLWI3ZGVhMjUxNGFiYS0xNTg3ODc4NDY5NzM2IiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiYTQ5NTcwZmUtYTdhYS00YWFjLWEzOWQtMjNhZjYwN2YwNThiIiwidHlwZSI6InQifQ.CzZyZdmwF8Dp6U1HL5dJezZMIRG-l9Y22CmAqtqMzWQ");

    Map<String, Object> results = new HashMap<>();
    results.put("cases", testCases);
    data.put("results", results);

    // Upload
    Map<String, Object> response = Results.upload(data);
    System.out.println("success: " + response.get("success"));
    System.out.println("message: " + response.get("message"));
    System.out.println("warnings: " + ((List<String>) response.get("warnings")).size());
    System.out.println("errors: " + ((List<String>) response.get("errors")).size());
  }
}
