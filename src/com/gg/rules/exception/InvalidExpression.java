package com.gg.rules.exception;

public class InvalidExpression extends Exception {
	
	public InvalidExpression(Throwable e){
		super(e);
	}
	
	public InvalidExpression(String e){
		super(e);
	}

}
