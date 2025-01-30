package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	
	@BeforeClass
	public void regSetUp() {
		
		registerPage=loginPage.navigateToRegisterPage();
		
	}
	
	public String getRandomEmail() {
		return "uiautomation"+ System.currentTimeMillis() +"@opencart.com";
	}
	
	@DataProvider
	public Object[][] getSheetData() {
		
		return ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
		
		
		
	}
	
	
	
	@Test(dataProvider = "getSheetData")
	public void userRegistrationTest(String firstname,String lastname,String telephone,String password,String subscribe) {
		
		//Assert.assertTrue(registerPage.userRegistration("fname", "lname",getRandomEmail(), "87346374634", "pwd123","yes"));
		
		Assert.assertTrue(registerPage.userRegistration(firstname, lastname,getRandomEmail(), lastname, password,subscribe));
		
		
		
		
	}
	

}
