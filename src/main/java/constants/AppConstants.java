package constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	public static final String LOGIN_PAGE_TITLE = "Customer Login";
//	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
//	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String REGISTRATION_SUCCESSFULL_MENU = "Welcome";
	public static final String BAGS_PAGE_TITLE ="Bags";
	public static final String SUCCESS_MESSAGE ="Thank you for your purchase!";
//	public static final List<String> EXPECTED_ACC_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
//			"My Affiliate Account", "Newsletter");
	public static final CharSequence USER_REGISTER_SUCCESS_MESSG = "Thank you for registering with Main Website Store.";
	public static final String LOGOUT_TEXT="Account Logout";
	
	public static final List<String> EXPECTED_MY_ACCOUNT_LIST=Arrays.asList("Edit your account information","Change your password",
            "Modify your address book entries","Modify your wish list");
	
	public static final List<String> EXPECTED_MY_ORDERS_LIST=Arrays.asList("View your order history", 
            "Downloads", "Your Reward Points", "View your return requests", "Your Transactions", "Recurring payments");
	public static final List<String> EXPECTED_MY_AFFILIATED_ACCOUNT=Arrays.asList("Register for an affiliate account");
	
	public static final List<String> EXPECTED_NEWSLETTER=Arrays.asList("Subscribe / unsubscribe to newsletter");
	/* Default timeout value */
	public static final int SHORT_TIME_OUT = 5;
	public static final int MEDIUM_TIME_OUT = 10;
	public static final int LONG_TIME_OUT = 15;
	public static final int BIG_LONG_TIME_OUT = 20;
}