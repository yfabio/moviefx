package com.pro.moviefx.resource;

import java.util.Properties;

public class Resource {
	
	private static final Properties prop = new Properties();
	
	static {
		try {
			prop.load(Resource.class.getResourceAsStream("/prop/app.properties"));
		} catch (Exception e) {			
		}
	}
	
	public static String getValue(String key) {
	 return prop.getProperty(key);	
	}
}
