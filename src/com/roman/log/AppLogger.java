package com.roman.log;

public class AppLogger {

	public static void info(String message){
		System.out.println(message);
	}
	
	public static void debug(String message){
		System.out.println(message);
	}
	
	public static void error(String message){
		System.err.println(message);
	}
	
	public static void error(String message, Throwable e){
		System.err.println(message + " " + e);
		e.printStackTrace();
	}
}
