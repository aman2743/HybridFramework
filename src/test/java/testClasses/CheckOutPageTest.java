package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;

public class CheckOutPageTest extends BaseClass {
	String company = prop.getProperty("companyName");
	String address1 = prop.getProperty("Address1");
	String city = prop.getProperty("city");
	String country = prop.getProperty("country");
	String state = prop.getProperty("state");
	String pcode = prop.getProperty("postalCode");
	String pnumber = prop.getProperty("PhoneNumber");

	@BeforeClass
	public void PageInitCheckOutPage() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("uname"), prop.getProperty("password"));
		checkOutPage = accPage.CheckoutPageNavigation();
		Thread.sleep(20);
	}

	@Test(priority = 1)
	public void shippingAddressPageVerification() {
		Assert.assertTrue(checkOutPage.verifyShippingAddressPage());
	}

	@Test(priority = 2)
	public void testShippingAddressPage() throws InterruptedException {
//		System.out.println("Inside Test2");
		Thread.sleep(5000);
		boolean b=checkOutPage.verifyCompanyNameFieldIsDisplayed();
//		System.out.println(b);
		if (b==true) {
			checkOutPage.shippingAddress(company, address1, city);
			checkOutPage.SelectCountry(country);
			checkOutPage.SelectState(state);
			checkOutPage.postalCode(pcode);
			checkOutPage.phoneNumber(pnumber);
			checkOutPage.clickFirstNext();
		} else {
			checkOutPage.clickFirstNext();
		}
	}

	@Test(priority = 3)
	public void verifyPaymentsMethodPageIsDisplayed() {
		Assert.assertTrue(checkOutPage.verifyPaymentsMethodPage());
	}

}
