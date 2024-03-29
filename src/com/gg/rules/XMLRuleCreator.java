package com.gg.rules;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gg.rules.exception.InvalidRuleException;
import com.gg.rules.exception.XMLRulesParseException;

public class XMLRuleCreator implements IRuleCreator{

	public java.util.Map<Character,IRule> createRules(InputStream xml) throws Exception{
		Map<Character, IRule> letterRules = new HashMap<Character,IRule>();
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document ruleDoc = db.parse(xml);
			NodeList letterNodes = ruleDoc.getElementsByTagName("letter");			
			
			for( int index=0; index< letterNodes.getLength(); index++){
				Node letterNode = letterNodes.item(index);
				XMLRule xmlRule = XMLRule.valueOf(letterNode);
				letterRules.put(xmlRule.getSymbol(), xmlRule);
			}
		}catch(InvalidRuleException e){
			throw new XMLRulesParseException(e);		
		}
		catch(Exception e){
			throw new Exception("Error parsing xml file " + e.getMessage());
			
			
		}
		
		
		return letterRules;
	};
}
