package com.magento.testBase;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.magento.reusableComponents.CommonUtilities;
import com.magento.reusableComponents.PropertiesUtility;

/**
 * @author: Mohan N
 */
public class TestBase extends CommonUtilities {
	BrowserFactory bf = new BrowserFactory();
	
	@BeforeMethod
	public void launchApplication() throws Exception {
		String browser = PropertiesUtility.getPropertyValueByKey("browser");
		String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().navigate().to(url);
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {	
		DriverFactory.getInstance().closeBrowser();
	}
}
