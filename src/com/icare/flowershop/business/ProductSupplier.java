package com.icare.flowershop.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.icare.flowershop.model.product.Flower;
import com.icare.flowershop.model.product.PricedBundle;
import com.icare.flowershop.model.product.Product;

public class ProductSupplier {

	public static List<Product> generateDemoProducts() {
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

}
