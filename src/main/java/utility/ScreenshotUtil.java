package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/test-output/Screenshots/";

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timeStamp + ".png";
        String fullPath = SCREENSHOT_PATH + fileName;

        try {
            // Create screenshot directory
            File screenshotDir = new File(SCREENSHOT_PATH);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(fullPath);
            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot saved: " + fullPath);
            return fullPath;

        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "screenshot");
    }
}
