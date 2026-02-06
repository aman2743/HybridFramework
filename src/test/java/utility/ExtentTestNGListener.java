package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestNGListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ExtentTestNGListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started: {}", context.getName());
        ExtentReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        logger.info("Starting Test: {}", testName);

        // Start Extent Test
        ExtentTest extentTest = ExtentReportManager.startTest(testName, description);
        extentTest.assignCategory(result.getTestClass().getRealClass().getSimpleName());

        // Log test start
        extentTest.info("Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long duration = result.getEndMillis() - result.getStartMillis();

        logger.info("Test Passed: {} ({}ms)", testName, duration);

        ExtentTest extentTest = ExtentReportManager.getTest();
        extentTest.pass(MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
        extentTest.info("Duration: " + duration + "ms");

        // Capture screenshot on success (optional)
        captureScreenshot(result, "Success");

        ExtentReportManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        logger.error("Test Failed: {}", testName);
        logger.error("Error: {}", throwable.getMessage());

        ExtentTest extentTest = ExtentReportManager.getTest();
        extentTest.fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
        extentTest.fail(throwable);

        // Capture screenshot on failure
        String screenshotPath = captureScreenshot(result, "Failure");
        if (screenshotPath != null) {
            extentTest.fail("Screenshot on failure:");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }

        ExtentReportManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        logger.warn("Test Skipped: {}", testName);

        ExtentTest extentTest = ExtentReportManager.getTest();
        extentTest.skip(MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
        extentTest.skip(result.getThrowable());

        ExtentReportManager.endTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished: {}", context.getName());
        logger.info("Passed: {}, Failed: {}, Skipped: {}",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());

        // Flush Extent Reports
        ExtentReportManager.flush();
        logger.info("Extent Report generated successfully");
    }

    // Helper method to capture screenshot
    private String captureScreenshot(ITestResult result, String status) {
        try {
            // Get driver from BaseClass via reflection or ThreadLocal
            Object testInstance = result.getInstance();
            WebDriver driver = getDriverFromTest(testInstance);

            if (driver != null) {
                String testName = result.getMethod().getMethodName();
                return ScreenshotUtil.captureScreenshot(driver, testName + "_" + status);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
        return null;
    }

    // Get driver from test class instance
    private WebDriver getDriverFromTest(Object testInstance) {
        try {
            // Try to get driver using reflection
            java.lang.reflect.Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            return (WebDriver) getDriverMethod.invoke(testInstance);
        } catch (Exception e) {
            logger.error("Could not get driver from test instance: {}", e.getMessage());
            return null;
        }
    }
}