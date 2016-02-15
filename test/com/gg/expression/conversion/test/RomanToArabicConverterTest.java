package com.gg.expression.conversion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gg.expression.converter.RomanToArabicConverter;
import com.gg.expression.validator.RomanExpressionValidator;
import com.gg.rules.exception.ConversionException;

public class RomanToArabicConverterTest {

	@Test
	public void test() {
		
		try{
		long result = new RomanToArabicConverter().convert("MCMXLIV");
		
		assertTrue(result == 1944 );
		}catch(ConversionException ce){
			fail(ce.getMessage());
		}
	}
	
	@Test
	public void test2() {
		
		try{
		long result = new RomanToArabicConverter().convert("XXXIX");
		
		assertTrue(result == 39 );
		}catch(ConversionException ce){
			fail(ce.getMessage());
		}
	}
	
	@Test
	public void test3() {
		
		try{
		long result = new RomanToArabicConverter().convert("MCMIII");
		
		assertTrue(result == 1903 );
		}catch(ConversionException ce){
			fail(ce.getMessage());
		}
	}

}
