package com.gg.expression.validator.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gg.expression.validator.RomanExpressionValidator;
import com.gg.rules.exception.InvalidExpression;

public class RomanExpressionValidatorTestForV {

	@Test
	public void morerepeatitions() {
		try{
			new RomanExpressionValidator().validate("VVXI");
			fail();
		}catch(InvalidExpression e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void correctrepeatitions() {
		try{
			new RomanExpressionValidator().validate("VXI");
			assertTrue(true);
		}catch(InvalidExpression e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void maxOccurs() {
		try{
			new RomanExpressionValidator().validate("IVMV");
			fail();
		}catch(InvalidExpression e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void emptyInput() {
		try{
			new RomanExpressionValidator().validate("");
		}catch(InvalidExpression e){
			assertTrue(true);
		}
	}
	
}
