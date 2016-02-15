package com.gg.expression.evalutor;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gg.expression.converter.RomanToArabicConverter;
import com.gg.log.AppLogger;
import com.gg.rules.exception.ConversionException;
import com.gg.rules.exception.EvaluatorException;
import com.gg.rules.exception.UnavailableWordException;
import com.gg.storage.DBFactory;


public class QuestionEvaluator implements IExpressionEvaluator<String>, IInputMatcher {

	/**
	 * the below regex returns true for both of these inputs, have to find a better way. 
	 * for now handling by replacing with empty in evaluate method
	 * input1: how much is how many Credits is pish tegj glob glob ?
	 * input2: how many Credits is how much is pish tegj glob glob ?
	 *
	 */
	
	
	private static Pattern PATTERN = Pattern.compile("(how much is|how many Credits is){1}\\s+(([a-zA-Z]+\\s{0,}){1,})\\?");
	
	@Override
	public String evaluate(String s) throws EvaluatorException {
		// TODO Auto-generated method stub
		Matcher matcher = PATTERN.matcher(s);
		String result = null;
		try{
		if(matcher.find()){
			AppLogger.debug(" group count " + matcher.groupCount());
			AppLogger.debug(" group 1 " + matcher.group(1));
			AppLogger.debug(" group 2 " + matcher.group(2));
			
			String expressionGroup = matcher.group(2);
			StringTokenizer tokenizer = new StringTokenizer(expressionGroup, " ");
			//List<Character> evalList = new ArrayList<Character>();
			StringBuilder romanExp = new StringBuilder();
			
			while(tokenizer.hasMoreTokens()){
				String token = tokenizer.nextToken();
				Character romanLetter = DBFactory.getAliasDBHandler().getRomanLetterForAlias(token.trim());
				if((romanLetter == null || "".equals(romanLetter)) 
						&& tokenizer.hasMoreTokens() && romanExp.length()== 0){ // unknow glob Silver is 34 Credits
					throw new UnavailableWordException(token);
				}else if(romanLetter == null && !tokenizer.hasMoreTokens()){// Silver is 34 Credits or glob glob silver
					long sum = 0;
					
						sum = calculateSum(romanExp.toString());
					
					Long creditValue = DBFactory.getCreditDBHandler().getCreditValueForKey(token);
					
					if(creditValue == null){
						throw new UnavailableWordException("Please add creditValue for : " + token);
					}
					AppLogger.debug("sum for expression group " + expressionGroup + " is " + sum);
					long newValue = Long.valueOf(creditValue);
					//if(sum != 0)
					newValue = (newValue * sum);					
						
					//DBFactory.getCreditDBHandler().insertNewKey(token, newValue);
					return expressionGroup.trim() + " is " +String.valueOf(newValue) + " Credits";
				}else if(romanLetter != null){// glob glob Silver is 34 Credits
					//TODO evaluate expression
					//evalList.add(romanLetter);
					romanExp.append(romanLetter);
				}
			}
		
			result = expressionGroup + " is " + calculateSum(romanExp.toString());// glob glob pish tegj liek only roman
		}else{
			AppLogger.error("find returned false in QuestionEvaluator " + s);
		}
		
		}catch(ConversionException ce){
			throw new EvaluatorException(ce);
		}
		
		return result;
	}
	
	private long calculateSum(String romanExp) throws ConversionException{
		int sum = 0;
		/*for(Character letter: evalList){
			sum = sum + DBFactory.getAliasDBHandler().getCreditValue(letter);
		}*/
		
		return new RomanToArabicConverter().convert(romanExp);
		
	}
	
	@Override
	public boolean match(String s) {
		return PATTERN.matcher(s).matches();
	}	
}
