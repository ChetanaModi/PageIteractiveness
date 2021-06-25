package com.magento.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.TestBase;

public class BasePage extends TestBase{
	
	By signInLink=By.xpath("(//span[text()='Sign In'])[1]/..");
	By emailTextbox=By.id("login-dropdown-customer-email");
	By passwordTextbox=By.id("login-dropdown-pass");
	By signInButton=By.xpath("(//span[text()='Sign In'])[2]/..");
	By customerLoggedIn = By.id("customer-logged-in");
	By myAccountLink=By.xpath("(//li[@class='customer-welcome']//button)[1]");
	By logoutLink=By.xpath("(//span[text()='Logout'])[1]");
	By liveChatButton=By.xpath("//span[text()='Live Chat']/..");

	public void clickSignInLink() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(signInLink), "sign in link");
	}
	
	public void enterEmail(String emailText) {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(emailTextbox), emailText, "email");
	}
	
	public void enterPassword(String passwordText) {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(passwordTextbox), passwordText, "password");
	}

	public void clickSignInButton() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(signInButton), "sign in button");
	}
	
	public void clickMyAccountLink() {
		waitForElementToBeClickable(DriverFactory.getInstance().getDriver().findElement(liveChatButton));
		waitForElementToBeClickable(DriverFactory.getInstance().getDriver().findElement(myAccountLink));
		click_custom(DriverFactory.getInstance().getDriver().findElement(myAccountLink), "my account link");
	}
	
	public void clickLogoutLink() {
	//	WebElement logoutLink = DriverFactory.getInstance().getDriver().findElement(logoutLink1);
		waitForElementToBeClickable(DriverFactory.getInstance().getDriver().findElement(logoutLink));
		click_custom(DriverFactory.getInstance().getDriver().findElement(logoutLink),"Logout Link");
	}
	
	public void waitForPageLoad() throws InterruptedException {
		waitForElementToBeClickable(DriverFactory.getInstance().getDriver().findElement(liveChatButton));
		waitFor(Integer.parseInt(PropertiesUtility.getPropertyValueByKey("waitForPageLoad")));
	}
	
	public void loginFromHeader(String emailText, String passwordText) {
		clickSignInLink();
		enterEmail(emailText);
		enterPassword(passwordText);
		clickSignInButton();
		waitForElementToBeVisible(DriverFactory.getInstance().getDriver().findElement(customerLoggedIn));
		Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(myAccountLink).isDisplayed());
	}
	
	public void logout() {
		clickMyAccountLink();
		clickLogoutLink();
	}
}
