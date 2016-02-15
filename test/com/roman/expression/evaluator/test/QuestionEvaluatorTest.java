package com.roman.expression.evaluator.test;
import static org.junit.Assert.*;
import org.junit.Test;

import com.roman.expression.evalutor.QuestionEvaluator;

public class QuestionEvaluatorTest {

	@Test
	public void successScenario(){
		String input= "how many Credits is glob prok Gold ?";
		assertTrue(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void successScenario2(){
		String input= "how much is pish tegj glob glob ?";
		assertTrue(input, new QuestionEvaluator().match(input));
	}
	
	/*@Test
	public void test(){
		String input= "how much is how many Credits is pish tegj glob glob ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void test1(){
		String input= "how many Credits is how much is pish tegj glob glob ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}*/
	
	@Test
	public void test(){
		String input= "glob is pish tegj glob glob ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void test1(){
		String input= "glob is 50 Credits ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void test3(){
		String input= "how many Credits is 5000 ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}
	@Test
	public void test4(){
		String input= "how much is 5000 ?";
		assertFalse(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void test5(){
		String input= "how many Credits is glob?";
		assertTrue(input, new QuestionEvaluator().match(input));
	}
	
	@Test
	public void test6(){
		String input= "how many Credits is glob prok?";
		assertTrue(input, new QuestionEvaluator().match(input));
	}
	
	
}
