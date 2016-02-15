import com.gg.expression.evalutor.AssignmentEvaluator;
import com.gg.expression.evalutor.IExpressionEvaluator;


public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		IExpressionEvaluator<String> expressionEvaluator = new AssignmentEvaluator();
		expressionEvaluator.evaluate("glob glob Silver is 34 Credits");
		
		
	}

}
