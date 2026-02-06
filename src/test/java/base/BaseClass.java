package base;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.Logger;
import factory.BrowserFactory;
import pageClasses.*;
import utility.ElementUtil;
import utility.LoggerUtil;
import org.testng.annotations.AfterTest;

public class BaseClass {
    private static final Logger logger = LoggerUtil.getLogger(BaseClass.class);
    protected Properties prop;
    protected LoginPage loginPage;
    protected ElementUtil eleUtil;
    protected SoftAssert softAssert;
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTests = new ThreadLocal<>();

	public BaseClass() {
        System.out.println("inside BaseClass constructor");
		File f = new File(".\\src\\test\\resources\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
            logger.info("Config file loaded successfully");
		} catch (Exception e) {
            logger.error("Failed to load config.properties: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load configuration", e);
		}
        softAssert=new SoftAssert();
	}

	@BeforeTest
	public void init() {
            String browserName = prop.getProperty("browser");
            String url = prop.getProperty("url");
            logger.info("Browser: {}", browserName);
            logger.info("URL: {}", url);
            WebDriver driver = BrowserFactory.createInstance(browserName);
            drivers.set(driver);
        if (getDriver() == null) {
            logger.fatal("Driver initialization failed!");
            throw new RuntimeException("Driver initialization failed!");
        }
            getDriver().manage().deleteAllCookies();
            getDriver().get(url);
            logger.info("Navigating to URL: {}", url);
            getDriver().manage().window().maximize();
//          Initialize Page Objects
            logger.info("Initializing Page Objects");
            loginPage = new LoginPage(getDriver());
	}

	public WebDriver getDriver() {
		return drivers.get();
	}
    // Get ExtentTest for current thread
    public ExtentTest getExtentTest() {
        return extentTests.get();
    }

    // Set ExtentTest for current thread
    public void setExtentTest(ExtentTest test) {
        extentTests.set(test);
    }

//@AfterTest
//public void tearDown() {
//    logger.info("========== Test Execution Complete - Cleaning Up ==========");
//    if (getDriver() != null) {
//        logger.info("Quitting WebDriver");
//        getDriver().quit();
//
//    }
//    drivers.remove();// Critical for thread cleanup
//    extentTests.remove();
//    logger.info("========== Cleanup Complete ==========");
//}
}
