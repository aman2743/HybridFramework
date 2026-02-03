package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import constants.AppConstants;
import utility.ElementUtil;

public class MyAccountPage {
	
	public WebDriver driver;
	private ElementUtil eleUtil;
    
	private By myAccountHeader=By.xpath("//span[text()='My Account']");
    private By myOrders=By.xpath("//a[text()='My Orders']");
    private By orderNumber=By.xpath("(//tbody/child::tr/child::td)[1]");
    
//	Constructor
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean verifyMyAccountPage() {
		WebElement we=eleUtil.waitForElementVisible(myAccountHeader,AppConstants.LONG_TIME_OUT);
		return we.isDisplayed();
	}
	
	public void clickMyOrderLink() {
		eleUtil.clickElementWhenReady(myOrders,AppConstants.LONG_TIME_OUT);
	}
	
	public boolean verifyUserIsAtMyOrdersPage() {
		return eleUtil.checkElementIsDisplayed(myOrders);
	}
	
	public String getOrderNumberFromOrderList() {
	 String OrderNumberFromMyAccountPage= eleUtil.doElementGetText(orderNumber);
	 return OrderNumberFromMyAccountPage;
	}
	
}
