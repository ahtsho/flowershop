package com.icare.flowershop.model.order;

public class Item {
	private int amount;
	private String code;
	private double price;
	private int bundle;

	public Item(int amount, String code, int bundle,double price) {
		this.amount = amount;
		this.code = code;
		this.bundle = bundle;
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBundle() {
		return bundle;
	}

	public void setBundle(int bundle) {
		this.bundle = bundle;
	}

}
