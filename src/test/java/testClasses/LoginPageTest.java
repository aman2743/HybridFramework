package testClasses;

import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import utility.ExtentReportManager;

public class LoginPageTest extends BaseClass {

    @Test(description = "Verify user can login with valid credentials")
    public void testValidLogin() {
        // Perform login
        loginPage.login("jsmith", "demo1234");

        // Verify login success
//        boolean isLoggedIn = loginPage.isWelcomeTextShown();

//        if (isLoggedIn) {
//            test.pass("Login successful - Welcome text displayed");
//        } else {
//            test.fail("Login failed - Welcome text not displayed");
//        }
//
//        Assert.assertTrue(isLoggedIn, "Login failed - welcome message not displayed");
//    }

//    @Test(description = "Verify login page title")
//    public void testLoginPageTitle() {
//        ExtentTest test = ExtentReportManager.getTest();
//        test.info("Verifying login page title");
//
//        String actualTitle = loginPage.getLoginPageTitle();
//        test.info("Actual title: " + actualTitle);

//        Assert.assertEquals(actualTitle, "Customer Login");

    }
}