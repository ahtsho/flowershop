package com.icare.flowershop.controller;

public class InputValidator {

	public static boolean isValid(String userInput) {
		if(userInput ==null) return false;
		return userInput.matches("[0-9]* (R12|L09|T58)");
	}

}
