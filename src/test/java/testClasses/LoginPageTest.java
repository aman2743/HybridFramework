package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import constants.AppConstants;

public class LoginPageTest extends BaseClass {
	public static String uname;
	public static String pwd;

	@Test(priority = 1, enabled = true)
	public void LoginPageTitleTest() {
		String actualText = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualText, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, enabled = true)
	public void ForgotPasswordLinkExistsTest() {
		boolean b = loginPage.isForgotPasswordLinkExists();
		Assert.assertTrue(b);
	}

//	@Test(priority=3,dataProviderClass = ExcelUtil.class, dataProvider = "DataSupplierNew")

	@Test(priority = 3)
	public void LoginTest() throws InterruptedException {
		uname = prop.getProperty("uname");
		pwd = prop.getProperty("password");
//		System.out.println(uname + ":" + pwd);
		loginPage.userLogin(uname, pwd);
		Thread.sleep(3000);
        boolean b=loginPage.isWelcomeTextShown();
        Assert.assertTrue(b,"User Login successfully");
	}
}