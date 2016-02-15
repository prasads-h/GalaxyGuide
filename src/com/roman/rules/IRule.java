package com.roman.rules;

public interface IRule {
	
	char getSymbol();
	int getCreditValue();
	int getMaxOccurs();
	int getMinOccurs();

	public static class Operation{
		
		public enum operator{
			subtract, add;
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
