package com.gg.expression.converter;

import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import com.gg.expression.validator.RomanExpressionValidator;
import com.gg.log.AppLogger;
import com.gg.rules.IRule;
import com.gg.rules.IRule.Operation;
import com.gg.rules.IRule.Operation.operator;
import com.gg.rules.exception.ConversionException;
import com.gg.rules.exception.InvalidExpression;
import com.gg.storage.DBFactory;
import com.gg.utils.StringUtils;

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
