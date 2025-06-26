package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchPage searchPage;
	protected ProductInfoPage prodInfoPage;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional String browserName) {
		
		df=new DriverFactory();
		prop=df.initProp();
		
		//This below line of code is directly hard coding the browser name
		//driver=df.initDriver("chrome");
		
		//check if browser parameter is coming testng.xml
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			
		}
		
		driver=df.initDriver(prop);
		
		
		loginPage=new LoginPage(driver);
		
		accountsPage=new AccountsPage(driver);
		softAssert=new SoftAssert();
		
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
