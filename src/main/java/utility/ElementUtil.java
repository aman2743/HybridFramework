package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentTest;

public class ElementUtil {

    private static final Logger logger = LogManager.getLogger(ElementUtil.class);

    public WebDriver driver;
    private Actions act;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        act = new Actions(driver);
        logger.info("ElementUtil initialized with driver: {}", driver.getClass().getSimpleName());
    }

    // Safe method to log to Extent (checks for null)
    private void extentLogInfo(String message) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.info(message);
        }
    }

    private void extentLogPass(String message) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.pass(message);
        }
    }

    private void extentLogFail(String message) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.fail(message);
        }
    }

    public void doSendKeys(WebElement element, String value) {
        logger.info("Entering text '{}' into element", value);
        extentLogInfo("Entering text: '" + value + "'");  // Safe call

        try {
            element.sendKeys(value);
            logger.debug("Text entered successfully");
            extentLogPass("Text entered successfully");  // Safe call
        } catch (Exception e) {
            logger.error("Failed to enter text '{}': {}", value, e.getMessage());
            extentLogFail("Failed to enter text: " + e.getMessage());  // Safe call
            throw e;
        }
    }

    public void doClick(WebElement element) {
        logger.info("Clicking on element");
        extentLogInfo("Clicking on element");

        try {
            element.click();
            logger.debug("Click performed successfully");
            extentLogPass("Click performed successfully");
        } catch (Exception e) {
            logger.error("Failed to click element: {}", e.getMessage());
            extentLogFail("Failed to click: " + e.getMessage());
            throw e;
        }
    }

    public void doSelectRadioButton(WebElement element) {
        logger.info("Selecting radio button");
        extentLogInfo("Selecting radio button");

        try {
            element.click();
            logger.debug("Radio button selected");
            extentLogPass("Radio button selected");
        } catch (Exception e) {
            logger.error("Failed to select radio button: {}", e.getMessage());
            extentLogFail("Failed to select radio button: " + e.getMessage());
            throw e;
        }
    }

    public String doElementGetText(WebElement element) {
        logger.info("Getting text from element");
        extentLogInfo("Getting text from element");

        try {
            String elementText = element.getText();
            logger.info("Element text retrieved: '{}'", elementText);
            extentLogPass("Text retrieved: '" + elementText + "'");
            return elementText;
        } catch (Exception e) {
            logger.error("Failed to get element text: {}", e.getMessage());
            extentLogFail("Failed to get text: " + e.getMessage());
            throw e;
        }
    }

    public boolean checkElementIsDisplayed(WebElement element) {
        logger.debug("Checking if element is displayed");
        extentLogInfo("Checking element visibility");

        try {
            boolean displayed = element.isDisplayed();
            logger.info("Element displayed status: {}", displayed);
            extentLogPass("Element displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Failed to check display status: {}", e.getMessage());
            extentLogFail("Failed to check visibility: " + e.getMessage());
            throw e;
        }
    }

    public String getElementAttribute(WebElement element, String attrName) {
        logger.info("Getting attribute '{}' from element", attrName);
        extentLogInfo("Getting attribute: '" + attrName + "'");

        try {
            String attrValue = element.getAttribute(attrName);
            logger.info("Attribute '{}' value: '{}'", attrName, attrValue);
            extentLogPass("Attribute '" + attrName + "' = '" + attrValue + "'");
            return attrValue;
        } catch (Exception e) {
            logger.error("Failed to get attribute '{}': {}", attrName, e.getMessage());
            extentLogFail("Failed to get attribute: " + e.getMessage());
            throw e;
        }
    }

    public boolean checkElementIsDisabled(WebElement element) {
        logger.debug("Checking if element is disabled");
        extentLogInfo("Checking if element is disabled");

        try {
            String disabledValue = element.getAttribute("disabled");
            boolean isDisabled = "true".equals(disabledValue);
            logger.info("Element disabled status: {}", isDisabled);
            extentLogPass("Element disabled: " + isDisabled);
            return isDisabled;
        } catch (Exception e) {
            logger.error("Failed to check disabled status: {}", e.getMessage());
            extentLogFail("Failed to check disabled status: " + e.getMessage());
            throw e;
        }
    }
}