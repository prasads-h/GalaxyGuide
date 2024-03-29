package com.gg.rules;

import java.util.Set;

public interface IRule {
	
	char getSymbol();
	long getCreditValue();
	int getMaxOccurs();
	int getMinOccurs();
	int getRepeatitions();
	public Set<Operation> getOperations();
	

	public static class Operation{
		
		public enum operator{
			subtract, add;
		}
		
		private boolean isdefault;
		public boolean isIsdefault() {
			return isdefault;
		}
		private int priority;

		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		public void setdefault(boolean isdefault) {
			this.isdefault = isdefault;
		}
		private String expression;
		private String optLetters;
		public String getExpression() {
			return expression;
		}
		public void setExpression(String expression) {
			this.expression = expression;
		}
		public String getOptLetters() {
			return optLetters;
		}
		public void setOptLetters(String optLetters) {
			this.optLetters = optLetters;
		}
		public operator getOperator() {
			return operator;
		}
		public void setOperator(operator operator) {
			this.operator = operator;
		}
		private operator operator;
		
	}
}
