package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage  {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchProductResults=By.cssSelector("div#content div.product-layout");
	
	

	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	public int getSearchProductCount() {
		
		int productCount= eleUtil.waitvisibilityOfAllElements(AppConstants.DEFAULT_MEDIUM_TIME_OUT, searchProductResults).size();
		System.out.println("Search ProductCount :: " + productCount);
		return productCount;
		
	}
	
	public ProductInfoPage selectProduct(String pdtName) {
		
		By prodLocator=By.linkText(pdtName);
		
		eleUtil.waitUntilVisible(prodLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
		
		
	}
	

	


}
