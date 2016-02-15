package com.roman.expression.converter;

public class ExpressionOutput<OUTPUT_OBJ> {
	
	private OUTPUT_OBJ obj;

	public ExpressionOutput(OUTPUT_OBJ obj){
		this.obj  = obj;
	}

	public OUTPUT_OBJ getOutput() {
		return obj;
	}	
}


import com.roman.expression.converter.RomanToArabicConverter;
import com.roman.expression.evalutor.AssignmentEvaluator;
import com.roman.expression.evalutor.IExpressionEvaluator;


public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*IExpressionEvaluator<String> expressionEvaluator = new AssignmentEvaluator();
		expressionEvaluator.evaluate("glob glob Silver is 34 Credits");*/
		
		System.out.println(new RomanToArabicConverter().convert("MMVI").getOutput());		
	}

}


package com.roman.expression.converter;

import com.roman.rules.exception.ConversionException;

public interface IConverter<OUTPUT_OBJ> {
	
	ExpressionOutput<OUTPUT_OBJ> convert(String exp) throws ConversionException;
}


package com.roman.expression.converter;

import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import com.roman.expression.validator.RomanExpressionValidator;
import com.roman.log.AppLogger;
import com.roman.rules.IRule;
import com.roman.rules.IRule.Operation;
import com.roman.rules.IRule.Operation.operator;
import com.roman.rules.exception.ConversionException;
import com.roman.rules.exception.InvalidExpression;
import com.roman.storage.DBFactory;
import com.roman.utils.StringUtils;

public class RomanToArabicConverter implements IConverter<Long>{
	
	
	private static Pattern GT_PATTERN = Pattern.compile("nextletter is greater");
	
	@Override
	public ExpressionOutput<Long> convert(String exp) throws ConversionException {
		/*// TODO Auto-generated method stub
		if(ruleMap == null){
			AppLogger.info("Rule map is not created ");
		}*/	
		
		try{
			new RomanExpressionValidator().validate(exp);
		}catch(InvalidExpression ie){
			throw new ConversionException(ie); // validation failed
		}
		int expLength = exp.length();
		int cIndex = 0;
		
		Stack<Long> expStack = new Stack<Long>(){
			@Override
			public Long push(Long item) {
				// TODO Auto-generated method stub
				AppLogger.debug(String.valueOf(item));
				return super.push(item);
			}
		};
		while(cIndex < expLength){
			IRule cRule = DBFactory.getRulesDBHandler().getRule(exp.charAt(cIndex));
			boolean doDefault = true;;
			
			Set<Operation> opList = cRule.getOperations();
			
			for(Operation op: opList){
				if(StringUtils.isNotEmpty(op.getExpression()) &&
						GT_PATTERN.matcher(op.getExpression()).matches()){				
					if(cIndex +1 < expLength){
						IRule nexCharRule = DBFactory.getRulesDBHandler().getRule(exp.charAt(cIndex +1));
						if(nexCharRule.getCreditValue() > cRule.getCreditValue()){
							String optLetters = op.getOptLetters();
							if((optLetters != null && !"".equals(optLetters))){
								if(optLetters.indexOf(nexCharRule.getSymbol()) == -1){ // if it has preferences for subtraction
									expStack.push(cRule.getCreditValue());
									cIndex++;
									
								}else{
									expStack.push(performOperation(op.getOperator(), cRule.getCreditValue(), nexCharRule.getCreditValue()));
									cIndex = cIndex +2; // move to next leter										
								}
							}else{
								expStack.push(performOperation(op.getOperator(), cRule.getCreditValue(), nexCharRule.getCreditValue()));
								cIndex = cIndex +2; // move to next leter								
							}
							doDefault = false;
						}								
					}
					
				}else if( op.isIsdefault() && doDefault){
					expStack.push(cRule.getCreditValue());
					cIndex++;				
				}
			}			
		}	
		
		long result = 0;
		for(Long lValue: expStack){
			result += lValue;
		}
		
		return new ExpressionOutput<Long>(result);
	}
	
	private long performOperation(operator o, long curcreditValue, long nextcreditValue){
		AppLogger.debug(nextcreditValue + " - " + curcreditValue);
		switch(o){
		case subtract:
			return nextcreditValue - curcreditValue;
		case add:
			return nextcreditValue + curcreditValue;
		default:
			return 0;
		}
		
	}
}


package com.roman.expression.validator;

import com.roman.rules.IRule;
import com.roman.rules.exception.InvalidExpression;
import com.roman.rules.exception.InvalideInputException;
import com.roman.rules.exception.RuleNotFoundException;
import com.roman.storage.DBFactory;

public class RomanExpressionValidator implements IExpressionValidator {

	public RomanExpressionValidator() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void validate(String exp) throws InvalidExpression {
		// TODO Auto-generated method stub
		
		
		
		if(exp == null || "".equals(exp))
			throw new InvalidExpression("input is either null or empty");
		
		int cLength = exp.length();
		int cIndex = 0;// curIndex
		
		while(cIndex < cLength){
			char cChar = exp.charAt(cIndex);
			IRule cRule = DBFactory.getRulesDBHandler().getRule(cChar);	
			if(cRule == null)
				throw new InvalidExpression(new RuleNotFoundException(cChar));
			
			if(!new MaxMinOccurValidator().validate(cIndex, exp, cRule))
				throw new InvalidExpression("Failed MaxOccur validation for character " + cChar);
			if(!new RepeatitionValidator().validate(cIndex, exp, cRule))
				throw new InvalidExpression("Character repeats more than required times " + cChar);
			
			cIndex++;
		}
		
	}
	
	public interface IValidator{
		boolean validate(int cIndex, String exp, IRule rule);
	}
	
	public static class MaxMinOccurValidator implements IValidator{

		@Override
		public boolean validate(int cIndex, String exp, IRule rule) {
			// TODO Auto-generated method stub
			int noOfoccurs = 0;
			char c = exp.charAt(cIndex);
			int sIndex = 0;//search index.
			
			while((sIndex =exp.indexOf(c, sIndex)) >=0){
				noOfoccurs++;
				sIndex = sIndex+1; //start search from next car
			}
			if(noOfoccurs > rule.getMaxOccurs() || noOfoccurs < rule.getMinOccurs())
				return false;
			return true;
		}
	}
	
	public static class RepeatitionValidator implements IValidator{

		@Override
		public boolean validate(int cIndex, String exp, IRule rule) {
			// TODO Auto-generated method stub
			int noOfrepetitions = 0;
			char c = exp.charAt(cIndex);
			int sIndex = 0;//search index.
			int prevIndex = -1;
			while((sIndex =exp.indexOf(c, sIndex)) >=0){				
				if(prevIndex != -1 && prevIndex == sIndex-1)
					noOfrepetitions++;
				else if(prevIndex != sIndex -1)
					noOfrepetitions = 0;					
				if(noOfrepetitions > rule.getRepeatitions() -1)
					return false;			
				prevIndex = sIndex;
				sIndex = sIndex+1;					
			}			
			return true;
		}
	}
}


