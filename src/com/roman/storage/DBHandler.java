package com.roman.storage;

public class DBHandler  implements ICreditDBHandler, IAliasDBHandler{
	
	public void insertAlias(String key, char romanletter){
		AppDb.getInstance().insertAlias(key, romanletter);
		
	}
	
	public Character getRomanLetterForAlias(String key){
		return AppDb.getInstance().getRomanLetterForAlias(key);
	}
	
	
	public void insertNewKey(String key, int creditValue){
		AppDb.getInstance().insertNewKey(key, creditValue);
	}
	
	
	
	@Override
	public int getCreditValueForKey(String key) {
		// TODO Auto-generated method stub
		return AppDb.getInstance().getCreditValueForKey(key);
	}
	
	@Override
	public int getCreditValue(Character c) {
		// TODO Auto-generated method stub
		return 0;
	}

}
