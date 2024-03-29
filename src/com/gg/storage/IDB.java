package com.gg.storage;

import com.gg.rules.IRule;

public interface IDB {
	void insertAlias(String key, char romanletter);
	Character getRomanLetterForAlias(String key);
	void insertNewKey(String key, long creditValue);
	long getCreditValueForKey(String key);
	
	long getCreditValue(Character c);
	
	IRule getRule(Character c);
}
