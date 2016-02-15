package com.roman.expression.evaluator.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.roman.expression.evalutor.AssignmentEvaluator;;

public class  AssignmentEvaluatorTest {

	@Test
	public void successScenario() {
		String input = "glob glob Silver is 34 Credits";
		assertTrue(input, new AssignmentEvaluator().match(input));
	}
	
	@Test
	public void successScenario2() {
		String input = "a b c is 34 Credits";
		assertTrue(input, new AssignmentEvaluator().match(input));
	}
	@Test
	public void test8() {
		String input = "a 2 c is 34 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	@Test
	public void successScenario1() {
		String input = "glob glob prok silver is 70000 Credits";
		assertTrue(input, new AssignmentEvaluator().match(input));
	}
	
	@Test
	public void test() {
		String input = "glob glob prok silver is 70000 credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test2(){
		String input = "glob glob prok silver 70000 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test3(){
		String input = "glob glob prok silver is abc Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test4(){
		String input = "is 70000 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test5(){
		String input = "glob glob 1234 silver is 70000 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test6(){
		String input = "345234 silver is 70000 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
	
	@Test 
	public void test7(){
		String input = "345234 is 70000 Credits";
		assertFalse(input, new AssignmentEvaluator().match(input));
	}
}
