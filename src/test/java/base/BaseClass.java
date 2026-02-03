package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import factory.BrowserFactory;
import pageClasses.AccountsPage;
import pageClasses.CheckOutPage;
import pageClasses.LoginPage;
import pageClasses.MyAccountPage;
import pageClasses.RegisterPage;
import pageClasses.ReviewAndPayment;
import pageClasses.SuccessPage;
import utility.ElementUtil;

public class BaseClass {
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ElementUtil eleUtil;
	protected RegisterPage regPage;
	protected SoftAssert softAssert;
	protected CheckOutPage checkOutPage;
	protected ReviewAndPayment reviewAndPayment;
	protected SuccessPage successPage;
	protected MyAccountPage myAccountPage;
	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();

	public BaseClass() {
		File f = new File(".\\src\\test\\resources\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		softAssert = new SoftAssert();
	}

	@BeforeTest
	public void init() {
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		WebDriver driver = BrowserFactory.createInstance(browserName);
		drivers.set(driver);
		getDriver().manage().deleteAllCookies();
		getDriver().get(url);
		getDriver().manage().window().maximize();
		loginPage = new LoginPage(getDriver());
	}

	public WebDriver getDriver() {
		return drivers.get();
		
	}

//
	@AfterTest
	public void tearDown() {
		getDriver().quit();
	}
}
