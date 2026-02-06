package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Initialize Extent Reports
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        // Create report directory
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportName = "ExtentReport_" + timeStamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/";

        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Initialize Spark Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath + reportName);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Hybrid Framework Test Execution");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        // Initialize Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info
        extent.setSystemInfo("Project Name", "Hybrid Framework");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));

        System.out.println("Extent Report initialized at: " + reportPath + reportName);
    }

    // ✅ THIS METHOD WAS MISSING - Start test
    public static ExtentTest startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        extentTest.set(test);
        return test;
    }

    // ✅ THIS METHOD WAS MISSING - Start test with description
    public static ExtentTest startTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        extentTest.set(test);
        return test;
    }

    // ✅ THIS METHOD WAS MISSING - Get current test
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    // ✅ THIS METHOD WAS MISSING - End test
    public static void endTest() {
        if (extentTest.get() != null) {
            extentTest.remove();
        }
    }

    // ✅ THIS METHOD WAS MISSING - Flush reports
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Safe logging methods with null checks
    public static void logInfo(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.info(message);
        } else {
            System.out.println("[Extent-INFO] " + message);
        }
    }

    public static void logPass(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.pass(message);
        } else {
            System.out.println("[Extent-PASS] " + message);
        }
    }

    public static void logFail(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.fail(message);
        } else {
            System.err.println("[Extent-FAIL] " + message);
        }
    }

    public static void logWarning(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.warning(message);
        } else {
            System.out.println("[Extent-WARN] " + message);
        }
    }

    // Add screenshot
    public static void addScreenshot(String screenshotPath) {
        ExtentTest test = getTest();
        if (test != null && screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }
}