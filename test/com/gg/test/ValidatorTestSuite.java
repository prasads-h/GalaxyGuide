package com.gg.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.gg.expression.validator.test.RomanExpressionValidatorTestForI;
import com.gg.expression.validator.test.RomanExpressionValidatorTestForV;
import com.gg.expression.validator.test.RomanExpressionValidatorTestForX;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	   RomanExpressionValidatorTestForI.class,
	   RomanExpressionValidatorTestForV.class,
	   RomanExpressionValidatorTestForX.class
	})
public class ValidatorTestSuite {

}
