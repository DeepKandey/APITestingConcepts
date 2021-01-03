package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Platform;

import java.io.File;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static Platform platform;
    private static Date date = new Date();
    private static String reportFileName = "Extent_" + date.toString().replaceAll(":", "_") + ".html";
    private static String windowsPath = System.getProperty("user.dir") + "/test-output";
    // private static String macPath = System.getProperty("user.dir")+
    // "/TestReport";
    private static String winReportFileLoc = windowsPath + "/" + reportFileName;
    // private static String macReportFileLoc = macPath + "/" + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null) {

            createInstance();
        }
        return extent;
    } // End of method getInstance

    public static ExtentReports createInstance() {
        new ExtentManager();
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Automation Tester", "Deepak Rai");
        extent.setSystemInfo("Organization", "Deepak Automation Labs");
        extent.setSystemInfo("Build Number", "Deepak 1.0");

        return extent;
    } // End of method createInstance

    // Select the extent report file location based on platform
    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            /*
             * case MAC: reportFileLocation = macReportFileLoc; createReportPath(macPath);
             * System.out.println("ExtentReport Path for MAC: " + macPath + "\n"); break;
             */
            case WIN10:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    } // End of method getReportFileLocation

    // Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }// End of method createReportPath

    // Get current platform
    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String OS = System.getProperty("os.name").toLowerCase();
            if (OS.contains("win")) {
                platform = Platform.WIN10;
            } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                platform = Platform.LINUX;
            } else if (OS.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    } // End of method getCurrentPlatform
} // End of class ExtentManager