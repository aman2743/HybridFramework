package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import constants.AppConstants;
import utility.ElementUtil;

public class CheckOutPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By shippingAddress = By.xpath("//li[@id='shipping']/child::div[1]");
	private By companyName = By.name("company");
	private By streetAddress1 = By.name("street[0]");
	private By city = By.name("city");
	private By stateDropdown = By.name("region_id");
	private By postalCode = By.name("postcode");
	private By countryDropdown = By.name("country_id");
	private By phoneNumber = By.name("telephone");
	private By nextButton = By.xpath("//button[@data-role='opc-continue']/child::span");
	private By paymentMethod = By.xpath("//span[text()='Check / Money order']");

//  Login page constructor
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean verifyShippingAddressPage() {
		String text = eleUtil.waitForElementPresence(shippingAddress, AppConstants.LONG_TIME_OUT).getText();
		System.out.println(text);
		return eleUtil.waitForElementPresence(shippingAddress, AppConstants.LONG_TIME_OUT).isDisplayed();

	}

	public boolean verifyCompanyNameFieldIsDisplayed() {
		return eleUtil.checkElementIsDisplayed(companyName);
	}

	public void shippingAddress(String cvalue, String address1, String cname) {
		eleUtil.waitForElementAndEnterValue(companyName, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT,
				cvalue);
//		eleUtil.doSendKeys(streetAddress1, address1);
		eleUtil.waitForElementAndEnterValue(streetAddress1, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT,
				address1);
		eleUtil.waitForElementAndEnterValue(city, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT, cname);
//		eleUtil.doSendKeys(city, cname);

	}

	public void SelectCountry(String countryName) {
		eleUtil.doSelectDropDownByValue(countryDropdown, countryName);
	}

	public void SelectState(String StateName) {
		eleUtil.doSelectDropDownByVisibleText(stateDropdown, StateName);
	}

	public void postalCode(String postalValue) {
		eleUtil.waitForElementAndEnterValue(postalCode, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT,
				postalValue);
//		eleUtil.doSendKeys(postalCode, postalValue);
	}

	public void phoneNumber(String phoneValue) {
		eleUtil.waitForElementAndEnterValue(phoneNumber, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT,
				phoneValue);
//		eleUtil.doSendKeys(phoneNumber, phoneValue);
	}

//	public void ShippingMethod() {
//		eleUtil.doSelectRadioButton(firstRadiobutton);
//	}

	public void clickFirstNext() {
		eleUtil.clickElementWhenReady(nextButton, AppConstants.LONG_TIME_OUT);
	}

//	public void clickSecondNext() {
//		eleUtil.clickElementWhenReady(nextButton,AppConstants.LONG_TIME_OUT);
//	}

	public ReviewAndPayment submitShippingAddressPage(String cvalue, String address1, String cname, String countryName,
			String StateName, String postalValue, String phoneValue) throws InterruptedException {
		Thread.sleep(10000);
		boolean bn = verifyCompanyNameFieldIsDisplayed();
		System.out.println(bn);
		if (bn == true) {
			shippingAddress(cvalue, address1, cname);
			SelectCountry(countryName);
			SelectState(StateName);
			postalCode(postalValue);
			phoneNumber(phoneValue);
			clickFirstNext();
			return new ReviewAndPayment(driver);
		} else {
			clickFirstNext();
			return new ReviewAndPayment(driver);
		}
	}

	public boolean verifyPaymentsMethodPage() {
		return eleUtil.checkElementIsDisplayed(paymentMethod);

	}
}
