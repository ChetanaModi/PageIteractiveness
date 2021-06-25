/*
 * Author: Mohan N
 */
package com.magento.testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	//create WebDriver object for given browser
	public WebDriver createBrowserInstance(String browser){

		WebDriver driver = null;

		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);	
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			driver = new ChromeDriver(options);
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.addArguments("-private");
			fOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new FirefoxDriver(fOptions);
		} if (browser.equalsIgnoreCase("safari")) {
			SafariOptions options = new SafariOptions();
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new SafariDriver(options);
		}
		return driver;
	}
}
