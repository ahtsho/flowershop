package com.icare.flowershop.order;

import java.util.ArrayList;
import java.util.List;

public class SubOrder {
	private double subtotal;
	// code is a "cached" representation of the codes in the Items to speed up queries
	private String code;
	private List<Item> items;

	public SubOrder(List<Item> items) {
		this.items = items;
		this.setCode(this.items.get(0).getCode());
		this.computeTotal();
	}

	public List<Item> getItems() {
		return items;
	}

	public void pushItems(List<Item> items) {
		if (this.items == null) {
			this.items = new ArrayList<Item>();
		}
		this.items.addAll(items);
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public double computeTotal() {
		if(this.subtotal==0) {
			this.subtotal = this.items
					.stream()
					.map(item -> item.getPrice())
					.reduce(Double::sum).get();
		}
		return subtotal;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
