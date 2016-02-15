package com.roman.expression.evaluator.test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.gg.expression.evalutor.AssignmentEvaluator;
import com.gg.input.executor.InputExecutor;
import com.gg.rules.exception.EvaluatorException;
import com.gg.rules.exception.UnavailableWordException;

public class AssignmentEvaluatorTest {
	
	@Before
	public void prepareInput(){
		InputExecutor inputExecutor = new InputExecutor();
		
		inputExecutor.execute("glob is I");
		
		inputExecutor.execute("prok is V");
		
		inputExecutor.execute("pish is X");
		
		inputExecutor.execute("tegj is L");
	}
	
	@Test
	public void successSenario(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("glob glob  Silver is 34 Credits");
			if(result.indexOf("Inserted") != -1 
					&& result.indexOf("Silver") != -1 && result.indexOf("17")!=-1)
				assertTrue(true);
			else 
				fail();
		}catch(EvaluatorException e){
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void morespaces(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("pish   pish  Iron is 3910 Credits");
			if(result.indexOf("Inserted") != -1 
					&& result.indexOf("Iron") != -1 && result.indexOf("195")!=-1)
				assertTrue(true);
			else 
				fail();
		}catch(EvaluatorException e){
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void unavailabeAlias(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("pishpish  Iron is 3910 Credits");
			fail();
		}catch(EvaluatorException e){
			if(e instanceof UnavailableWordException)
				assertTrue(e.getMessage(), true);
		}		
	}
	
	@Test
	public void successSenario1(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("glob prok Gold is 57800 Credits");
			if(result.indexOf("Inserted") != -1 
					&& result.indexOf("Gold") != -1 && result.indexOf("14450")!=-1)
				assertTrue(true);
			else 
				fail();
		}catch(EvaluatorException e){
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void successSenario2(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("pish pish Iron is 3910 Credits");
			if(result.indexOf("Inserted") != -1 
					&& result.indexOf("Iron") != -1 && result.indexOf("195")!=-1)
				assertTrue(true);
			else 
				fail();
		}catch(EvaluatorException e){
			fail(e.getMessage());
		}		
	}
	
	
	
	@Test
	public void failSenario(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("glob glob glob is 34 Credits");
			if(result.indexOf("Nothing has happened") != -1)
				assertTrue(true);
			else 
				fail();
		}catch(EvaluatorException e){
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void failSenario1(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("Silver glob glob prok is 34 Credits");
			fail();
		}catch(EvaluatorException e){
			if(e instanceof UnavailableWordException )
				assertTrue(e.getMessage(), true);
		}		
	}
	
	@Test
	public void failSenario2(){
		
		try{
			String result = 
					new AssignmentEvaluator().evaluate("glob Gold prok is 34 Credits");
			fail(result);
		}catch(EvaluatorException e){
			if(e instanceof UnavailableWordException )
				assertTrue(e.getMessage(), true);
		}		
	}

}
