package com.gg.log;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class AppLogger {

	private static Properties appProperties = null;
	
	private static Level loglevel ;
	
	private static Level DEBUG = new Level("DEBUG", 500){
		
	};
	private static Level ERROR = new Level("ERROR", 100){
		
	};
	static{
		try{
			InputStream is = AppLogger.class.getClassLoader()
					.getResourceAsStream("app.properties");
			appProperties = new Properties();
			appProperties.load(is);
			loglevel = Level.parse(appProperties.getProperty("LOG_LEVEL"));
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	
	public static void info(String message){
		if(loglevel == Level.INFO)
		System.out.println(message);
	}
	
	public static void debug(String message){
		if(loglevel == DEBUG)
		System.out.println(message);
	}
	
	public static void error(String message){
		if(loglevel == ERROR)
		System.err.println(message);
	}
	
	public static void error(String message, Throwable e){
		if(loglevel == ERROR){
		System.err.println(message + " " + e);
		e.printStackTrace();
		}
	}
}
