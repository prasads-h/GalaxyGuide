package com.gg.expression.evalutor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gg.expression.converter.RomanToArabicConverter;
import com.gg.log.AppLogger;
import com.gg.rules.exception.ConversionException;
import com.gg.rules.exception.EvaluatorException;
import com.gg.rules.exception.UnavailableWordException;
import com.gg.storage.DBFactory;

//TEST PENDING

/**
 * Assumptions:
 * in glob glob Silver, Unknown string like Silver comes at the end of the group
 * Credit values are only in integers
 * @author Hari Prasad
 *
 */
public class AssignmentEvaluator implements IExpressionEvaluator<String>, IInputMatcher {

	//glob glob Silver is 34 Credits
	private static Pattern PATTERN = Pattern.compile("(([a-zA-Z]+\\s+){1,})is\\s+([\\d]+)\\s+Credits");
	
	@Override
	public String evaluate(String s) throws EvaluatorException{
		// TODO Auto-generated method stub
		
		Matcher matcher = PATTERN.matcher(s);
		matcher.reset();
		if(matcher.find()){
			AppLogger.debug("Assignemtn Matcher " +String.valueOf(matcher.groupCount()));
			AppLogger.debug("group1 " +String.valueOf(matcher.group(1)));
			AppLogger.debug("group2 " + String.valueOf(matcher.group(3)));
			
			String expressionGroup = matcher.group(1);
			String creditValue = matcher.group(3);
			
			StringTokenizer tokenizer = new StringTokenizer(expressionGroup, " ");
			//List<Character> evalList = new ArrayList<Character>();
			
			
			StringBuilder romanExp = new StringBuilder();
			while(tokenizer.hasMoreTokens()){
				String token = tokenizer.nextToken();
				Character romanLetter = DBFactory.getAliasDBHandler().getRomanLetterForAlias(token.trim());
				if((romanLetter == null || "".equals(romanLetter)) 
						&& tokenizer.hasMoreTokens()){ // unknow glob Silver is 34 Credits
					throw new UnavailableWordException(token);
				}else if(romanLetter == null && !tokenizer.hasMoreTokens()){// Silver is 34 Credits
					long sum = 0;
					try{
						sum = calculateSum(romanExp.toString());
					}catch(ConversionException ce){
						throw new EvaluatorException(ce);
					}
					
					AppLogger.debug("sum for expression group " + expressionGroup + " is " + sum);
					long newValue = Long.valueOf(creditValue);
					if(sum != 0)
						newValue = (newValue /sum);					
						
					DBFactory.getCreditDBHandler().insertNewKey(token, newValue);
					AppLogger.debug("inserted token " + token + " sum " + newValue);
					return "Inserted "  + token + " with value "+ newValue;
				}else if(romanLetter != null){// glob glob Silver is 34 Credits
					//TODO evaluate expression
					//evalList.add(romanLetter);
					romanExp.append(romanLetter);
				}				
			}						
		}else{
			AppLogger.info("AssignemtnEvaluator for some reason find returned false " );
		}
		return "Nothing has happened"; //glob glob glob is 34 Credits
	}
	
	
	private long calculateSum(String romanExp) throws ConversionException{
		int sum = 0;
		/*for(Character letter: evalList){
			sum = sum + DBFactory.getAliasDBHandler().getCreditValue(letter);
		}*/
		
		return new RomanToArabicConverter().convert(romanExp);
		
	}
	
	public boolean match(String s){
		return PATTERN.matcher(s).matches(); 
	}
}
