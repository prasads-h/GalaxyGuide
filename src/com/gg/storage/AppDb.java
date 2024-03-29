package com.gg.storage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.gg.expression.converter.RomanToArabicConverter;
import com.gg.log.AppLogger;
import com.gg.rules.IRule;
import com.gg.rules.XMLRuleCreator;

public class AppDb implements IDB{

	private static AppDb _instance;
	//not a good place to load have to move somewhere else
	private static final String RULES_XML = "roman_rules.xml";
	private static Map<Character, IRule> ruleMap = null;
	
	static{
		try{
			InputStream xmlStream = RomanToArabicConverter.class
					.getClassLoader().getResourceAsStream(RULES_XML);
			
			ruleMap = new XMLRuleCreator().createRules(xmlStream);
		}catch(Exception e){
			AppLogger.error("unable to create rulemap " , e);
		}	
	}
	
	private Map<String, Character> aliasStore =new HashMap<String, Character>();
	
	private Map<String, Long> creditStore = new HashMap<String, Long>();
	private AppDb(){
			
	}
	
	public synchronized static IDB getInstance(){
		if(_instance == null)
			_instance = new AppDb();
		return _instance;		
	}
	
	@Override
	public Character getRomanLetterForAlias(String key) {
		// TODO Auto-generated method stub
		if(key.length() == 1 && ruleMap != null && ruleMap.get(key.charAt(0)) != null){
			return key.charAt(0);
		}
		return aliasStore.get(key);		
	}
	
	@Override
	public void insertAlias(String key, char romanletter) {
		// TODO Auto-generated method stub
		
		aliasStore.put(key,  romanletter);
		
	}
	
	@Override
	public void insertNewKey(String key, long creditValue) {
		// TODO Auto-generated method stub
		
		creditStore.put(key, creditValue);
		
		
	}
	@Override
	public long getCreditValueForKey(String key) {
		// TODO Auto-generated method stub
		if(key.length() == 1 && ruleMap != null && ruleMap.get(key.charAt(0)) != null){
			return ruleMap.get(key.charAt(0)).getCreditValue();
		}
		return creditStore.get(key);
	}
	
	@Override
	public long getCreditValue(Character c) {
		// TODO Auto-generated method stub
		IRule o = ruleMap.get(c);
		if(o != null)
			return o.getCreditValue();
		else if(creditStore.get(String.valueOf(c)) != null){
			return creditStore.get(String.valueOf(c));
		}
		return 0L;
	}
	
	
	@Override
	public IRule getRule(Character c) {
		// TODO Auto-generated method stub
		return ruleMap.get(c);
	}
	
}
