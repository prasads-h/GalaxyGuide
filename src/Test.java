import com.gg.expression.evalutor.AssignmentEvaluator;
import com.gg.expression.evalutor.IExpressionEvaluator;
import com.gg.expression.evalutor.InputEvaluator;
import com.gg.expression.evalutor.QuestionEvaluator;
import com.gg.input.executor.InputExecutor;


public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		InputExecutor inputExecutor = new InputExecutor();
		
		System.out.println(inputExecutor.execute("glob is I"));
		
		System.out.println(inputExecutor.execute("prok is V"));
		
		System.out.println(inputExecutor.execute("pish is X"));
		
		System.out.println(inputExecutor.execute("tegj is L"));
		
		System.out.println(inputExecutor.execute("glob glob Silver is 34 Credits"));
		
		System.out.println(inputExecutor.execute("glob prok Gold is 57800 Credits"));
		
		System.out.println(inputExecutor.execute("pish pish Iron is 3910 Credits"));
		
		System.out.println(inputExecutor.execute("how much is pish tegj glob glob ?"));
		
		System.out.println(inputExecutor.execute("how many Credits is glob prok Silver ?"));
		
		System.out.println(inputExecutor.execute("how many Credits is glob prok Gold ?"));
		
		System.out.println(inputExecutor.execute("how many Credits is glob prok Iron ?"));
		
		System.out.println(inputExecutor.execute("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));	
		
		
	}

}
