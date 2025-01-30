package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT=10;
	public static final int DEFAULT_SHORT_TIME_OUT=5;
	public static final int DEFAULT_LONG_TIME_OUT=20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE="Account Login11";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE="route=account/login";
	
	public static final String ACCOUNTS_PAGE_TITLE_VALUE="My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE="route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT=4;
	
	public static final List<String> EXPECTED_ACCOUNT_PAGE_HEADERS_LIST=Arrays.asList("My Account","My Orders",
			     															 "My Affiliate Account","Newsletter","Deepika");
	
	public static final String SEARCH_PAGE_TITLE_VALUE="Search - ";
	public static final String SEARCH_PAGE_URL_FRACTION_VALUE="search&search=";
	
	
	
	public static final String REGISTER_PAGE_TITLE_VALUE="Register Account";
	public static final String REGISTER_PAGE_URL_FRACTION_VALUE="route=account/register";
	public static final String REGISTER_PAGE_SUCCESS_MSG="Your Account Has Been Created!";
    public static final String REG_SHEET_NAME="register";
    public static final String ACC_PRODUCT_SRCH_SHEET_NAME="searchproduct";

}
