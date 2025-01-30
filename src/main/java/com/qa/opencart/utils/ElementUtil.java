package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);

	}
	
	private void checkElementHighLight(WebElement element) {
		
		if(Boolean.parseBoolean(DriverFactory.isHighlight)) {
			jsUtil.flash(element);
		}
		
	}

	public  WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		
		checkElementHighLight(element);
		
		return element;

	}
	
	public  WebElement getElement(By locator,int timeOut) {

		//return driver.findElement(locator);
		
		return waitUntilVisible(locator, timeOut);

	}

	public  void doSendKeys(By locator,String value) {
		
		WebElement ele=getElement(locator);
		ele.clear();
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		
		
		WebElement element=driver.findElement(locator);
		
		checkElementHighLight(element);
		element.click();

	}

	public String  getlocatortext(By locator) {
		return getElement(locator).getText();

	}

	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	} 

	
	//********Select based Dropdown Utils *********************/ 
	public  void doSelectDropdownByIndex(By locator,int index) {

		Select select=new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public  void doSelectDropdownByValue(By locator,String value) {

		Select select=new Select(getElement(locator));
		select.selectByValue(value);

	}

	public  void doSelectDropdownByVisibleText(By locator,String text) {

		Select select=new Select(getElement(locator));
		select.selectByVisibleText(text);

	}
	
	
	//*************ActionSendKeys
	
	public  void doActionSendKeys(By locator,String text) {
		Actions act=new Actions(driver);
		WebElement ele=getElement(locator);
		ele.clear();
		
		act.sendKeys(ele,text).build().perform();
		
	}
	
	public  void doActionClick(By locator) {
		Actions act=new Actions(driver);
		
		act.click(getElement(locator)).build().perform();
		
	}
	
	//********Wait Utils***************
	
	/*An expectation for checking that an element is present on the DOM of a page. 
	 * This does notnecessarily mean that the element is visible.*
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitUntilPresenceofElement(By locator, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	
	/*An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.*
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitUntilVisible(By locator, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkElementHighLight(element);
		return element;
		
	}
	
	
	public  Alert waitForAlertIsPresent(int timeOuts) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOuts));
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public  String alertGetText(int timeOuts) {
		return waitForAlertIsPresent(timeOuts).getText();
		
		
	}
	
	public  void acceptAlert(int timeOuts) {
		waitForAlertIsPresent(timeOuts).accept();
		
		
	}
	
	public  void dismissAlert(int timeOuts) {
		waitForAlertIsPresent(timeOuts).dismiss();
		
		
	}
	
	public  String waitForTitleContainsandFetch(int timeouts,String fractionofText) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(fractionofText));
		return driver.getTitle();
	}
	
	public  String waitForTitleIsandFetch(int timeouts,String text) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs(text));
		return driver.getTitle();
	}
	
	public  String waitForUrlContainsandFetch(int timeouts,String fractionofUrl) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains(fractionofUrl));
		return driver.getCurrentUrl();
	}
	
	public  Boolean waitForUrlIsandFetch(int timeouts,String fractionofUrl) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.urlContains(fractionofUrl));
		
	}

	/**
	 * 
	 * @param timeouts
	 * @param url
	 * @return
	 */

	public  String waitForUrlandFetch(int timeouts,String url) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe(url));
		return driver.getCurrentUrl();
	}
	
	
	public  List<WebElement> waitpresenceOfAllElements(int timeOut, By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
	}
	
	public  List<WebElement> waitvisibilityOfAllElements(int timeOut, By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
	}
	public  void waitframeToBeAvailable(int timeOut, By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		
		
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	
	public  void waitframewithNameToBeAvailable(int timeOut, String frameName) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		
		
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		
	}
	
	/*elementToBeClickable-An expectation for checking an element is visible and enabled such that you can click it.*
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public  void clickWhenReady(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
	
	public  WebElement waitForElementToBeClicakable(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    return  wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	public void doClickActionsAndWait(By locator,int timeOut) {
		WebElement ele=waitForElementToBeClicakable(locator,timeOut);
		Actions act=new Actions(driver);
		act.click(ele).build().perform();
		
	}
	
	
public  WebElement waitForElementtoClickablewithFluentWait(int timeOut,int pollingTime,By locator) {
		
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
	            
	            .withTimeout(Duration.ofSeconds(timeOut))
	            .ignoring(NoSuchElementException.class)
	            .ignoring(NoClassDefFoundError.class)
	            .pollingEvery(Duration.ofSeconds(pollingTime))
	            .withMessage("...not found...");
          return wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}

}
