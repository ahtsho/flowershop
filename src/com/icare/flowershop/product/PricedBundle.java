package com.icare.flowershop.product;

public class PricedBundle {
	
	private int bundle;
	private double price;
	
	public PricedBundle(int bundle, double price) {
		super();
		this.bundle = bundle;
		this.price = price;
	}

	
	public int getBundle() {
		return bundle;
	}

	public void setBundle(int bundle) {
		this.bundle = bundle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	
	
	
}