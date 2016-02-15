package com.gg.expression.evalutor;

public class EvaluatorFactory {

	
	
	private static EvaluatorFactory _inst = null;
	
	private EvaluatorFactory(){
		
	}
	
	public synchronized static EvaluatorFactory getInstance(){
		if(_inst == null)
			_inst = new EvaluatorFactory();
		return _inst;
	}
	
	private AliasEvalator aliasEvaluator = new AliasEvalator();
	private AssignmentEvaluator assignEvaluator = new AssignmentEvaluator();
	private QuestionEvaluator questionEvaluator = new QuestionEvaluator();
	
	public IInputMatcher aliasMatcher(){
		return aliasEvaluator;
	}
	
	public IInputMatcher assignmentMatcher(){
		return assignEvaluator;
	}
	
	public IInputMatcher questionMatcher(){
		return questionEvaluator;
	}
	
	public IExpressionEvaluator<String> aliasEvaluator(){
		return aliasEvaluator;
	}
	
	public IExpressionEvaluator<String> assignmentEvaluator(){
		return assignEvaluator;
	}
	
	public IExpressionEvaluator<String> questionEvaluator(){
		return questionEvaluator;
	}
}
