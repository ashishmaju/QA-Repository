package com.ndtv.propertiesFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
	public static Properties prop;
	public static String propKey(String key) {
		prop= new Properties();
		try {

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\ndtv\\propertiesFile\\Data.properties");
			prop.load(fis);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error in Loading Property File " + e.getLocalizedMessage());
			return null;
		}
	}
}
