package com.gg.utils;

public class StringUtils {

	public static boolean isNotEmpty(String s){
		return s!= null && !"".equals(s);
	}
	
	public static boolean isEmpty(String s){
		return s== null || "".equals(s);
	}
	
}
