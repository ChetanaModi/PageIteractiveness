package com.magento.reusableComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.utilities.DriverManager;

/**
 * @author: Mohan N
 */
public class CommonUtilities {
	
	//Custom sendKeys method-> To log sendKeys message for every occurrence.
	public void sendKeys_custom(WebElement element, String valueToBeSent, String fieldName) {
		try {
			element.clear();
			element.sendKeys(valueToBeSent);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Ented value as: "+valueToBeSent);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
			Assert.assertTrue(false);
		}
	}

	//custom click method to log evey click action in to extent report
	public void click_custom(WebElement element, String fieldName) {
		try {
			element.click();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	
	//javascript click method to log every click action in to extent report
		public void click_javascript(WebElement element, String fieldName) {
			try {
				((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", element);
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
			} catch (Exception e) {
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
			}
		}

	//clear data from field
	public void clear_custom(WebElement element,String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Data Cleared Successfully! ");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);
			Assert.assertTrue(false);
		} 
	}

	//custom mouseHover 
	public void moveToElement_custom(WebElement element,String fieldName){
		try{
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());		
			actions.moveToElement(element).build().perform();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		}catch(Exception e){
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);
			Assert.assertTrue(false);
		}
	}

	//Select dropdown value value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement element, String ddVisibleText, String fieldName) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
			Assert.assertTrue(false);
		}
	}

	//Select dropdown value value by value
	public void selectDropDownByValue_custom(WebElement element, String ddValue, String fieldName) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddValue);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	//get all the options from select dropdown
	public List<WebElement> getOptions(Select selectDropdown) {
		return selectDropdown.getOptions();
	}


	//Get text from WebElement
	public String getText_custom(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Text retried is: "+ text);
			return text;
		} catch (Exception e) {		
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"==> Text not retried due to exception: "+ e);
			Assert.assertTrue(false);
		}
		return text;
	}
	
	//	Validations
	//check if element is Present
	public boolean isElementPresent_custom(WebElement element,String fieldName){
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Presence of field is: "+ flag);
			return flag;
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " +fieldName +" not tested due to exception: "+e);
			return flag;
		}
	}

	//String Asserts
	public void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if(actualValue.equals(expvalue)) {
				Assert.assertTrue(true);
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
			}else {
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

//	WebDriverWait calls
//	Wait for element to be clickable
	public void waitForElementToBeClickable(WebElement ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 120).until(ExpectedConditions.elementToBeClickable(ele));
	}

//	Wait for element to be visible
	public void waitForElementToBeVisible(WebElement ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 30).until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToBeInVisible(List<WebElement> ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 120).until(ExpectedConditions.invisibilityOfAllElements(ele));
	}
	
	
//	Stop for x milliseconds
	public static void waitFor(int mSecs) throws InterruptedException {
		Thread.sleep(mSecs);
	}
	
//	Scroll operations
	public void scrollDown(int x,int y) {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("window.scrollBy("+x+","+y+")");
	}
	
	public void scrollDownToWebElement(WebElement element) {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollUp() {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("window.scrollTo(0,document.body.scrollTop)");
	}
	
	//for clear browser Cookies
	public void clearBrowserCookies() {
		DriverFactory.getInstance().getDriver().manage().deleteAllCookies();//delete all cookies
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //wait 7 seconds to clear cookies.
	}
	
	//for naviate to home page 
	public void navigateTo_custom(String url) throws InterruptedException {
		try {
			DriverFactory.getInstance().getDriver().navigate().to(url);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Navigated to ==> "+url);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Navigate to : " +url +" due to exception: "+e);
			Assert.assertTrue(false);
		}	
	}

	//for navigate back 
	public void navigateBack_custom() {
		try {
			DriverFactory.getInstance().getDriver().navigate().back();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Navigated Back");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Navigate Back: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	public void moveInsideTheFrame_custom(WebElement frameElement) {
		try {
			 DriverFactory.getInstance().getDriver().switchTo().frame(frameElement);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Inside the Frame");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to come Inside the Frame: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	public void dragandDropFile_custom(WebElement source,WebElement destination) {
		try {
			Actions action=new Actions(DriverFactory.getInstance().getDriver());
			action.dragAndDrop(source, destination).build().perform();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Drag and Drop File Successfully! ");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Drag and Drop File: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	public void uploadTheFiles_custom(String fileName) throws InterruptedException, AWTException{
		Robot rb = new Robot();
		// copying File path to Clipboard
		StringSelection str = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
/*	public boolean waitTillSpinnerDisable(WebDriver driver, By by)
	{		
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		fWait.withTimeout(10, TimeUnit.SECONDS);
		fWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		fWait.ignoring(NoSuchElementException.class);
		
		Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>()
		{
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
			
		//		System.out.println(element.getCssValue("display"));			
				
				if(element.getCssValue("display").equalsIgnoreCase("none")){
					return true;}
				
				return false;
			}
		};
		return fWait.until(func);
	}*/
}
