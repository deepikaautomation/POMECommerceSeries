package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;

	public static String isHighlight;
	
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	/**
	 * this method is initializing the driver on the basis of given browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").toLowerCase().trim();

		System.out.println("browesr name is---->" + browserName);
		
		isHighlight= prop.getProperty("highlight");

		op = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			
			//driver = new ChromeDriver(op.getChromeOptions());
			
			//Driver with thread local
			
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver();
			
			tlDriver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			
			//driver = new SafariDriver();
			
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Pls pass the right browser..." + browserName);

		}

		//driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		//driver.get(prop.getProperty("url"));
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		
		//return driver;
		return getDriver();

	}
	
	/**
	 * THIS METHOD IS RETURNING THE DRIVER WITH THREADLOCAL
	 * @return
	 */
	public static  WebDriver getDriver() {
		
		return tlDriver.get();
		
	}

	/**
	 * this method is reading the properties file
	 * 
	 * @return
	 */
	// mvn clean install-Denv="dev"
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("running tests on env: " + envName);
		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Plz pass right env");
					throw new FrameworkException("INVALID EXCEPTION");

				}
			}

			// try {
			// FileInputStream ip=new
			// FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		
	File srcFile=	((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path=System.getProperty("user.dir")+"/screenshot/"+ methodName +"_"+System.currentTimeMillis() + ".png";
	
	File destinationFile=new File(path);
	
	try {
		FileHandler.copy(srcFile,destinationFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return path;
	
	}
	
	
	

}
