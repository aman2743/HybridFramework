package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import constants.AppConstants;


public class AccountsPageTest extends BaseClass {
	
	@BeforeClass
	public void SearchAndFilterSetup() {
		accPage = loginPage.doLogin(prop.getProperty("uname"),prop.getProperty("password"));
	}

	@Test (priority=1)
	public void performSearch() throws InterruptedException {
		accPage.doSearch();
	}
	
	@Test(priority=2)
	public void BagsPageTitleTest() throws InterruptedException {
		Assert.assertEquals(accPage.verifyBagsPage(),AppConstants.BAGS_PAGE_TITLE);
	}
	
	@Test(priority=3)
	public void checkOutPageTest() throws InterruptedException {
		accPage.clickOnMyCart();
	}
	
	@Test(priority=4)
	public void verifyCheckOutButtonTest() {
		Assert.assertTrue(accPage.verifyProceedToCheckoutButton());
	}
	@Test (priority=5)
	public void verifyUserNavigatedToCheckOutPage() {
		accPage.clickOnProceedToCheckoutButton();
	}

}
