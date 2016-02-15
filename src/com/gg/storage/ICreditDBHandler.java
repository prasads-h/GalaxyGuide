package com.gg.storage;

public interface ICreditDBHandler {

	
	void insertNewKey(String key, long creditValue);
	long getCreditValueForKey(String key);
}
