package com.magento.testScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;
import com.magento.utilities.ExcelUtility;

//	find whether sheet exists
public class TestPageInteractiveForProducts extends TestBase{
	
ExcelUtility excelUtility = new ExcelUtility();
BasePage bp = new BasePage();
PDPProductOptionsPage pdp=new PDPProductOptionsPage();

	@Test(dataProvider = "getData")
	public void productPageInteractive(Hashtable<String,String> data) throws Throwable{
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		Reporter.log("Product : "+data.get("Products"));
	
		bp.waitForPageLoad();
		bp.loginFromHeader(PropertiesUtility.getPropertyValueByKey("defaultUserName"), PropertiesUtility.getPropertyValueByKey("defaultPassword"));
		pdp.navigateToProductURLAndClockTheTime(data.get("ProductURL"),data.get("Products"),PropertiesUtility.getPropertyValueByKey("visit1"));
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.clickHomeLink();
		pdp.navigateToProductURLAndClockTheTime(data.get("ProductURL"),data.get("Products"),PropertiesUtility.getPropertyValueByKey("visit2"));
	}

	@DataProvider
	public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
		String sheetName = m.getName();
		int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), sheetName);
		int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), sheetName);
		rows = rows+1;
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for(int colNum = 0; colNum < columns; colNum++) {
				table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), sheetName, rowNum, colNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}
}