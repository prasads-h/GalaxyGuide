package com.roman.storage;

public interface IDB {
	void insertAlias(String key, char romanletter);
	Character getRomanLetterForAlias(String key);
	void insertNewKey(String key, int creditValue);
	int getCreditValueForKey(String key);
	
	int getCreditValue(Character c);
}
