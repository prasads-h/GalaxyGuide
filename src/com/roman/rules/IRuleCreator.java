package com.roman.rules;

import java.io.InputStream;
import java.util.Map;

public interface IRuleCreator {

	Map<Character, IRule> createRules(InputStream xml) throws Exception;
}
