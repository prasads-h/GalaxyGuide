package com.gg.storage;

public interface IAliasDBHandler {
	void insertAlias(String key, char romanletter);
	Character getRomanLetterForAlias(String key);
	
	long getCreditValue(Character c);
	
}
