package com.gg.rules;


import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gg.log.AppLogger;
import com.gg.rules.IRule.Operation.operator;
import com.gg.rules.exception.InvalidRuleException;

public class XMLRule implements IRule{
	
	private char symbol;
	private long creditValue;
	private int maxOccurs;
	private int repeatitions;
	private int minOccurs;
	private Set<Operation> operations;	

	private XMLRule(){		
	}

	public static XMLRule valueOf(Node xmlNode) throws InvalidRuleException{
		AppLogger.debug("in valueOf " + xmlNode);
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
		
		//List<Operation> operationsList= new ArrayList<Operation>();
		TreeSet<Operation> operationSet = new TreeSet<Operation>(new Comparator<Operation>() {
			@Override
			public int compare(Operation o1, Operation o2) {
				// TODO Auto-generated method stub
				int res = o2.getPriority() - o1.getPriority();
				return res == 0 ?  o1.hashCode() - o2.hashCode() : res;
			}
		});
		
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
			
			oattr = getOperationAttr("default", opAttrMap);	
			if(oattr != null)
				operation.setdefault(Boolean.valueOf(oattr.getNodeValue()));
			oattr = getOperationAttr("priority", opAttrMap);	
			if(oattr != null)
				operation.setPriority(Integer.valueOf(oattr.getNodeValue()));
			else
				operation.setPriority(0);
			
			operationSet.add(operation);
		}	
		
		thisRule.setOperations(operationSet);
		
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

	public long getCreditValue() {
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

	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}
}

