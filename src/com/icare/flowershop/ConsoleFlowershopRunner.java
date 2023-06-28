package com.icare.flowershop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.controller.ConsoleReader;
import com.icare.flowershop.controller.InputParser;
import com.icare.flowershop.controller.InputValidator;
import com.icare.flowershop.demo.ConsoleFlowershopAutoDemo;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.PurchaseRequest;
import com.icare.flowershop.view.Console;

public class ConsoleFlowershopRunner {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-t")) {
			ConsoleFlowershopAutoDemo.runWithCodingTestSampleInput();
		} else {

			try {

				Shop shop = new Shop(ProductSupplier.generateDemoProducts());

				Console.displayWelcomeMessage();
				Console.displayAvailableProducts(shop);
				Console.displayInputPrompt();

				List<PurchaseRequest> orderRequest = new ArrayList<PurchaseRequest>();

				while (true) {
					String userInput = ConsoleReader.readOrders();

					if (InputValidator.isValid(userInput)) {
						orderRequest.add(InputParser.parse(userInput));
					} else if (userInput.equals("")) {
						if (!orderRequest.isEmpty()) {
							Console.displayThanks();
						} else {
							Console.displayNoPurchaseObservation();
						}
						ConsoleReader.close();
						break;
					} else {
						Console.displayInvalidInputError();
						Console.displayInputPrompt();
					}
				}

				Order bundledOrder = shop.bundleSell(orderRequest);
				
				Console.displayBundleBreakdownForEachProduct(orderRequest, bundledOrder);
				
				Console.displayGoodbye();

			} catch (NumberFormatException | IOException e) {
				Console.displayInvalidInputError();
				Console.displayInputPrompt();
			}

		}
	}

}
