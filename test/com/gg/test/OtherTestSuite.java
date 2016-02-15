package com.gg.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.gg.expression.conversion.test.RomanToArabicConverterTest;
import com.roman.expression.evaluator.test.AliasEvaluatorTest;
import com.roman.expression.evaluator.test.AliastMatcherTest;
import com.roman.expression.evaluator.test.AssignmentEvaluatorTest;
import com.roman.expression.evaluator.test.AssignmentMatcherTest;
import com.roman.expression.evaluator.test.QuestionEvaluatorTest;
import com.roman.expression.evaluator.test.QuestionMatcherTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({RomanToArabicConverterTest.class,
		AliasEvaluatorTest.class,
		AliastMatcherTest.class,
		AssignmentEvaluatorTest.class,
		AssignmentMatcherTest.class,
		QuestionEvaluatorTest.class,
		QuestionMatcherTest.class})
public class OtherTestSuite {

}
