package com.roman.rules.exception;

public class UnavailableWordException extends EvaluatorException{

	public UnavailableWordException(String word){
		super("alias unavailable for word " + word + " please insert the alias first ");
	}
}
