package com.roman.expression.evaluator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gg.expression.evalutor.QuestionEvaluator;
import com.gg.input.executor.InputExecutor;
import com.gg.rules.exception.EvaluatorException;

public class QuestionEvaluatorTest {
	
	@Before
	public void loadInput(){
		InputExecutor inputExecutor = new InputExecutor();
		
		inputExecutor.execute("glob is I");
		
		inputExecutor.execute("prok is V");
		
		inputExecutor.execute("pish is X");
		
		inputExecutor.execute("tegj is L");
		
		inputExecutor.execute("glob glob Silver is 34 Credits");
		
		inputExecutor.execute("glob prok Gold is 57800 Credits");
		
		inputExecutor.execute("pish pish Iron is 3910 Credits");	
		
	}

	@Test
	public void test() {
		try{
			String result = new QuestionEvaluator().evaluate("how much is pish tegj glob glob ?");
			
			assertTrue("pish tegj glob glob  is 42".equals(result));
		}catch(EvaluatorException ee){
			fail(ee.getMessage());
		}		
	}
	
	@Test
	public void test2() {
		try{
			String result = new QuestionEvaluator().evaluate("how many Credits is glob prok Silver ?");
			
			assertTrue("glob prok Silver is 68 Credits".equals(result));
		}catch(EvaluatorException ee){
			fail(ee.getMessage());
		}		
	}
	
	@Test
	public void test3() {
		try{
			String result = new QuestionEvaluator().evaluate("how many Credits is glob prok Gold ?");
			
			assertTrue("glob prok Gold is 57800 Credits".equals(result));
		}catch(EvaluatorException ee){
			fail(ee.getMessage());
		}		
	}
	
	@Test
	public void test4() {
		try{
			String result = new QuestionEvaluator().evaluate("how many Credits is glob prok Iron ?");
			
			assertTrue("glob prok Iron is 780 Credits".equals(result));
		}catch(EvaluatorException ee){
			fail(ee.getMessage());
		}		
	}
	
	@Test
	public void unrecognizabletext(){
		try{
			String result = new QuestionEvaluator().evaluate("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
			
			assertTrue(null == result);
		}catch(EvaluatorException ee){
			fail(ee.getMessage());
		}
		
	}

}
