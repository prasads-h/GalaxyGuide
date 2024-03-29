package com.gg.storage;

import com.gg.rules.IRule;

public class DBHandler  implements ICreditDBHandler, IAliasDBHandler, IRuleDBHandler{
	
	public void insertAlias(String key, char romanletter){
		AppDb.getInstance().insertAlias(key, romanletter);
		
	}
	
	public Character getRomanLetterForAlias(String key){
		return AppDb.getInstance().getRomanLetterForAlias(key);
	}
	
	
	public void insertNewKey(String key, long creditValue){
		AppDb.getInstance().insertNewKey(key, creditValue);
	}
	
	
	
	@Override
	public long getCreditValueForKey(String key) {
		// TODO Auto-generated method stub
		return AppDb.getInstance().getCreditValueForKey(key);
	}
	
	@Override
	public long getCreditValue(Character c) {
		// TODO Auto-generated method stub
		return AppDb.getInstance().getCreditValue(c);
	}
	
	@Override
	public IRule getRule(Character c) {
		// TODO Auto-generated method stub
		return AppDb.getInstance().getRule(c);
	}
	
	
	

}
