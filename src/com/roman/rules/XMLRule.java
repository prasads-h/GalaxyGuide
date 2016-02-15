package com.roman.rules;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.roman.rules.IRule.Operation.operator;
import com.roman.rules.exception.InvalidRuleException;

public class XMLRule implements IRule{
	
	private char symbol;
	private int creditValue;
	private int maxOccurs;
	private int repeatitions;
	private int minOccurs;
	private List<Operation> operations;
	
	private XMLRule(){		
	}

	public static XMLRule valueOf(Node xmlNode) throws InvalidRuleException{
		System.out.println("in valueOf " + xmlNode);
		NamedNodeMap attrMap = xmlNode.getAttributes();
		
		XMLRule thisRule = new XMLRule();
		Node attr = getNamedItem("symbol", attrMap);		
		thisRule.setSymbol(attr.getNodeValue().charAt(0));
		
		attr = getNamedItem("creditvalue", attrMap);
		thisRule.setCreditValue(Integer.valueOf(attr.getNodeValue()));
		
		attr = getNamedItem("maxOccurs", attrMap);		
		thisRule.setMaxOccurs(Integer.valueOf(attr.getNodeValue()));
		
		attr = getNamedItem("minOccurs", attrMap);
		thisRule.setMinOccurs(Integer.valueOf(attr.getNodeValue()));	
		
		
		attr = getNamedItem("repeatitions", attrMap);
		thisRule.setRepeatitions(Integer.valueOf(attr.getNodeValue()));	
		
		Element xmlElement = (Element)xmlNode;
		
		NodeList operationList = xmlElement.getElementsByTagName("operation");
		
		List<Operation> operationsList= new ArrayList<Operation>();
		
		for(int i=0; i< operationList.getLength();i++){
			Node operationNode = operationList.item(i);
			
			Operation operation = new Operation();
			
			NamedNodeMap opAttrMap = operationNode.getAttributes();
			
			Node oattr = getOperationAttr("expression", opAttrMap);		
			if(oattr != null)
				operation.setExpression(oattr.getNodeValue());
			
			
			oattr = getOperationAttr("perform", opAttrMap);	
			if(oattr != null)
				operation.setOperator(operator.valueOf(oattr.getNodeValue()));
			else
				operation.setOperator(operator.add); // default operation
			
			oattr = getOperationAttr("letters", opAttrMap);	
			if(oattr != null)
				operation.setOptLetters(oattr.getNodeValue());
		}		
		
		return thisRule;
	}
	
	
	private static Node getOperationAttr(String attrName, NamedNodeMap attrMap) throws InvalidRuleException{
		Node attr = attrMap.getNamedItem(attrName);
		if(attr != null && "perform".equals(attrName)){
			if(operator.valueOf(attr.getNodeValue()) == null){
				throw new InvalidRuleException("doesn't contain valid value" +  attrName +"attribute");
			}
		}
		return attr;
	}
	
	private static Node getNamedItem(String attrName, NamedNodeMap attrMap) throws InvalidRuleException{
		Node attr = attrMap.getNamedItem(attrName);
		if(attr == null || attr.getNodeValue()== null|| "".equals(attr.getNodeValue()))
			throw new InvalidRuleException("doesn't contain valid" +  attrName +"attribute");
		
		
		return attr;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public int getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	public int getRepeatitions() {
		return repeatitions;
	}

	public void setRepeatitions(int repeatitions) {
		this.repeatitions = repeatitions;
	}

	public int getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
}

