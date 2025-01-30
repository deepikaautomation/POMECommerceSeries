package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By email=By.id("input-email");
	private By confirmpassword=By.id("input-confirm");
	private By subscribeYes=By.xpath(("//label[@class='radio-inline']/input[@type='radio' and @value='1']"));
	private By subscribeNo=By.xpath(("//label[@class='radio-inline']/input[@type='radio' and @value='0']"));
	private By privacyPolicy=By.xpath("//input[@name='agree']");
	//private By continueBtn=By.xpath("//input[@type='submit' and @value='Continue']");
	private By continueBtn=By.xpath("//a[@class='agree']/following-sibling::input[@type='submit']");
	private By successMsg=By.cssSelector("div#common-success h1");
	private By logOut=By.linkText("Logout");
	private By register=By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
		
		
	}
	
	public String getLoginPageTitle() {
		
		String title=eleUtil.waitForTitleContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.REGISTER_PAGE_TITLE_VALUE);
		System.out.println("Registration page title:  " + title);
		return title;
		
	}
	
	public String getLoginPageUrl() {
		
		String url=eleUtil.waitForUrlContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.REGISTER_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page URL:  " + url);
		return url;
	}
	
	
	
	public boolean userRegistration(String firstname,String lastName,String email,String telephone,String password,String subscribe) {
		eleUtil.waitUntilVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstname);
		
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		eleUtil.doSendKeys(this.email, email);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
			
		}else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doActionClick(privacyPolicy);
		eleUtil.doClick(continueBtn);
		
		String successMessage=eleUtil.waitUntilVisible(successMsg, 30).getText();
		
		System.out.println(successMessage);
		if(successMessage.contains(AppConstants.REGISTER_PAGE_SUCCESS_MSG)) {
			eleUtil.doClick(logOut);
			eleUtil.doClick(register);
			return true;
		}else {
			return false;
		}
		
	}

}
