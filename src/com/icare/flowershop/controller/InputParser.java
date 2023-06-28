package com.icare.flowershop.controller;

import com.icare.flowershop.model.order.PurchaseRequest;

public class InputParser {

	public static PurchaseRequest parse(String userInput) throws NumberFormatException {

		return new PurchaseRequest(Integer.parseInt(userInput.split(" ")[0]),userInput.split(" ")[1]);
		
	}

}
