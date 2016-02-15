package com.gg.expression.validator;

import com.gg.rules.IRule;
import com.gg.rules.exception.InvalidExpression;
import com.gg.rules.exception.InvalideInputException;
import com.gg.rules.exception.RuleNotFoundException;
import com.gg.storage.DBFactory;

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
