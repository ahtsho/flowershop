package com.icare.flowershop.demo;

import java.util.ArrayList;
import java.util.List;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.PurchaseRequest;
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
		List<PurchaseRequest> orderRequest = new ArrayList<PurchaseRequest>();
		
		orderRequest.add(new PurchaseRequest(10, "R12"));
		orderRequest.add(new PurchaseRequest(15,"L09"));
		orderRequest.add(new PurchaseRequest(13,"T58"));

		Shop shop = new Shop(ProductSupplier.generateDemoProducts());
		Order bundledOrder = shop.bundleSell(orderRequest);
		Console.displayBundleBreakdownForEachProduct(orderRequest, bundledOrder);
	}

	

}
