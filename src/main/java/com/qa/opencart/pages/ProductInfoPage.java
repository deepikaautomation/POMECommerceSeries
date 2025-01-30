package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

/**
 * 
 */
public class ProductInfoPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeaderText=By.tagName("h1");
	private By ProductImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String,String> productMetaInfo;
	private Map<String,String> productPriceInfo;
	//this is to learn git commands
	private By prstTest=By.name("TEst");
	
	public ProductInfoPage(WebDriver driver) {
		
		
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductText() {
		
		String productText=eleUtil.getlocatortext(productHeaderText);
		System.out.println("ProductInfoName :::" + productText);
		return productText;
		
	}
	
	public int getProductImageCount() {
		
		int productimgCount= eleUtil.waitpresenceOfAllElements(AppConstants.DEFAULT_MEDIUM_TIME_OUT, ProductImages).size();
		System.out.println("Iamges count:::" + productimgCount);
		return productimgCount;
	}
	
	
	
	/*
	 * Brand: Apple 
	 * Product Code: Product 18 
	 * Reward Points: 800 
	 * Availability: In Stock
	 * 
	 */
	private void getProductMetaData() {
		
		
		
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String metaText=e.getText();
			String metaArray[]=metaText.split(":");
			String metaKey=metaArray[0].trim();
			String metaValue=metaArray[1].trim();
			
			productMetaInfo.put(metaKey, metaValue);
		}
	}
		
		private void getProductPriceData() {
			
			
			
			List<WebElement> priceList=eleUtil.getElements(productPriceData);
			String price=priceList.get(0).getText().trim();
			String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
			productMetaInfo.put("ProductPrice", price);
			productMetaInfo.put("ExTaxPrice", exTaxPrice);
		
		
	}
		
		public Map<String,String> getCompleteProductmetaData() {
			
			productMetaInfo=new HashMap<String,String>();  //doesnt have any order
			//productMetaInfo=new LinkedHashMap<String,String>();  this maintains insertion order
			//productMetaInfo=new TreeMap<String,String>();    this maintains sorted order-- alphabetical order
			
			productMetaInfo.put("ProductName", getProductText());
			
			getProductMetaData();
			getProductPriceData();
			System.out.println("Product Full Data :: " + productMetaInfo);
			return productMetaInfo;
			
		}

}
