package testClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseClass;
import constants.AppConstants;

public class RegisterPageTest extends BaseClass {
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider(name = "gerUserResTestData")
	public Object[][] gerUserResTestData() {
		return new Object[][] { { "FN" + regPage.getData(), "LN" + regPage.getData(),
				"demo" + regPage.getData() + "@test.com", "pw@" + regPage.getData(), "pw@" + regPage.getData() }, };
	}

	@Test(dataProvider = "gerUserResTestData", priority = 1)
	public void userRegisterTest(String fnamevalue, String lnamevalue, String emailvalue, String pwd,
			String confirmpwd) {
		Assert.assertTrue(regPage.Registration(fnamevalue, lnamevalue, emailvalue, pwd, confirmpwd));
	}

	@Test(priority = 2)
	public void verifyUserRegistered() {
		Assert.assertEquals(regPage.getHeader(), AppConstants.REGISTRATION_SUCCESSFULL_MENU);
	}

}
