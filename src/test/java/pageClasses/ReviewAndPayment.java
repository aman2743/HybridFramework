package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import constants.AppConstants;
import utility.ElementUtil;

public class ReviewAndPayment {
	
	public WebDriver driver;
	private ElementUtil eleUtil;
    
	private By placeOrder=By.xpath("//button[@title='Place Order']");

	
//	Constructor
	public ReviewAndPayment(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean isPlaceOrderButtonDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		WebElement we=eleUtil.waitForElementPresence(placeOrder,AppConstants.LONG_TIME_OUT,AppConstants.SHORT_TIME_OUT);
//		WebElement we=eleUtil.waitForElementVisible(placeOrder,AppConstants.LONG_TIME_OUT);
		boolean b1 =we.isDisplayed();
		System.out.println(b1);
		return b1;
		
	}
	
//	public void clickOnPlaceOrderButton() {
//		eleUtil.clickElementWhenReady(placeOrder,AppConstants.SHORT_TIME_OUT);
//	}
	
	public SuccessPage placeOrderButtonClick() throws InterruptedException {
		Thread.sleep(5000);
		eleUtil.clickElementWhenReady(placeOrder,AppConstants.LONG_TIME_OUT);
		return new SuccessPage(driver);
	}
}
