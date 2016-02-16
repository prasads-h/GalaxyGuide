package com.gg.input.executor;

import com.gg.expression.evalutor.EvaluatorFactory;
import com.gg.log.AppLogger;
import com.gg.rules.exception.EvaluatorException;
import com.gg.rules.exception.UnRecognizableInputException;

public class InputExecutor {
	
	private EvaluatorFactory evaluatorFactory = EvaluatorFactory.getInstance();
	
	public String execute(String inputExp){
		
		try{
			if(evaluatorFactory.aliasMatcher().match(inputExp))
				return evaluatorFactory.aliasEvaluator().evaluate(inputExp);
			else if(evaluatorFactory.assignmentMatcher().match(inputExp))
				return evaluatorFactory.assignmentEvaluator().evaluate(inputExp);
			else if(evaluatorFactory.questionMatcher().match(inputExp))
				return evaluatorFactory.questionEvaluator().evaluate(inputExp);
			else 
				throw new UnRecognizableInputException("I am not sure what you are asking for ::: " + inputExp);
		}catch(EvaluatorException ee){
			AppLogger.error("Error executing input  " + inputExp + "\n" , ee);
			return ee.getMessage();
		}catch (UnRecognizableInputException e) {
			AppLogger.error(e.getMessage());
			return e.getMessage();
		}			
	}

}
