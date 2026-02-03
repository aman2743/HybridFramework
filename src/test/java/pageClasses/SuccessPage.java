package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import constants.AppConstants;
import utility.ElementUtil;

public class SuccessPage {
	
	public WebDriver driver;
	private ElementUtil eleUtil;
 
	private By thankYouPage=By.xpath("//span[text()='Thank you for your purchase!']");
	private By orderNumberText=By.xpath("//a[@class='order-number']");
	private By orderNumberValue=By.xpath("//a[@class='order-number']/child::strong");
	private By customerNameArrow=By.xpath("(//button[@data-action='customer-menu-toggle'])[1]");
	private By myAccount=By.xpath("//a[text()='My Account'][1]");
	
//	Constructor
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String verifyOrderIsSuccess() {
		WebElement we=eleUtil.waitForElementVisible(thankYouPage,AppConstants.SHORT_TIME_OUT);
		String actualText=we.getText();
		return actualText;
	}
	
	public boolean verifyOrderNumberGenerated() {
		WebElement we=eleUtil.waitForElementVisible(orderNumberText,AppConstants.SHORT_TIME_OUT);
		return we.isDisplayed();
	}
	
	public String getOrderNumberGenerated() {
	     String OrderNumberFromSuccessPage=eleUtil.doElementGetText(orderNumberValue);
		 return OrderNumberFromSuccessPage;
	}
	
	public void clickCustomerNameArrow() {
		WebElement we=eleUtil.waitForElementVisible(customerNameArrow,AppConstants.LONG_TIME_OUT,AppConstants.SHORT_TIME_OUT);
		we.click();
	}
	
	public MyAccountPage clickMyAccountOption(String str) throws InterruptedException {
		Thread.sleep(8000);
		clickCustomerNameArrow();
		eleUtil.waitForElementAndClick(myAccount,AppConstants.LONG_TIME_OUT,AppConstants.SHORT_TIME_OUT);
		return new MyAccountPage(driver);
	}
}
