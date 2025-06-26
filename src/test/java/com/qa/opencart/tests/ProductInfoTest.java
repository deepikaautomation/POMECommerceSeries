package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	
	public void productInfoPageSetUp() {
		
		accountsPage=loginPage.doLogin("deepikadr86@gmail.com", "test@123");
		
	}
	
	@Test
	public void productHeaderText() { 
		
		searchPage=accountsPage.searchProduct("macbook");
		prodInfoPage=searchPage.selectProduct("MacBook Pro");
		Assert.assertEquals(prodInfoPage.getProductText(), "MacBook Pro");
		
	} 
	
	@Test
	public void productInfoTest() {
		searchPage=accountsPage.searchProduct("macbook");
		prodInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String,String> actualProductData=prodInfoPage.getCompleteProductmetaData();
		
		/*
		 * //Assert Hard Assertions-whwn any assertion gets failed immediately the whole
		 * testcases gets terminated and marked as failed
		 * Assert.assertEquals(actualProductData.get("Brand"), "Apple11");
		 * Assert.assertEquals(actualProductData.get("Product Code"), "Product 18");
		 * Assert.assertEquals(actualProductData.get("Reward Points"), "800");
		 * Assert.assertEquals(actualProductData.get("Availability"), "In Stock");
		 * Assert.assertEquals(actualProductData.get("ProductPrice"), "$2,000.00");
		 * Assert.assertEquals(actualProductData.get("ExTaxPrice"), "$2,000.00");
		 */
		
		
		softAssert.assertEquals(actualProductData.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductData.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductData.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductData.get("Availability"), "In Stock");
		softAssert.assertEquals(actualProductData.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(actualProductData.get("ExTaxPrice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getProductImagesCount() {
		
		return new Object[][] {
			{"MacBook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1}
		};
		
	}
	
	@Test (dataProvider = "getProductImagesCount")
	public void getImageCount(String searchKey, String productname,int productCount) {
		
		SearchPage sp=accountsPage.searchProduct(searchKey);
		ProductInfoPage pi=sp.selectProduct(productname);
		
		int actualImagesCouint=pi.getProductImageCount();
		Assert.assertEquals(actualImagesCouint, productCount);
	}

	

}
