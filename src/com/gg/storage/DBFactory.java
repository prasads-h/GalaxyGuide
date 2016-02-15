package com.gg.storage;

public class DBFactory {
	
	

	public static IAliasDBHandler getAliasDBHandler(){
		return new DBHandler();
	}
	
	public static ICreditDBHandler getCreditDBHandler(){
		return new DBHandler();
	}
	
	public static IRuleDBHandler getRulesDBHandler(){
		return new DBHandler();
	}
}
