package com.gg.expression.validator.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gg.expression.validator.RomanExpressionValidator;
import com.gg.rules.exception.InvalidExpression;

public class RomanExpressionValidatorTestForX {

	@Test
	public void morerepeatitions() {
		try{
			new RomanExpressionValidator().validate("XXXXI");
			fail();
		}catch(InvalidExpression e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void correctrepeatitions() {
		try{
			new RomanExpressionValidator().validate("XXXIX");
			assertTrue(true);
		}catch(InvalidExpression e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void maxOccurs() {
		try{
			new RomanExpressionValidator().validate("XIMXVXMXX");
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
