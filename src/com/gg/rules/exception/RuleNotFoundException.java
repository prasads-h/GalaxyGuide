package com.gg.rules.exception;

public class RuleNotFoundException extends ConversionException{

	public RuleNotFoundException(char c) {
		super("Rule not found " + c);
	}
}
