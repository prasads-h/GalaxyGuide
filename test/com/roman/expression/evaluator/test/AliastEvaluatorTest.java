package com.roman.expression.evaluator.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.roman.expression.evalutor.AliasEvalator;;

public class AliastEvaluatorTest {

	@Test
	public void positiveInput() {
		String input = "glob is I";
		assertTrue(input, new AliasEvalator().match(input));
	}
	
	@Test 
	public void test6(){
		String input = "glob is 1";
		assertFalse(input, new AliasEvalator().match(input));
	}
	@Test 
	public void test7(){
		String input = "gold is 12342";
		assertFalse(input, new AliasEvalator().match(input));
	}
	
	@Test 
	public void test9(){
		String input = "2342 is 1";
		assertFalse(input, new AliasEvalator().match(input));
	}
	@Test 
	public void test3(){
		String input = "glob is ABC";
		assertFalse(input, new AliasEvalator().match(input));
	}
	
	@Test 
	public void test4(){
		String input = "  is ABC";
		assertFalse(input, new AliasEvalator().match(input));
	}
	
	@Test 
	public void test5(){
		String input = "break asd  is I credits";
		assertFalse(input, new AliasEvalator().match(input));
	}

}
