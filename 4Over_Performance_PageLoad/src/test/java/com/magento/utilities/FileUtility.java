package com.magento.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public static Properties config = new Properties();
	public static FileInputStream fis;
	
	public static Properties readProperty() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		return config;
	}
}
