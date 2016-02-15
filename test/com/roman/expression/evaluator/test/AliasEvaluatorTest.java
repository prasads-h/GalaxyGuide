package com.roman.expression.evaluator.test;

import org.junit.Test;

import static org.junit.Assert.*;
import com.gg.expression.evalutor.AliasEvalator;
import com.gg.rules.exception.EvaluatorException;

public class AliasEvaluatorTest {

	@Test
	public void successTest() {
		
		String result = new AliasEvalator().evaluate("glob is I");
		
		assertTrue(result.indexOf("Succesfully inserted alias key") != -1);
	}
	
	
	@Test
	public void invalidRomanLetter() {
		
		String result = new AliasEvalator().evaluate("glob is G");
		
		assertTrue(result.indexOf("assigning to invalid roman letter") != -1);
	}
	
	@Test
	public void aliasforexistingRomanLetter() {
		
		String result = new AliasEvalator().evaluate("I is X");
		
		assertTrue(result.indexOf("cannot assign existing roman letter with alias") != -1);
	}
}
