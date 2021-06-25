package com.magento.utilities;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean root=false;

	public static Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("src"+File.separator+"test"+File.separator+"resources"+File.separator+"properties"+File.separator+"log4j.properties");
		root=true;
		return Logger.getLogger(cls);
	}

}
