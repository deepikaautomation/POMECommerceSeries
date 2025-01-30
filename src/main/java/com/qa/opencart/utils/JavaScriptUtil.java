package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
private WebDriver driver;
JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	
	public void  drawBorder(WebElement ele) {

		

		
	   js.executeScript("arguments[0].style.border='3px solid red';",ele);

	}
	
	public void flash(WebElement element)  {
		String bgcolor=element.getCssValue("backgroundColor");
		for(int i=0;i<10;i++) {
			changeColor("rgb(0,200,0",element);
			changeColor(bgcolor,element);
			
		}
	}
		
		private void changeColor(String color,WebElement element) {
			js.executeScript("arguments[0].style.backgroundColor = '" +color + "'", element);
			
		}
		
	}


