package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By locators
	
	private By emailId=By.cssSelector("input#input-email");
	private By password=By.cssSelector("input#input-password");
	private By forgotPwd=By.linkText("Forgotten Password");
	private By submitBtn=By.xpath("//input[@type='submit']");  
	private By registerLink=By.linkText("Register");
	
	
	//2.constructor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	
	//3.page action methods
	
	public String getLoginPageTitle() {
		//String title=driver.getTitle();
		String title=eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title:  " + title);
		return title;
		
	}
	
	
	public String getLoginPageUrl() {
		//String url=driver.getCurrentUrl();
		String url=eleUtil.waitForUrlContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page URL:  " + url);
		return url;
		
	}
	
	public boolean isForgotPwdLinkExist() {
		
		
		//return driver.findElement(forgotPwd).isDisplayed();
		
		return eleUtil.waitUntilVisible(forgotPwd, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		
		
	}
	
	public AccountsPage doLogin(String un,String pwd) {
		
		//driver.findElement(emailId).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(submitBtn).click();
		
		//eleUtil.doSendKeys(emailId, un);
		eleUtil.waitUntilVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(submitBtn);
		return new AccountsPage(driver);
		
	}
	
	public RegisterPage navigateToRegisterPage() {
		
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
}
