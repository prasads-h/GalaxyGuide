package com.roman.expression.evalutor;

import com.roman.expression.converter.ExpressionOutput;
import com.roman.rules.exception.EvaluatorException;

public interface IExpressionEvaluator<OUTPUT> {

	OUTPUT evaluate(String s) throws EvaluatorException;
}
