package com.icare.flowershop.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.icare.flowershop.error.NoSuchBundleException;
import com.icare.flowershop.model.order.FailedSubOrder;
import com.icare.flowershop.model.order.Item;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.PurchaseRequest;
import com.icare.flowershop.model.order.SubOrder;
import com.icare.flowershop.model.product.PricedBundle;
import com.icare.flowershop.model.product.Product;

public class Shop {

	private List<Product> products;
	
	public Shop(List<Product> products) {
		this.products = products;
	}

	public Order bundleSell(List<PurchaseRequest> requests) {
		Order bundledOrder = new Order(new ArrayList<SubOrder>());
		BundleMaker bundleMaker = new BundleMaker();
		for (PurchaseRequest request : requests) {
			
			List<Item> bundledItems;
			try {
				Product product = this.getProductByCode(request.getCode());
				List<PricedBundle> descSortedBundles = this.getReverseSortedBundles(product.getBundels());

				bundledItems = bundleMaker.createBundledItems(request.getAmount(), product, descSortedBundles);
				bundledOrder.addSuborder(new SubOrder(bundledItems));
			} catch (NoSuchBundleException e) {
				bundledOrder.addSuborder(new FailedSubOrder(request.getAmount(),request.getCode()));
			}
			
		}
		return bundledOrder;
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
	
	public List<Product> getAllProducts() {
		return this.products;
	}

}
