package com.roman.expression.converter;

public interface IConverter<OUTPUT_OBJ> {
	
	ExpressionOutput<OUTPUT_OBJ> convert(String exp);
}
