package com.gg.expression.evalutor;

import com.gg.expression.converter.ExpressionOutput;
import com.gg.rules.exception.EvaluatorException;

public interface IExpressionEvaluator<OUTPUT> {

	OUTPUT evaluate(String s) throws EvaluatorException;
}
