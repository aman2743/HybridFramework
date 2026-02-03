package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;

public class ReviewAndPaymentTest extends BaseClass {
	 String company=prop.getProperty("companyName");
	 String address1=prop.getProperty("Address1");
	 String city=prop.getProperty("city");
	 String country=prop.getProperty("country");
	 String state=prop.getProperty("state");
	 String pcode=prop.getProperty("postalCode");
	 String pnumber=prop.getProperty("PhoneNumber");
	 
	@BeforeClass
	public void PageInitReviewAndPayment() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("uname"), prop.getProperty("password"));
		checkOutPage = accPage.CheckoutPageNavigation();
		reviewAndPayment = checkOutPage.submitShippingAddressPage(company, address1, city, country, state,pcode,pnumber );
		Thread.sleep(20);
	}

	@Test(priority=1)
	public void verifyPlaceOrderButtonShown() throws InterruptedException {
		Assert.assertTrue(reviewAndPayment.isPlaceOrderButtonDisplayed());
	}
	
	@Test(priority=2)
    public void testPlaceOrderButtonClick() throws InterruptedException {
    	successPage=reviewAndPayment.placeOrderButtonClick();
    }
}
