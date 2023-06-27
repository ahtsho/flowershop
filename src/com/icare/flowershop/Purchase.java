package com.icare.flowershop;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icare.flowershop.order.Order;
import com.icare.flowershop.order.SubOrder;
import com.icare.flowershop.product.Flower;
import com.icare.flowershop.product.PricedBundle;
import com.icare.flowershop.product.Product;

public class Purchase {
	/*
	 * Sample input
	 * 
	 * 10 R12 
	 * 15 L09 
	 * 13 T58
	 * 
	 * expected output
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

	public static void main(String[] args) {

		Map<String, Integer> orderRequest = new HashMap<String, Integer>();
		orderRequest.put("R12", 10);
		orderRequest.put("L09", 15);
		orderRequest.put("T58", 13);

		Shop shop = new Shop(generateProducts());
		Order bundledOrder = shop.bundleSell(orderRequest);
		displayBundleBreakdownForEachProduct(orderRequest, bundledOrder);

	}

	private static List<Product> generateProducts() {
		List<Product> flowers = new ArrayList<Product>();
		// 5 @ $6.99, 10 @ $12.99
		flowers.add(new Flower("Roses", "R12", Arrays.asList(new PricedBundle(5, 6.99), new PricedBundle(10, 12.99))));
		// 3 @ $9.95, 6 @ $16.95, 9 @ $24.95
		flowers.add(new Flower("Lilies", "L09",
				Arrays.asList(new PricedBundle(3, 9.95), new PricedBundle(6, 16.95), new PricedBundle(9, 24.95))));
		// 3 @ $5.95, 5 @ $9.95, 9 @ $16.99
		flowers.add(new Flower("Tulips", "T58",
				Arrays.asList(new PricedBundle(3, 5.95), new PricedBundle(5, 9.95), new PricedBundle(9, 16.99))));
		return flowers;
	}

	private static void displayBundleBreakdownForEachProduct(Map<String, Integer> userOrders, Order bundledOrder) {
		DecimalFormat numformat = new DecimalFormat("#.##");
		
		for (Map.Entry<String, Integer> order : userOrders.entrySet()) {
			SubOrder suborder = bundledOrder.getSuborderByItemCode(order.getKey());
			System.out.println(order.getValue() + " " + order.getKey() + " $" + numformat.format(suborder.getSubtotal()));
			suborder.getItems()
					.stream()
					.forEach(item -> System.out
							.println("	" + item.getAmount() + " x " + item.getBundle() + " $" + item.getPrice()));
		}
	}
}
