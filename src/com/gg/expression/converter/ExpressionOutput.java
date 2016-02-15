package com.gg.expression.converter;

public class ExpressionOutput<OUTPUT_OBJ> {
	
	private OUTPUT_OBJ obj;

	public ExpressionOutput(OUTPUT_OBJ obj){
		this.obj  = obj;
	}

	public OUTPUT_OBJ getOutput() {
		return obj;
	}	
}
