package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest{
	
	
	@BeforeClass
	
	public void accPageSetUp() {
		
		accountsPage=loginPage.doLogin("deepikadr86@gmail.com", "test@123");
		
	}
	
	
	@Test
	public void logoExist() {
		
		Assert.assertTrue(accountsPage.isLogoExist());
	
		
		
		
	}
	
	@Test
	public void accPageTitleTest() {
		
		String accTitle=accountsPage.getAccPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		
	}

	@Test
   public void accPageUrlTest() {
		
		String accUrl=accountsPage.getAccPageUrl();
		Assert.assertTrue(accUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
		
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.logoutLinkExist());
	}
	
	@Test 
	public void accPageHeadersTest() {
		List<String> actualAccPageHeadersList=accountsPage.getAccountPageHeadersList();
		System.out.println("Acc PageHeaders List-- " +actualAccPageHeadersList );
		
		Assert.assertEquals(actualAccPageHeadersList.size(), 4);
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getProductData() {
		
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
		
	}
	
	
	
	
	@Test(dataProvider = "getProductData" )
	public void searchProductCountTest(String searchKey) {
		
		searchPage=accountsPage.searchProduct(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount()>0);
		
	}
	
	
	  @DataProvider public Object[][] serachProductData() {
	  
	  return new Object[][] { {"MacBook","MacBook Air"}, {"MacBook","MacBook Pro"},
	  {"iMac","iMac"}, {"Apple","Apple Cinema 30\""},
	  {"Samsung","Samsung SyncMaster 941BW"} };
	  
	  }
	 
	
	@DataProvider
	public Object[][] serachProductExcelData() {
		
		return ExcelUtil.getTestData(AppConstants.ACC_PRODUCT_SRCH_SHEET_NAME);
		
	}
	
	
	@Test(dataProvider = "serachProductExcelData")
	public void searchProductTest(String searchKey,String productName) {
		
		searchPage=accountsPage.searchProduct(searchKey);
		if(searchPage.getSearchProductCount()>0) {
			prodInfoPage=searchPage.selectProduct(productName);
			String actualProductName=prodInfoPage.getProductText();
			Assert.assertEquals(actualProductName, productName);
			
		}
		
		
	}

}
