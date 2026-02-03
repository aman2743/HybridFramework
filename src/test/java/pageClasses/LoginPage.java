package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import constants.AppConstants;
import utility.ElementUtil;

public class LoginPage  {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By login=By.linkText("Sign In");
	private By forgotPasswordLink=By.xpath("//a[@class='action remind']/child::span");
	private By email=By.id("email");
	private By pwd=By.id("pass");
	private By signIn=By.xpath("(//button[@id='send2'])[1]/child::span");
	private By createAccount = By.linkText("Create an Account");
//	private By myaccount=By.xpath("//strong[text()='My Account']");
	private By welcome=By.xpath("//span[contains(text(),'Welcome')]");
	
//  Login page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.clickElementWhenReady(login,AppConstants.LONG_TIME_OUT);
	}

	public String getLoginPageTitle() {
		String expectedTitle = eleUtil.waitForTitleIsDisplayed(AppConstants.LOGIN_PAGE_TITLE,
				AppConstants.SHORT_TIME_OUT);
		System.out.println("Page Title is " + expectedTitle);
		return expectedTitle;
	}

	public boolean isForgotPasswordLinkExists() {
		return eleUtil.waitForElementVisible(forgotPasswordLink, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}

	public AccountsPage doLogin(String emailId, String password) {
		eleUtil.doSendKeys(email, emailId);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(signIn);
		return new AccountsPage(driver);
	}
	public void userLogin(String emailId, String password) {
		eleUtil.doSendKeys(email, emailId);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(signIn);
	}
	
	public boolean isWelcomeTextShown() {
		return eleUtil.waitForElementVisible(welcome, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickElementWhenReady(createAccount, AppConstants.MEDIUM_TIME_OUT);
		return new RegisterPage(driver);
	}
}
