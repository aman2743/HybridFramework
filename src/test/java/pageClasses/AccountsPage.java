package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import constants.AppConstants;
import utility.ElementUtil;

public class AccountsPage {

	public WebDriver driver;
	private ElementUtil eleUtil;

	private By gear = By.xpath("//span[text()='Gear']");
	private By bags = By.xpath("//span[text()='Bags']");
	private By bagspage = By.xpath("//span[@data-ui-id='page-title-wrapper']");
	private By styleFilter = By.xpath("(//div[@id='narrow-by-list']/child::div)[1]/child::div[text()='Style']");
	private By backpackStyle = By.xpath(
			"(//div[@id='narrow-by-list']/child::div)[1]/child::div[text()='Style']/following-sibling::div/child::ol/child::li[1]/a");
	private By firstItemFromFilter = By
			.xpath("//ol[@class='products list items product-items']/child::li[1]/child::div");
	private By addToCart = By.xpath("//span[text()='Add to Cart']");
	private By mycart = By.xpath("//a[@class='action showcart']/child::span[2]");
	private By proceedToCheckout = By.xpath("//button[@id='top-cart-btn-checkout']");
//	private By popupItem = By.xpath("//div[@id='minicart-content-wrapper']");
//	private By closeButtonIcon = By.xpath("//div[@id='btn-minicart-close']");

//	Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public void doSearch() throws InterruptedException {
		eleUtil.mouseHover(gear);
		eleUtil.doActionsClick(bags);
		eleUtil.clickElementWhenReady(styleFilter, AppConstants.SHORT_TIME_OUT);
		eleUtil.clickElementWhenReady(backpackStyle, AppConstants.SHORT_TIME_OUT);
		eleUtil.mouseHover(firstItemFromFilter);
		eleUtil.waitForElementAndClick(addToCart, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		Thread.sleep(10000);
	}

	public String verifyBagsPage() throws InterruptedException {
		String actualText = eleUtil.waitForElementPresence(bagspage, AppConstants.MEDIUM_TIME_OUT).getText();
		Thread.sleep(10000);
		return actualText;
	}

	public void clickOnMyCart() throws InterruptedException {
		Thread.sleep(10000);
		WebElement we=eleUtil.waitForElementPresence(mycart,AppConstants.LONG_TIME_OUT,AppConstants.SHORT_TIME_OUT);
		we.click();
		
//		WebElement we = eleUtil.getElement(popupItem);
//		if (we.isDisplayed()) {
//			eleUtil.doActionsClick(closeButtonIcon);
//		} else {
//			eleUtil.clickElementWhenReady(mycart, AppConstants.LONG_TIME_OUT);
//		}

	}

	public boolean verifyProceedToCheckoutButton() {
		return eleUtil.waitForElementPresence(proceedToCheckout, AppConstants.LONG_TIME_OUT).isDisplayed();
	}

	public void clickOnProceedToCheckoutButton() {
		eleUtil.clickElementWhenReady(proceedToCheckout, AppConstants.SHORT_TIME_OUT);

	}

	public CheckOutPage CheckoutPageNavigation() throws InterruptedException {
		doSearch();
		eleUtil.clickElementWhenReady(mycart, AppConstants.MEDIUM_TIME_OUT);
		eleUtil.clickElementWhenReady(proceedToCheckout, AppConstants.MEDIUM_TIME_OUT);
		return new CheckOutPage(driver);
	}
}
