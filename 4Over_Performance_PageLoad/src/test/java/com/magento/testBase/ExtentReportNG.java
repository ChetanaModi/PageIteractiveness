package com.magento.testBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
	
	static ExtentReports extent;

	public static ExtentReports setupExtentReport() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String reportPath = System.getProperty("user.dir")+
				"/reports/ExecutionReport_"+actualDate+".html";
		
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("MagentoTestAutomationReport");
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("PDP Page interactiveness Report");
		
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Test Engineers : ", "Sumit, Suwarna, Chetana, Mohan");
		
		return extent;
	}


}
