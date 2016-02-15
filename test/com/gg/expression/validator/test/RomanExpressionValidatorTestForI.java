package com.gg.expression.validator.test;

import org.junit.Test;
import static org.junit.Assert.*;

import com.gg.expression.validator.RomanExpressionValidator;
import com.gg.rules.exception.InvalidExpression;

public class RomanExpressionValidatorTestForI {

	@Test
	public void morerepeatitions() {
		try{
			new RomanExpressionValidator().validate("IIIIXI");
			fail();
		}catch(InvalidExpression e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void correctrepeatitions() {
		try{
			new RomanExpressionValidator().validate("IIIXI");
			assertTrue(true);
		}catch(InvalidExpression e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void maxOccurs() {
		try{
			new RomanExpressionValidator().validate("IXMIVIMII");
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
