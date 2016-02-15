package com.gg.expression.evalutor;

import java.util.regex.Pattern;

import com.gg.expression.converter.ExpressionOutput;


public class QuestionEvaluator implements IExpressionEvaluator<String>, IInputMatcher {

	/**
	 * the below regex returns true for both of these inputs, have to find a better way. 
	 * for now handling by replacing with empty in evaluate method
	 * input1: how much is how many Credits is pish tegj glob glob ?
	 * input2: how many Credits is how much is pish tegj glob glob ?
	 *
	 */
	
	
	private static Pattern PATTERN = Pattern.compile("(how much is|how many Credits is){1}\\s+([a-zA-Z]+\\s{0,}){1,}\\?");
	
	@Override
	public String evaluate(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean match(String s) {
		return PATTERN.matcher(s).matches();
	}
	
}
