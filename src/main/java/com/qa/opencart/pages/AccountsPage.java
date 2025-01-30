package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;


public class AccountsPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By logo=By.xpath("//img[@title='naveenopencart']");
    private By logoutLink=By.linkText("Logout");
	private By accHeaders=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchPdt=By.cssSelector("#search button");
	
	
	
	public AccountsPage(WebDriver driver) {
		
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	
	
	public boolean isLogoExist() {
		 return driver.findElement(logo).isDisplayed();
	}
	
	public boolean logoutLinkExist() {
		
		return driver.findElement(logoutLink).isDisplayed();
		
	}
	
	public String getAccPageTitle() {
		//String title=driver.getTitle();
		String title=eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		
		System.out.println("Account page title is===  >   " + title);
		return title;
	}
	
	public String getAccPageUrl() {
		//String url=driver.getCurrentUrl();
		String url=eleUtil.waitForUrlContainsandFetch(AppConstants.DEFAULT_LONG_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account page URL is=== >   " + url);
		return url;
	}
	
	public boolean searchExist() {
		
		//return driver.findElement(search).isDisplayed();
		
		return eleUtil.isElementDisplayed(search);
		
	}
	
	public List<String> getAccountPageHeadersList() {
		
		//List<WebElement> accHeaderList=driver.findElements(accHeaders);
		List<WebElement> accHeaderList=eleUtil.waitvisibilityOfAllElements(AppConstants.DEFAULT_MEDIUM_TIME_OUT, accHeaders);
		List<String> accHeadersTextList=new ArrayList<String>();
		for(WebElement e: accHeaderList) {
			
		      String text=e.getText();
		      accHeadersTextList.add(text);
	}
		return accHeadersTextList;
	}
	
	public SearchPage searchProduct(String searchKey) {
		
		if(searchExist()) {
			
			eleUtil.doActionSendKeys(search, searchKey);
			eleUtil.doClick(searchPdt);
			
			return new SearchPage(driver);
			
			
		}
		else {
			System.out.println("Search element not exist");
			return null;
		}
		
	}
	
	

}
