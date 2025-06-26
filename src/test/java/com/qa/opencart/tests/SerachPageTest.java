package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

public class SerachPageTest extends BaseTest {
	
	@BeforeClass
	public void searchPageSetUp() {
		loginPage.doLogin("deepikadr86@gmail.com", "test@123");
	}
	
	@DataProvider
	public Object[][] getsearchData(){
		return new Object[][]{{"MacBook Pro"},
			{"iPhone"}};
		}
		
	
	
	
	@Test(dataProvider="getsearchData")
	public void addToCartTest(String prodctname) {
		SearchPage sp=accountsPage.searchProduct(prodctname);
		ProductInfoPage pi=sp.selectProduct(prodctname);
		
		
		
			String text =pi.getAddtoCart();
		  Assert.assertEquals(text, "Success: You have added "+ prodctname + " to your shopping cart!");
		  	
		 
		
	}
	

}
