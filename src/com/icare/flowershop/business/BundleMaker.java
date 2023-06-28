package com.icare.flowershop.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.icare.flowershop.error.NoSuchBundleException;
import com.icare.flowershop.model.order.Item;
import com.icare.flowershop.model.product.PricedBundle;
import com.icare.flowershop.model.product.Product;

public class BundleMaker {
	private List<BundlingMatrixCell> bundlingMtx;

	/** 
	 * 	   
	 * This method tries all possible bundle configurations and picks the one with minimum number of bundles.
	 * 1. Create a square matrix of size = n°bundles
	 * 2. sort the bundles from biggest to smallest
	 * 3. divide the original amount by the first bundle and store quotient and remainder 
	 * 4. while the remainder is > 0 exhaust the columns by repeating the same process
	 * 5. also repeat on the next row by starting from the next column 
	 * 6. when the end of the matrix is reached, pick the row with the smallest sum of bundles. 
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
	 *       
	 */
	public int calculateMinBundlesConfig(int amount, Product product, List<PricedBundle> descSortedBundles) throws NoSuchBundleException {
		if (amount == 0) throw new NoSuchBundleException(amount + " "+ product.getCode());

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
			throw new NoSuchBundleException(amount + " "+ product.getCode());
		}
		return winningRow;
	}

	public List<Item>  createBundledItems(int amount, Product product, List<PricedBundle> descSortedBundles) throws NoSuchBundleException {
		List<Item> bundledItems = new ArrayList<Item>(); 
		int minRow = this.calculateMinBundlesConfig(amount, product,descSortedBundles);
		List<BundlingMatrixCell> minialConfig = this.bundlingMtx
													.stream().filter(cell -> cell.row==minRow).collect(Collectors.toList());
		
		for(BundlingMatrixCell cell: minialConfig) {
			bundledItems.add(new Item(cell.quotient,product.getCode(),cell.bundle,cell.price));
		}
		
		return bundledItems;
	}
}
