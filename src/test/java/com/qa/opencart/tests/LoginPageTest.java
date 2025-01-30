package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void loginPageTitleTest() {
		
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		
		
		
	}
	
	@Test
	public void loginPageUrlTest() {
		String actualUrl=loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
		
	}
	
	@Test
	public void forgotPwdExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	public void loginTest() {
		accountsPage=loginPage.doLogin("deepikadr86@gmail.com", "test@123");
		Assert.assertTrue(accountsPage.logoutLinkExist());
	}

}
