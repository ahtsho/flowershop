package com.icare.flowershop.controller;

import java.util.HashMap;
import java.util.Map;

public class InputParser {

	public static Map<String, Integer> parse(String userInput) throws NumberFormatException {

		return new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;

			{
				put(userInput.split(" ")[1], Integer.parseInt(userInput.split(" ")[0]));
			}
		};
	}

}
