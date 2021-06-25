package com.magento.testBase;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	//Singleton Design Pattern
	//private constructor = nobody can create object of this class
	private ExtentFactory() {
		
	}
	
	private static ExtentFactory instance  = new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
	
	//Factory Design Pattern >> define separate factory methods for creating objects and create objects by calling those methods
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	
	public void removeExtentObject() {
		extent.remove();
	}
}
