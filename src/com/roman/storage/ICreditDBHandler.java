package com.roman.storage;

public interface ICreditDBHandler {

	
	void insertNewKey(String key, int creditValue);
	int getCreditValueForKey(String key);
}
