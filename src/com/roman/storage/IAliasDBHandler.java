package com.roman.storage;

public interface IAliasDBHandler {
	void insertAlias(String key, char romanletter);
	Character getRomanLetterForAlias(String key);
	
	int getCreditValue(Character c);
	
}
