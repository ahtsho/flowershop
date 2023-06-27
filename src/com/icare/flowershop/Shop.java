package com.icare.flowershop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.icare.flowershop.order.Item;
import com.icare.flowershop.order.Order;
import com.icare.flowershop.order.SubOrder;
import com.icare.flowershop.product.PricedBundle;
import com.icare.flowershop.product.Product;

public class Shop {

	private List<Product> products;
	private List<BundlingMatrixCell> bundlingMtx;
	
	public Shop(List<Product> products) {
		this.products = products;
	}

	public Order bundleSell(Map<String, Integer> orders) {
		Order bundledOrder = new Order(new ArrayList<SubOrder>());
		for (Map.Entry<String, Integer> order : orders.entrySet()) {
			
			List<Item> bundledItems = this.createBundledItems(order.getValue(), this.getProductByCode(order.getKey()));
			bundledOrder.addSuborder(new SubOrder(bundledItems));
		}
		return bundledOrder;
	}

	private List<Item>  createBundledItems(int amount, Product product) {
		List<PricedBundle> descSortedBundles = this.getReverseSortedBundles(product.getBundels());
		List<Item> bundledItems = new ArrayList<Item>(); 
		int minRow = this.calculateMinBundlesConfig(amount, product,descSortedBundles);
		List<BundlingMatrixCell> minialConfig = this.bundlingMtx
													.stream().filter(cell -> cell.row==minRow).collect(Collectors.toList());
		
		for(BundlingMatrixCell cell: minialConfig) {
			bundledItems.add(new Item(cell.quotient,product.getCode(),cell.bundle,cell.price));
		}
		/*for(int i = start; i < descSortedBundles.size(); i++) {
			PricedBundle bundle = descSortedBundles.get(i);
			if(amount < bundle.getBundle()) continue;
			
			segment = amount / bundle.getBundle();
			
			if(segment >= 1) {
				bundledItems.add(new Item(segment,product.getCode(),bundle.getBundle(),bundle.getPrice()));
				amount -=  segment * bundle.getBundle();
				
				if(amount == 0) break;
				
			} else {
				bundledItems.add(new Item(amount,product.getCode(),bundle.getBundle(),bundle.getPrice()));
				break;
			}
		}
		if(amount != 0) bundledItems.clear(); // unsuccessful bundling
		*/ 
		
		return bundledItems;
	}
	 
	/** The complexity of my algorithm scares me but it's beautiful!
	 * 	   
	 *     Bundling matrix
	 * 
	 *  	9 	| 6  | 3
	 *  	--------------
	 *  9  |15/9|6/6 | -  |
	 *     |1, 6|1, 0|    | 1+1 = 2 -> min number of bundles
	 *     	--------------
	 *  6  | \/	|15/6|3/3 |
	 *     | /\	|2, 3|1, 0| 2+1 = 3
	 *     	--------------	 
	 *  3  | \/	| \/ |15/3|
	 *     | /\	| /\ |5, 0| 5
	 *     	--------------	 
	 *     
	 *     where 
	 *     amount = 15
	 *     sorted bundles are 9,6,3   
	 */
	private int calculateMinBundlesConfig(int amount, Product product, List<PricedBundle> descSortedBundles) {
		bundlingMtx = new ArrayList<>();
		
		int minNumberOfBundles = Integer.MAX_VALUE;
		int winningRow = -1;

		for(int row = 0; row < descSortedBundles.size(); row++) {			
			int totalNuberOfBundlesPerRow = 0;
			int evolvingAmount = amount;
			for(int col = row; col < descSortedBundles.size(); col++) {
				BundlingMatrixCell cell = new BundlingMatrixCell(row,col);
				cell.amount = evolvingAmount;
				cell.quotient = cell.amount / descSortedBundles.get(col).getBundle();
				cell.remainder = evolvingAmount - cell.quotient * descSortedBundles.get(col).getBundle();
				cell.bundle = descSortedBundles.get(col).getBundle();
				cell.price = descSortedBundles.get(col).getPrice();
				bundlingMtx.add(cell);
				totalNuberOfBundlesPerRow += cell.quotient;
				evolvingAmount = cell.remainder;
				if(cell.remainder == 0) break;
			}
			
			if(totalNuberOfBundlesPerRow < minNumberOfBundles && evolvingAmount==0) {
				minNumberOfBundles = totalNuberOfBundlesPerRow;
				winningRow = row;
			}
			
		}
		if(winningRow==-1) {
			throw new Error("No bundling solution found for requested amount");
		}
		return winningRow;
	}

	 
	// let's pretend the code is always correct for now
	public Product getProductByCode(String code) {
		return products
				.stream()
				.filter(product -> product.getCode().equals(code))
				.findFirst().get();
	}
	
	public List<PricedBundle> getReverseSortedBundles(List<PricedBundle> bundles) {
		return bundles
			.stream()
			.sorted(Comparator.comparingInt(PricedBundle::getBundle)
				.reversed())
			.collect(Collectors.toList());
	}

	public void setProducts(List<Product> products) {
		this.products = products;	
	}
	
	private class BundlingMatrixCell {
		@Override
		public String toString() {
			return "("+ row + ","+ col + ") " + amount +"/"+bundle+"="+ quotient + " +"+ remainder +", p=" + price + "";
		}
		int row, col, amount, quotient, remainder, bundle;
		double price;
		public BundlingMatrixCell(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}

}
