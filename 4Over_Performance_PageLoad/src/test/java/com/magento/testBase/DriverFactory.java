/**
 * Author: Mohan N
 */
package com.magento.testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	//Singleton Design Pattern
	//private constructor = nobody can create object of this class
	private DriverFactory() {
		
	}
	
	private static DriverFactory instance  = new DriverFactory();
	
	public static DriverFactory getInstance() {
		return instance;
	}
	
	
	//Factory Design Pattern --> define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void setDriver(WebDriver driverParm) {
		driver.set(driverParm);
	}
	
	
	public void closeBrowser() {
		//		driver.get().quit();
		driver.get().close();
		try{
			driver.get().quit();
		}catch (Exception e){
			
		}
		driver.remove();
	}
}
