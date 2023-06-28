package com.icare.flowershop.demo;

import java.util.HashMap;
import java.util.Map;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.view.Console;

public class ConsoleFlowershopAutoDemo {
	/**
	 * Sample input
	 * 
	 * 10 R12 
	 * 15 L09 
	 * 13 T58
	 * 
	 * Expected output
	 * 
	 * 10 R12 $12.99 
	 * 	1 x 10 $12.99 
	 * 15 L09 $41.90 
	 * 	1 x 9 $24.95 
	 * 	1 x 6 $16.95 
	 * 13 T58 $25.85 
	 * 	2 x 5 $9.95 
	 * 	1 x 3 $5.95
	 */
	public static void runWithCodingTestSampleInput() {
		Map<String, Integer> orderRequest = new HashMap<String, Integer>();
		orderRequest.put("R12", 10);
		orderRequest.put("L09", 15);
		orderRequest.put("T58", 13);

		Shop shop = new Shop(ProductSupplier.generateDemoProducts());
		Order bundledOrder = shop.bundleSell(orderRequest);
		Console.displayBundleBreakdownForEachProduct(orderRequest, bundledOrder);
	}

	

}
