package com.gg.expression.validator;

import com.gg.rules.exception.InvalidExpression;

public interface IExpressionValidator {
	void validate(String exp) throws InvalidExpression;
}
