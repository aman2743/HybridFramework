package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import constants.AppConstants;

public class SuccessPageTest extends BaseClass {
	 public static String orderNumberAtSuccessPage;
	 String company=prop.getProperty("companyName");
	 String address1=prop.getProperty("Address1");
	 String city=prop.getProperty("city");
	 String country=prop.getProperty("country");
	 String state=prop.getProperty("state");
	 String pcode=prop.getProperty("postalCode");
	 String pnumber=prop.getProperty("PhoneNumber");
	 String urlAccount=prop.getProperty("myAccountURL");
	 
	@BeforeClass
	public void PageInitSuccessPage() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("uname"), prop.getProperty("password"));
		checkOutPage = accPage.CheckoutPageNavigation();
		reviewAndPayment = checkOutPage.submitShippingAddressPage(company, address1, city, country, state,pcode,pnumber );
		successPage=reviewAndPayment.placeOrderButtonClick();
		Thread.sleep(20);
	}

	@Test(priority = 1)

	public void testOrderIsSuccessfull() {
		Assert.assertEquals(successPage.verifyOrderIsSuccess(),AppConstants.SUCCESS_MESSAGE);
	}

	@Test(priority = 2)
	
	public void testOrderNumberTextShown() {
		Assert.assertTrue(successPage.verifyOrderNumberGenerated());
	}	
	@Test(priority = 3)
	public String testOrderNumberGenerated() {
		orderNumberAtSuccessPage=successPage.getOrderNumberGenerated();
		return orderNumberAtSuccessPage;
	}	

	@Test(priority = 4)
	public void testMyAccountClicking() throws InterruptedException {
		myAccountPage=successPage.clickMyAccountOption(urlAccount);
	}
}
