package com.roman.expression.evalutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.roman.log.AppLogger;
import com.roman.storage.DBFactory;

public class AliasEvalator implements IExpressionEvaluator<String>, IInputMatcher{
	
	//sample input glob is I
	private static Pattern ALIAS_PATTERN = Pattern.compile("([a-zA-Z]+){1}\\s+is\\s+([A-Z])");

	@Override
	public String evaluate(String s) {
		// TODO Auto-generated method stub		
		Matcher matcher = ALIAS_PATTERN.matcher(s);
		if(matcher.find()){
			AppLogger.debug("group count " + matcher.groupCount());
			AppLogger.debug("group count " + matcher.group(1));
			AppLogger.debug("group count " + matcher.group(2));
			String alias = matcher.group(1);
			char romanletter = matcher.group(2).charAt(0);			
			DBFactory.getAliasDBHandler().insertAlias(alias, romanletter);
			
			return "Succesfully inserted alias key : " + alias + " romanletter: " + romanletter;
		}else{
			AppLogger.debug("for some reason find() is not matching check!!!!");
		}
		
		return "failed to insert for input string " + s;
	}
	
	public boolean match(String s){
		
		return ALIAS_PATTERN.matcher(s).matches();
	}
}
