package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;

public class MyAccountPageTest extends BaseClass {
	String company = prop.getProperty("companyName");
	String address1 = prop.getProperty("Address1");
	String city = prop.getProperty("city");
	String country = prop.getProperty("country");
	String state = prop.getProperty("state");
	String pcode = prop.getProperty("postalCode");
	String pnumber = prop.getProperty("PhoneNumber");
	String urlAccount=prop.getProperty("myAccountURL");

	@BeforeClass
	public void PageInitMyAccountPage() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("uname"), prop.getProperty("password"));
		checkOutPage = accPage.CheckoutPageNavigation();
		reviewAndPayment = checkOutPage.submitShippingAddressPage(company, address1, city, country, state, pcode,
				pnumber);
		successPage = reviewAndPayment.placeOrderButtonClick();
		myAccountPage = successPage.clickMyAccountOption(urlAccount);
		Thread.sleep(20);
	}

	@Test(priority = 1)
	public void verifyUserLandsOnMyAccountPage() {
		Assert.assertTrue(myAccountPage.verifyMyAccountPage());
	}

	@Test(priority = 2)
	public void verifyUserLandsOnMyOrdersPage() {
		myAccountPage.clickMyOrderLink();
		Assert.assertTrue(myAccountPage.verifyUserIsAtMyOrdersPage());
	}

	@Test(priority = 3)
	public void verifyOrderNumber() {
		String str1 =successPage.getOrderNumberGenerated() ;
		String str2 = myAccountPage.getOrderNumberFromOrderList();
		if (str1.equals(str2)) {
			System.out.println("Order Number Matched");
		} else {
			System.out.println("Order Number not Matched");
		}
	}

}
