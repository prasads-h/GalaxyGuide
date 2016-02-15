package com.gg.expression.converter;

import com.gg.rules.exception.ConversionException;

public interface IConverter<OUTPUT_OBJ> {
	
	OUTPUT_OBJ convert(String exp) throws ConversionException;
}
