package com.magento.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class PDPProductOptionsPage extends TestBase{
	By homeLink=By.linkText("Home");
	By allDropdowns=By.xpath("//div[@id='product-options-wrapper']//select");
	By firstDropdown=By.xpath("(//div[@id='product-options-wrapper']//select)[1]");
	
	//for click home page link
	public void clickHomeLink() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(homeLink),"Home");
	}
	
	//for select limited dropdown
	public void selectAnOptionInAllTheDropdowns() throws InterruptedException {
		List<WebElement> dropdownList=DriverFactory.getInstance().getDriver().findElements(allDropdowns);
		if(dropdownList.size()>4) {
			for(int i=0;i<=3;i++) {
				if(dropdownList.get(i).isDisplayed()) {
					Select dropdownMenu = new Select(dropdownList.get(i));
					List<WebElement> allOptions = getOptions(dropdownMenu);
					for(int j=1;j<allOptions.size();j++) {
						if(allOptions.get(j).isEnabled())
						{
							dropdownMenu.selectByVisibleText(allOptions.get(j).getText());
							ExtentFactory.getInstance().getExtent().log(Status.INFO,allOptions.get(j).getText());
							break;
						}
					}
				}
			}
		}
		else {
			for(WebElement dropdown:dropdownList) {
				if(dropdown.isDisplayed()) {
					Select dropdownMenu = new Select(dropdown);
					List<WebElement> allOptions = getOptions(dropdownMenu);
					for(int i=1;i<allOptions.size();i++) {
						if(allOptions.get(i).isEnabled())
						{
							dropdownMenu.selectByVisibleText(allOptions.get(i).getText());
							ExtentFactory.getInstance().getExtent().log(Status.INFO,allOptions.get(i).getText());
							break;
						}
					}
				}
			}
		}
		waitFor(Integer.parseInt(PropertiesUtility.getPropertyValueByKey("waitForOptionStore")));
	}
	
	//for navigate to ProductURL and clock the time
	public void navigateToProductURLAndClockTheTime(String url,String product,String visit) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		navigateTo_custom(url);
		WebElement selectFirstDropdown=DriverFactory.getInstance().getDriver().findElement(firstDropdown);
		waitForElementToBeClickable(selectFirstDropdown);
		long endTime = System.currentTimeMillis();
		//log the time in the report
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "WaitTillPageReadyState for Product : "+product+" <-----> "+visit+" Timer  : "+ (endTime-startTime)+" ms");
		Reporter.log("WaitTillPageReadyState for Product : "+product+" <-----> "+visit+" Timer  : "+ (endTime-startTime)+" ms");
	}
}
