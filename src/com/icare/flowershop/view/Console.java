package com.icare.flowershop.view;

import java.text.DecimalFormat;
import java.util.Map;

import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.order.FailedSubOrder;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.SubOrder;
import com.icare.flowershop.model.product.PricedBundle;
import com.icare.flowershop.model.product.Product;

public class Console {
	private static String[] FLOWERS = new String[]{"🌹","🌼","🌸","🌻","🍀"};
	private static String WELCOME = "Welcome to iCare's";
	private static String AUTHOR = "Ahadu Tsegaye Abebe";
	private static String FLOWERSHOP = " ________   .---.       ,-----.    .--.      .--.    .-''-.  .-------.       .-'''-. .---.  .---.     ,-----.    .-------.  \n"
			+ "|        |  | ,_|     .'  .-,  '.  |  |_     |  |  .'_ _   \\ |  _ _   \\     / _     \\|   |  |_ _|   .'  .-,  '.  \\  _(`)_ \\ \n"
			+ "|   .----',-./  )    / ,-.|  \\ _ \\ | _( )_   |  | / ( ` )   '| ( ' )  |    (`' )/`--'|   |  ( ' )  / ,-.|  \\ _ \\ | (_ o._)| \n"
			+ "|  _|____ \\  '_ '`) ;  \\  '_ /  | :|(_ o _)  |  |. (_ o _)  ||(_🌼 _) /   (_ o _).   |   '-(_{;}_);  \\  '_ /  | :|  (_,_) / \n"
			+ "|_( )_   | > (_)  ) |  _`,/ \\ _/  || (_,_) \\ |  ||  (_,_)___|| (_,_).' __  (_,_). '. |      (_,_) |  _`,/ \\ _/  ||   '-.-'  \n"
			+ "(_ o._)__|(  .  .-' : (  '\\_/ \\   ;|  |/    \\|  |'  \\   .---.|  |\\ \\  |  |.---.  \\  :| _ _--.   | : (  '\\_/ \\   ;|   |      \n"
			+ "|(_,_)     `-'`-'|___\\ `\"/  \\  ) / |  '  /\\  `  | \\  `-'    /|  | \\ `'   /\\    `-'  ||( ' ) |   |  \\ `\"/  \\  ) / |   |      \n"
			+ "|   |       |        \\'. \\_/``\".'  |    /  \\    |  \\       / |  |  \\    /  \\       / (_{;}_)|   |   '. \\_/``\".'  /   )      \n"
			+ "'---'       `--------`  '-----'    `---'    `---`   `'-..-'  ''-'   `'-'    `-...-'  '(_,_) '---'     '-----'    `---'      \n"
			+ "                                                                                                                           ";
	public static void displayWelcomeMessage() {
		int numFlowers = 50; 
		displayRandomFlowerRow(numFlowers);
		System.out.println();
		System.out.println("   "+WELCOME);
		System.out.println(FLOWERSHOP);
		System.out.println(" 													by "+AUTHOR);
		System.out.println();
		displayRandomFlowerRow(numFlowers);
		System.out.println();
		System.out.println("Unfortunately we don't sell our flowers in arbitrary amounts any more.");
		System.out.println("We now sell our flowers in bundles and the price will depend on the bundle.");
		System.out.println("⚠️ WARNING: If the amount you ask is not splittable in existing bundeles, we will not be able to satisfy your request️.");
		System.out.println();
		System.out.println();
		System.out.println("Here's an inventory of our current products and the available bundles.");
		System.out.println("-------------------------------------------------------------------------------");
		

	}
	public static void displayAvailableProducts(Shop shop) {
		displayMsgOfGivenLength( " NAME	|", 10);
		displayMsgOfGivenLength( " CODE	|", 10);
		displayMsgOfGivenLength( " BUNDLES", 10);
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");

		for(Product prod: shop.getAllProducts()) {
			displayMsgOfGivenLength(" "+prod.getName() + "	|", 10);
			displayMsgOfGivenLength(" "+prod.getCode() + "		|", 10);
			for(PricedBundle bundle : prod.getBundels()) {
				displayMsgOfGivenLength(" "+bundle.toString()+", 	", 10);
				
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println();
	}
	private static void displayMsgOfGivenLength(String msg, int length) {
		System.out.print(msg);
		if(msg.length() < length) {
			System.out.print(new String(new char[length - msg.length()]).replace('\0', ' '));
		}
	}
	private static void displayRandomFlowerRow(int length) {
		for(int i = 0; i < length; i++) {
			int rand = (int) (0 + Math.random() * 4);
			System.out.print(FLOWERS[rand]);
			if(i%3==0) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	public static void displayInputPrompt() {
		System.out.println("Please list your orders in the following format 'amount <1 space> code' (case sensitive):\n"
				+"10 R12\n"
				+ "15 L09\n"
				+ "13 T58");
		System.out.println("and place them by pressing Enter twice.");
		System.out.println();
	};
	public static void displayBundleBreakdownForEachProduct(Map<String, Integer> userOrders, Order bundledOrder) {
		DecimalFormat numformat = new DecimalFormat("#.##");
		numformat.setMinimumFractionDigits(2);
		System.out.println("-------------------------------");
		for (Map.Entry<String, Integer> order : userOrders.entrySet()) {
			SubOrder suborder = bundledOrder.getSuborderByItemCode(order.getKey());
			String subtotalOrError = "";
			if(suborder.getSubtotal() > 0) {
				subtotalOrError = "$"+numformat.format(suborder.getSubtotal()); 
			} else {
				subtotalOrError = FailedSubOrder.FAILURE_MESSAGE;
			}
			System.out.println(order.getValue() + " " + order.getKey() + " " + subtotalOrError);
			suborder.getItems()
					.stream()
					.forEach(item -> System.out
							.println("	" + item.getAmount() + " x " + item.getBundle() + " $" + item.getPrice()));
		}
		System.out.println("-------------------------------");
	}
	public static void displayInvalidInputError() {
		System.out.println("ERROR: Invalid input!");
	}
	public static void displayThanks() {
		System.out.println("Thank you for your order!");
		System.out.println("Here's the cost and bundle breakdown for each product.");
		System.out.println();
		
	}
	
	public static void displayGoodbye() {
		System.out.println();
		System.out.print("Goodbye! See you next time!");
		displayRandomFlowerRow(1);
	}
	public static void displayNoPurchaseObservation() {
		System.out.println("Ops, we've notices that you haven't placed an order, please make sure to read the instructions.");
	}
	
}
