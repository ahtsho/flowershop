package com.icare.flowershop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader{
	
	private static BufferedReader reader;
	
	public static String readOrders() throws IOException {
		if (reader == null) {
			reader = new BufferedReader(new InputStreamReader(System.in));
		}
		
		return reader.readLine();
	}
	
	public static void close() throws IOException {
		reader.close();
	}
}
