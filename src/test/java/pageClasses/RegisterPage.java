package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import constants.AppConstants;
import utility.ElementUtil;

public class RegisterPage {
	public WebDriver driver;
	private ElementUtil eleUtil;
	
//  Registration page constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
    private By fname=By.id("firstname");
    private By lname=By.id("lastname");
    private By email=By.id("email_address");
    private By password=By.id("password");
    private By confirmPassword=By.id("password-confirmation");
    private By createButton=By.xpath("(//button[@type='submit'])[2]/child::span");
    private By successmessage=By.xpath("//div[@data-ui-id='message-success']/child::div");
    private By welcomeText=By.xpath("//span[contains(text(),'Welcome')]");
    
    public boolean Registration(String fnamevalue,String lnamevalue,String emailvalue,String pwd,String confirmpwd) {
    	eleUtil.doSendKeys(fname,fnamevalue);
    	eleUtil.doSendKeys(lname, lnamevalue);
    	eleUtil.doSendKeys(email, emailvalue);
    	eleUtil.doSendKeys(password,pwd);
    	eleUtil.doSendKeys(confirmPassword,confirmpwd);
    	eleUtil.doClick(createButton);
    	
    	String actualMessage=eleUtil.waitForElementVisible(successmessage,AppConstants.LONG_TIME_OUT).getText();
//    	System.out.println(actualMessage);
    	if(actualMessage.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)){
    	    return true;
    	}
    	 else {
    	   return false;
    }
    	}
    
    public String getHeader() {
    	String actualText=eleUtil.getElement(welcomeText).getText();
    	return actualText;
    }
    
    public String getData() {
    	return eleUtil.getLocalDateTime();
    }
}


